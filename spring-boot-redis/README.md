# SpringBoot（五）：SpringBoot 中访问 Redis
**REmote DIctionary Server(Redis)** 是一个由 `Salvatore Sanfilippo` 写的 KEY-VALUE 存储系统。Redis 是一个开源的使用 ANSI C 语言编写、遵守 BSD 协议、支持网络、可基于内存亦可持久化的日志型、Key-Value 数据库，并提供多种语言的 API。它通常被称为数据结构服务器，因为值可以是字符串, 哈希, 列表, 集合和有序集合等类型。

### 添加依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 配置文件
```yml
spring:
  redis:
    host: localhost
    database: 0
    port: 6379
    timeout: 3000
```

### 配置序列化方式
```java
@Configuration
public class LettuceRedisConfiguration {
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
```

### 测试一下
```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {
    @Resource
    private RedisTemplate<String, User> redisTemplate;
    @Test
    public void redisTemplateTest() {
        redisTemplate.opsForValue().set("USER", new User(1L, "Vincent", "Jiang"));
        User user = redisTemplate.opsForValue().get("USER");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId().longValue(), 1L);
        Assert.assertEquals(user.getFirstName(), "Vincent");
        Assert.assertEquals(user.getLastName(), "Jiang");
    }
}
```