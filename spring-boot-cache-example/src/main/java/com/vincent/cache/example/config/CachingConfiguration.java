package com.vincent.cache.example.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * author: vincent
 * date: 2019-03-25 18:29
 * comment:
 */

@Configuration
@EnableCaching
public class CachingConfiguration extends CachingConfigurerSupport {

    /**
     * 默认缓存失效的时间
     */
    private static final Long DEFAULT_EXPIRE = 60L;

    /**
     * 配置 RedisCacheManager 缓存管理器
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(DEFAULT_EXPIRE);
        return cacheManager;
    }

    /**
     * 配置缓存键生成器
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, methodParams) -> {
            StringBuilder builder = new StringBuilder();
            builder.append(target.getClass().getName());
            builder.append('.').append(method.getName());
            String methodParamString = Arrays.stream(methodParams).map(Object::toString)
                    .collect(Collectors.joining(", ", "(", ")"));
            builder.append(methodParamString);
            return builder.toString();
        };
    }
}
