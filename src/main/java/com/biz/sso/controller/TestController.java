package com.biz.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author haibin.tang
 * @create 2018-03-15 下午5:30
 **/
@Controller
@RequestMapping("test")
public class TestController {

    @GetMapping("index")
    public String index() {
        return "index";
    }
}
