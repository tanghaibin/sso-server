package com.biz.sso.service.impl;

import com.biz.sso.service.TGTService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author haibin.tang
 * @create 2018-03-15 下午3:23
 **/
public class DefualtTGTServiceImpl implements TGTService {

    private static final String PREFIX = "TGT-";

    private Set<String> tickets = new HashSet<>();

    @Override
    public String createTGT() {
        final String ticket = PREFIX + UUID.randomUUID().toString().replace("-", "").toUpperCase();
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public boolean validTGT(String ticket) {
        return tickets.contains(ticket);
    }

    @Override
    public boolean remove(String ticket) {
        return tickets.remove(ticket);
    }
}
