package com.pers.vincent.springboot.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.util.Slowlog;

import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/4/10
 * Comment: Redis 服务器信息动态监控配置测试
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisInfoUtilTest {

    @Before
    public void setUp() {

    }

    @Test
    public void redisInfoTest() {
        String result = RedisInfoUtil.redisInfo();
        Assert.assertNotNull(result);
    }

    @Test
    public void logsTest() {
        List<Slowlog> results = RedisInfoUtil.logs(2);
        Assert.assertNotNull(results);
    }

    @Test
    public void logSizeTest() {
        Long result = RedisInfoUtil.logSize();
        Assert.assertNotNull(result);
    }

    @Test
    public void logEmptyTest() {
        String result = RedisInfoUtil.logEmpty();
        Assert.assertNotNull(result);
    }

    @Test
    public void databaseSizeTest() {
        Long result = RedisInfoUtil.databaseSize();
        Assert.assertNotNull(result);
    }
}
