package com.biz.sso.service;

/**
 * 主票据服务
 *
 * @author haibin.tang
 * @create 2018-03-15 下午3:22
 **/
public interface TGTService {

    String createTGT();

    boolean validTGT(String ticket);

    boolean remove(String ticket);
}
