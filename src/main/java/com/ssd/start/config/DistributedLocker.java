package com.ssd.start.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author WHD
 * @date 2020/8/28 16:12
 */
@Component
public class DistributedLocker {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String luaScript = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /**
     *
     * @param key
     * @param value
     * @param timeout 设置锁的失效时间
     * @param unit
     * @return
     */
    public boolean lock(String key, String value, long timeout, TimeUnit unit){
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    public Object unlock(String key, String value){
        RedisScript<String> redisScript = RedisScript.of(luaScript, Long.class);
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(key), value);
        return result;
    }

}
