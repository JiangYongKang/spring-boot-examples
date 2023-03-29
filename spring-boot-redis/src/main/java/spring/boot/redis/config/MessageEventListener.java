package spring.boot.redis.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author vincent
 * @since 1.0
 */

@Slf4j
@Component
public class MessageEventListener implements StreamListener<String, MapRecord<String, String, String>> {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static final String STREAM_KEY = "MESSAGE_EVENT";
    public static final String STREAM_GROUP = "MESSAGE_GROUP";

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        log.info("Receive message: {}", message);
        stringRedisTemplate.opsForStream().acknowledge(STREAM_KEY, message);
    }

}
