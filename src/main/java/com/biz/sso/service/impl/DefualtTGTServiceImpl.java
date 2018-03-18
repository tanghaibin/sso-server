package com.biz.sso.service.impl;

import com.biz.sso.service.TGTService;
import com.biz.sso.support.RedisSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author haibin.tang
 * @create 2018-03-15 下午3:23
 **/
public class DefualtTGTServiceImpl implements TGTService {

    private static final String PREFIX = "TGT-";

    @Autowired
    private RedisSupport redisSupport;

    @Override
    public String createTGT() {
        return PREFIX + UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    @Override
    public boolean validTGT(String ticket) {
        return StringUtils.isNotBlank(redisSupport.get(ticket));
    }

    @Override
    public boolean remove(String ticket) {
        return redisSupport.del(ticket);
    }

    @Override
    public void save(String key, String value) {
        redisSupport.set(key, value);
    }
}
