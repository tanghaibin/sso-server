package com.biz.sso.constant;

/**
 * 请求返回码
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:50
 **/
public enum HttpStatus {

    OK(200, "处理成功"),

    NOT_PERMISSION(401, "无权限"),

    SERVER_ERROR(500, "服务器错误"),

    PARAM_ERROR(405, "参数异常");

    private int code;

    private String desc;

    HttpStatus(int code, String desc) {
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
