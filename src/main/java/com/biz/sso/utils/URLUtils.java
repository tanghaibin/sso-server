package com.biz.sso.utils;

import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * 辅助URL生成
 *
 * @author haibin.tang
 * @create 2018-03-15 下午6:13
 **/
public class URLUtils {

    public static String buildRedirectUrl(String url, Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            return url;
        }
        StringBuilder gotoUrl = new StringBuilder(url);

        if (url.indexOf("?") > 0) {
            gotoUrl.append("&");
        } else {
            gotoUrl.append("?");
        }
        for (String key : params.keySet()) {
            gotoUrl.append(key + "=" + params.get(key) + "&");
        }
        return gotoUrl.toString();
    }
}
