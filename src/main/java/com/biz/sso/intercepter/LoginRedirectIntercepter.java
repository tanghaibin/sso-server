package com.biz.sso.intercepter;

import com.biz.sso.constant.Constant;
import com.biz.sso.service.TGTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 如果已登录，则重定向到默认页面或者指定的页面
 *
 * @author haibin.tang
 * @create 2018-03-15 下午4:19
 **/
public class LoginRedirectIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    private TGTService tgtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie ticketCookie = WebUtils.getCookie(request, Constant.TGT_COOKIE_NAME);
        if(ticketCookie == null) {
            return true;
        }
        if(tgtService.validTGT(ticketCookie.getValue())) {
            response.sendRedirect("/test/index");
            return false;
        }
        return true;
    }
}
