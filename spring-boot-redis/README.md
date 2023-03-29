# SpringBoot（四）：Redis 缓存与 Stream

**Remote Dictionary Server(Redis)** 是一个由 **Salvatore Sanfilippo** 写的 KEY-VALUE 存储系统。Redis 是一个开源的使用 ANSI C 语言编写、遵守 BSD 协议、支持网络、可基于内存亦可持久化的日志型、Key-Value 数据库，并提供多种语言的 API。它通常被称为数据结构服务器，因为值可以是字符串, 哈希, 列表, 集合和有序集合等类型。在 Redis 5 版本，还添加了对 Stream 这一特殊的数据结构的支持。

### 添加依赖

首先我们在 pom.xml 中添加 redis 的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 配置 Redis

```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.database=0
spring.redis.timeout=5000
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.min-idle=0
spring.redis.lettuce.shutdown-timeout=100ms
```

```Java
@Configuration
public class RedisConfig {

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

}
```

### 访问 Redis 数据

```Java
@SpringBootTest
public class ApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void stringRedisTemplateTest() {
        stringRedisTemplate.opsForValue().set("MSG", "Hi");
        String data = stringRedisTemplate.opsForValue().get("MSG");
        Assertions.assertEquals(data, "Hi");
    }

    @Test
    public void redisTemplateTest() {
        redisTemplate.opsForHash().put("HMSG", "MSG", "Hi");
        Object data = redisTemplate.opsForHash().get("HMSG", "MSG");
        Assertions.assertEquals(String.valueOf(data), "Hi");
    }


}
```

### 配置 Redis Stream

```Java
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
```

添加一个用来接收 Redis Stream 的 Listener

```Java
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
```

向 Redis Stream 发送消息

```Java
@SpringBootTest
public class ApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisStreamTest() {
        Map<String, String> data = new HashMap<>();
        data.put("MSG", "Hi");
        MapRecord<String, String, String> record = MapRecord.create(MessageEventListener.STREAM_KEY, data);
        stringRedisTemplate.opsForStream().add(record);
    }
    
}
```
