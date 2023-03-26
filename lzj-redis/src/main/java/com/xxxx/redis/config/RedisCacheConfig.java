package com.xxxx.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public abstract class RedisCacheConfig {
    // 重写 RedisTemplate 序列化
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 为 String 类型 key 设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 为 String 类型 value 设置序列化器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 为 Hash 类型的 key 设置序列化器为 StringRedisSerializer
        template.setHashKeySerializer(new StringRedisSerializer());
        // 为 Hash 类型的 value 设置序列化器为 GenericJackson2JsonRedisSerializer
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    // 重写 Cache 序列化
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 设置 Redis 缓存的配置信息
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置默认超时时间为 30 分钟
                .entryTtl(Duration.ofMinutes(30L))
                // 不缓存空数据
                .disableCachingNullValues()
                // 设置 key 的序列化器为 StringRedisSerializer
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置 value 的序列化器为 GenericJackson2JsonRedisSerializer
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        // 创建 Redis 缓存管理器
        return RedisCacheManager
                // 设置 Redis 缓存写入方式为非锁定方式
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                // 设置 Redis 缓存配置信息
                .cacheDefaults(configuration)
                .build();
    }
}
