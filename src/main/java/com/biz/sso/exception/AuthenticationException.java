package com.biz.sso.exception;

import com.biz.sso.constant.AuthenticationErrorCode;

/**
 * 认证失败顶级异常
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:01
 **/
public class AuthenticationException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "认证失败";

    private int code;

    public AuthenticationException(AuthenticationErrorCode authenticationErrorCode) {
        super(authenticationErrorCode.getDesc());
        this.code = authenticationErrorCode.getCode();
    }

    public AuthenticationException(String message, AuthenticationErrorCode authenticationErrorCode) {
        super(message);
        this.code = authenticationErrorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
