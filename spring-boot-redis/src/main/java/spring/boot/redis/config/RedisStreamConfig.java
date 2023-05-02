package spring.boot.redis.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.concurrent.Executors;

/**
 * @author vincent
 * @since 1.0
 */

@Slf4j
@Configuration
public class RedisStreamConfig implements DisposableBean {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private MessageEventListener messageEventListener;

    private Subscription subscription;

    private StreamMessageListenerContainer<String, MapRecord<String, String, String>> listenerContainer;

    @Bean
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> listenerContainer() {

        // 在定义 Stream Container 之前，需要先创建 Stream 和对应的 Group
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(MessageEventListener.STREAM_KEY))) {
            RecordId recordId = stringRedisTemplate.opsForStream().add(MessageEventListener.STREAM_KEY, Collections.singletonMap("", ""));
            stringRedisTemplate.opsForStream().createGroup(MessageEventListener.STREAM_KEY, MessageEventListener.STREAM_GROUP);
            stringRedisTemplate.opsForStream().delete(MessageEventListener.STREAM_KEY, recordId);
            log.info("Redis stream does not exist, create empty stream [{}] and stream group [{}]", MessageEventListener.STREAM_KEY, MessageEventListener.STREAM_GROUP);
        }

        // Stream Container 的配置
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                        // 定义线程池
                        .executor(Executors.newFixedThreadPool(16))
                        // 每次拉取的消息数量
                        .batchSize(10)
                        // 异常处理器
                        .errorHandler(t -> log.error("Redis stream error!", t))
                        // 每次拉取最新消息的时间间隔
                        .pollTimeout(Duration.of(1000, ChronoUnit.MILLIS))
                        .serializer(new StringRedisSerializer())
                        .build();

        listenerContainer = StreamMessageListenerContainer.create(redisConnectionFactory, options);

        // 添加一个 Event Listener
        subscription = listenerContainer.receive(
                Consumer.from(MessageEventListener.STREAM_GROUP, "DEFAULT_CONSUMER"),
                StreamOffset.create(MessageEventListener.STREAM_KEY, ReadOffset.lastConsumed()),
                messageEventListener
        );

        // 启动监听容器
        listenerContainer.start();

        return listenerContainer;
    }

    @Override
    public void destroy() throws Exception {
        // Spring 应用关闭前，需要先关闭容器和容器里面处于 Active 状态下的订阅
        listenerContainer.stop(() -> {
            try {
                subscription.await(Duration.of(5000, ChronoUnit.MILLIS));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
