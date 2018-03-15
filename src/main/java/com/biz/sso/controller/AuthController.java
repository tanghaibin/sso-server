package com.biz.sso.controller;

import com.biz.sso.auth.AuthenticationHandler;
import com.biz.sso.bean.*;
import com.biz.sso.constant.Constant;
import com.biz.sso.exception.AuthenticationException;
import com.biz.sso.service.TGTService;
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
}
