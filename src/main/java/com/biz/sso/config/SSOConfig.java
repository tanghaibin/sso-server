package com.biz.sso.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置sso系统信息
 *
 * @author haibin.tang
 * @create 2018-03-16 下午5:01
 **/
@Component
@Data
@NoArgsConstructor
public class SSOConfig {
    /**
     * 登陆成功跳转地址
     */
    @Value("${login.success.url}")
    private String loginSuccessUrl;

    /**
     * 登陆地址
     */
    @Value("${login.url}")
    private String loginUrl;

    /**
     * cookie写入域
     */
    @Value("${cookie.domain}")
    private String cookieDomain;

    /**
     * cookie写入的资源路径
     */
    @Value("${cookie.path}")
    private String cookiePath;


}
