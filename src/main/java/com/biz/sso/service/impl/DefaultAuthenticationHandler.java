package com.biz.sso.service.impl;

import com.biz.sso.auth.AuthenticationHandler;
import com.biz.sso.bean.AuthToken;
import com.biz.sso.bean.HandlerResult;
import com.biz.sso.bean.UsernamePasswordAuthToken;
import com.biz.sso.constant.AuthenticationErrorCode;
import com.biz.sso.exception.UserNotMatchException;
import com.biz.sso.service.TGTService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * 默认身份认证处理器
 *
 * @author haibin.tang
 * @create 2018-03-15 下午2:42
 **/
public class DefaultAuthenticationHandler implements AuthenticationHandler {

    private static Map<String, String> users = new HashMap<>();

    @Autowired
    private TGTService tgtService;

    static {
        InputStream in = DefaultAuthenticationHandler.class.getClassLoader().getResourceAsStream("/auth/system.properties");
        Properties user = new Properties();
        try {
            user.load(in);
            users.put(user.getProperty("username"), user.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HandlerResult authentication(AuthToken authToken) {
        UsernamePasswordAuthToken usernamePasswordAuthToken = (UsernamePasswordAuthToken) authToken;
        if(users.containsKey(((UsernamePasswordAuthToken) authToken).getUsername()) && Objects.equals(((UsernamePasswordAuthToken) authToken).getPassword(), users.get(usernamePasswordAuthToken.getUsername()))) {
            return createHandlerResult(usernamePasswordAuthToken.getUsername());
        }
        throw new UserNotMatchException("用户名或密码错误", AuthenticationErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }

    private HandlerResult createHandlerResult(String id) {
        HandlerResult result = new HandlerResult(id, tgtService.createTGT());
        tgtService.save(result.getTicket(), id);
        return result;
    }
}
