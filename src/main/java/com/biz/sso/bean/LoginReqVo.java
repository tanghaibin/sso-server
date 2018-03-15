package com.biz.sso.bean;

import lombok.Data;

/**
 * 登陆请求vo
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:39
 **/
@Data
public class LoginReqVo {

    private String username;

    private String password;
}
