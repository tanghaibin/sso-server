package com.biz.sso.bean;

import com.biz.sso.constant.HttpStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * Rest接口返回对象
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:47
 **/
@Data
public class JSONResult implements Serializable {

    private int code;

    private String msg;

    private Object data;

    public JSONResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JSONResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JSONResult(Object data) {
        this.data = data;
        this.code = HttpStatus.OK.getCode();
    }

    public JSONResult() {
        this.code = HttpStatus.OK.getCode();
        this.msg = HttpStatus.OK.getDesc();
    }
}
