package com.biz.sso.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 认证返回结果
 *
 * @author haibin.tang
 * @create 2018-03-15 下午2:34
 **/
@Data
public class HandlerResult implements Serializable{

    private String id;

    private String ticket;

    public HandlerResult(String id, String ticket) {
        this.id = id;
        this.ticket = ticket;
    }
}
