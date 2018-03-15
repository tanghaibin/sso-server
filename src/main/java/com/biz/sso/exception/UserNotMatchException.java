package com.biz.sso.exception;

import com.biz.sso.constant.AuthenticationErrorCode;

/**
 * 用户名或者密码错误异常
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:03
 **/
public class UserNotMatchException extends AuthenticationException {

    public UserNotMatchException(AuthenticationErrorCode authenticationErrorCode) {
        super(authenticationErrorCode);
    }

    public UserNotMatchException(String message, AuthenticationErrorCode authenticationErrorCode) {
        super(message, authenticationErrorCode);
    }
}
