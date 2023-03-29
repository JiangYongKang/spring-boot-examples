package spring.boot.redis.tests;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import spring.boot.redis.config.MessageEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: vincent
 * Date: 2020-11-20 11:31
 * Comment:
 */

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

    @Test
    public void redisStreamTest() {
        Map<String, String> data = new HashMap<>();
        data.put("MSG", "Hi");
        MapRecord<String, String, String> record = MapRecord.create(MessageEventListener.STREAM_KEY, data);
        stringRedisTemplate.opsForStream().add(record);
    }


}
