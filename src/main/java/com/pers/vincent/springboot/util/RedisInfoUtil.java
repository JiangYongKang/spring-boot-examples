package com.pers.vincent.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/4/10
 * Comment: Redis 服务器信息
 */
public class RedisInfoUtil {

    private static final Jedis JEDIS = new JedisPool().getResource();
    private static final Client CLIENT = JEDIS.getClient();

    /**
     * 获取 redis 服务器信息
     *
     * @return redis 服务器信息
     */
    public static String redisInfo() {
        CLIENT.info();
        return CLIENT.getBulkReply();
    }

    /**
     * 获取日志列表
     *
     * @param entries 日志级别
     * @return 日志列表
     */
    public static List<Slowlog> logs(long entries) {
        return JEDIS.slowlogGet(entries);
    }

    /**
     * 获取日志统计条数
     *
     * @return 日志统计条数
     */
    public static Long logSize() {
        return JEDIS.slowlogLen();
    }

    /**
     * 清空日志
     *
     * @return 清空日志
     */
    public static String logEmpty() {
        return JEDIS.slowlogReset();
    }

    /**
     * 占用内存大小
     *
     * @return 内存大小
     */
    public static Long databaseSize() {
        CLIENT.dbSize();
        return CLIENT.getIntegerReply();
    }
}
