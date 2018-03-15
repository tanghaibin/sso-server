package com.biz.sso.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户名密码认证身份标识
 *
 * @author haibin.tang
 * @create 2018-03-15 下午2:40
 **/
@Data
public class UsernamePasswordAuthToken extends AuthToken implements Serializable {

    private String username;

    private String password;

    public UsernamePasswordAuthToken(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
