package com.beitu.saas.common.utils;

import com.fqgj.base.services.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author xiaochong
 * @create 2018/4/8 下午10:12
 * @description
 */
@Component
public class SaaSRedisClient extends RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;

    public void hPut(String keyFormat, String hashKey, Long value, String... keyValues) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        redisTemplate.setHashValueSerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        String key = format(keyFormat, keyValues);
        redisTemplate.opsForHash().put(key, hashKey, String.valueOf(value));
    }

    public Map<String, String> hNumGetAll(String keyFormat, String... keyValues) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        redisTemplate.setHashValueSerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        String key = format(keyFormat, keyValues);
        Map<String, String> result = redisTemplate.opsForHash().entries(key);
        return result;
    }
}
