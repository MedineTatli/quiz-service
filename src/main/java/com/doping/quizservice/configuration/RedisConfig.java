package com.doping.quizservice.configuration;

import com.doping.quizservice.properties.RedisProperties;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.doping.quizservice.contstant.CacheConstants.*;

@Configuration
@AllArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisProperties.getRedisHost());
        jedisConnectionFactory.setPort(redisProperties.getRedisPort());
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .withInitialCacheConfigurations(constructInitialCacheConfigurations())
                .build();
    }

    private Map<String, RedisCacheConfiguration> constructInitialCacheConfigurations() {
        final Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        final RedisCacheConfiguration allQuizCache = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith(ALL_QUIZ_CACHE_PREFIX)
                .entryTtl(Duration.ofHours(1))
                .disableCachingNullValues();
        final RedisCacheConfiguration quizCacheById = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith(QUIZ_CACHE_BY_ID_PREFIX)
                .entryTtl(Duration.ofHours(1))
                .disableCachingNullValues();
        final RedisCacheConfiguration allStudentCache = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith(ALL_STUDENT_CACHE_PREFIX)
                .entryTtl(Duration.ofHours(1))
                .disableCachingNullValues();
        redisCacheConfigurationMap.put(ALL_QUIZ_CACHE, allQuizCache);
        redisCacheConfigurationMap.put(QUIZ_CACHE_BY_ID, quizCacheById);
        redisCacheConfigurationMap.put(ALL_STUDENT_CACHE, allStudentCache);
        return redisCacheConfigurationMap;

    }

}
