package com.biz.sso.constant;

/**
 * 认证失败异常描述
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:56
 **/
public enum AuthenticationErrorCode {

    USERNAME_OR_PASSWORD_ERROR(1000, "用户名或密码错误");

    private int code;

    private String desc;

    AuthenticationErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
