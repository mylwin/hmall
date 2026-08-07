package com.hmall.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * cookie工具类
 */
@Slf4j
@Data
@Accessors(chain = true, fluent = true)
public class CookieBuilder {
    /**
     * 编码方式
     */
    private Charset charset = StandardCharsets.UTF_8;
    /**
     * cookie有效期，单位秒。-1表示永久有效
     */
    private int maxAge = -1;
    /**
     * cookie路径
     */
    private String path = "/";
    /**
     * 是否httpOnly
     */
    private boolean httpOnly;
    /**
     * cookie名
     */
    private String name;
    /**
     * cookie值
     */
    private String value;
    /**
     * cookie域名
     */
    private String domain;
    /**
     * request
     */
    private final HttpServletRequest request;
    /**
     * response
     */
    private final HttpServletResponse response;

    /**
     * 使用 request 与 response 构造 Cookie 构建器
     *
     * @param request  请求，用于读取域名，可为 null
     * @param response 响应，用于写入 Cookie，为 null 时 build() 不执行写入
     */
    public CookieBuilder(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * 构建cookie，会对cookie值用UTF-8做URL编码，避免中文乱码
     */
    public void build(){
        if (response == null) {
            log.error("response为null，无法写入cookie");
            return;
        }
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, charset));
        if(StrUtil.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }else if (request != null) {
            String serverName = request.getServerName();
            serverName = StrUtil.subAfter(serverName, ".", false);
            cookie.setDomain("." + serverName);
        }
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        log.debug("生成cookie，编码方式:{}，【{}={}，domain:{};maxAge={};path={};httpOnly={}】",
                charset.name(), name, value, domain, maxAge, path, httpOnly);
        response.addCookie(cookie);
    }

    /**
     * 利用UTF-8对cookie值解码，避免中文乱码问题
     * @param cookieValue cookie原始值
     * @return 解码后的值
     */
    public String decode(String cookieValue){
        return URLDecoder.decode(cookieValue, charset);
    }
}
