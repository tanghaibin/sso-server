package com.biz.sso.controller;

import com.biz.sso.auth.AuthenticationHandler;
import com.biz.sso.bean.*;
import com.biz.sso.constant.Constant;
import com.biz.sso.exception.AuthenticationException;
import com.biz.sso.service.TGTService;
import com.biz.sso.utils.URLUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:16
 **/
@Controller
@RequestMapping("sso")
public class AuthController {

    @Autowired
    private TGTService tgtService;

    @Autowired
    private AuthenticationHandler authenticationHandler;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("loginCheck")
    @ResponseBody
    public JSONResult loginCheck(LoginReqVo loginReqVo, HttpServletRequest request, HttpServletResponse response) {
        AuthToken authToken = new UsernamePasswordAuthToken(loginReqVo.getUsername(), loginReqVo.getPassword());
        try {
            HandlerResult authentication = authenticationHandler.authentication(authToken);
            //todo TGT加密 代码优化
            Cookie cookie = new Cookie(Constant.TGT_COOKIE_NAME, authentication.getTicket());
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            return new JSONResult();
        } catch (AuthenticationException ex) {
            return new JSONResult(ex.getCode(), ex.getMessage());
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(Constant.TGT_COOKIE_NAME, null);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);
        tgtService.remove(WebUtils.getCookie(request, Constant.TGT_COOKIE_NAME).getName());
        return "redirect:login";
    }

    @GetMapping("autoLogin")
    public String autoLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie ticketCookie = WebUtils.getCookie(request, Constant.TGT_COOKIE_NAME);
        if(ticketCookie == null || !tgtService.validTGT(ticketCookie.getValue())) {
            return "redirect:login";
        } else {
            final String redirectURL = request.getParameter(Constant.SSO_REDIRECT_KEY);
            if(StringUtils.isBlank(redirectURL)) {
                request.getSession().setAttribute(Constant.SSO_REDIRECT_KEY, redirectURL);
                return "redirect:login";
            } else {
                Map<String, String> params = new HashMap<>(1);
                params.put(Constant.TGT_COOKIE_NAME, ticketCookie.getValue());
                return "redirect:" + URLUtils.buildRedirectUrl(redirectURL, params);
            }
        }
    }
}
