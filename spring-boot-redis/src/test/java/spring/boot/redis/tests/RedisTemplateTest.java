package spring.boot.redis.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.redis.module.User;

import javax.annotation.Resource;

/**
 * Author: vincent
 * Date: 2020-11-20 11:31
 * Comment:
 */

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
