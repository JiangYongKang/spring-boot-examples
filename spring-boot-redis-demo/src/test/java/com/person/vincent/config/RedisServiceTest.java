package com.person.vincent.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import com.person.vincent.domain.TestUser;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Resource
    private HashOperations<String, String, Object> hashOperations;

    @Resource
    private ListOperations<String, Object> listOperations;

    @Resource
    private SetOperations<String, Object> setOperations;

    @Before
    public void doBefore() {
    }

    @Test
    public void hashOperationsTest() {
        hashOperations.delete("HASH.TEST", "USER1", "USER2", "USER3");

        hashOperations.put("HASH.TEST", "USER1", new TestUser("AAA", 1));
        hashOperations.put("HASH.TEST", "USER2", new TestUser("BBB", 2));
        hashOperations.put("HASH.TEST", "USER3", new TestUser("CCC", 3));

        Assert.assertEquals(3, hashOperations.size("HASH.TEST").longValue());
    }

    @Test
    public void listOperationsTest() {
        listOperations.leftPush("LIST.TEST", new TestUser("AAA", 1));
        listOperations.leftPush("LIST.TEST", new TestUser("BBB", 2));
        listOperations.leftPush("LIST.TEST", new TestUser("CCC", 3));

        Assert.assertEquals(3, listOperations.size("LIST.TEST").longValue());
    }

    @Test
    public void setHashOperationsTest() {
        setOperations.add("SET.TEST", new TestUser("AAA", 1));
        setOperations.add("SET.TEST", new TestUser("BBB", 2));
        setOperations.add("SET.TEST", new TestUser("CCC", 3));

        Assert.assertEquals(3, setOperations.size("SET.TEST").longValue());
    }
}
