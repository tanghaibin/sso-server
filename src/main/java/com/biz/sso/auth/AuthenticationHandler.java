package com.biz.sso.auth;

import com.biz.sso.bean.AuthToken;
import com.biz.sso.bean.HandlerResult;

/**
 * 认证处理器
 *
 * @author haibin.tang
 * @create 2018-03-15 下午2:32
 **/
public interface AuthenticationHandler {

    HandlerResult authentication(AuthToken authToken);
}
