package com.biz.sso.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis支持
 *
 * @author haibin.tang
 * @create 2018-03-16 下午5:50
 **/
@Component
public class RedisSupport {

    @Autowired
    private RedisTemplate redisTemplate;

    public String get(String key) {
        Object value = redisTemplate.<String, String>opsForValue().get(key);
        return value == null ? null : value.toString();
    }

    public void set(String key, String value) {
        assert StringUtils.isNoneBlank(key, value);
        redisTemplate.opsForValue().set(key, value);
    }

    public boolean del(String key) {
        assert StringUtils.isNotBlank(key);
        return redisTemplate.delete(key);
    }
}
