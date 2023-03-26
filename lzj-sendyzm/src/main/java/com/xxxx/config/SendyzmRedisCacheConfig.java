package com.xxxx.config;

import com.xxxx.redis.config.RedisCacheConfig;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class SendyzmRedisCacheConfig extends RedisCacheConfig {

    @Bean
    @Override
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return super.redisTemplate(redisConnectionFactory);
    }

    @Bean
    @Override
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return super.cacheManager(redisConnectionFactory);
    }
}
