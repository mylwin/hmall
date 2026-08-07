package com.hmall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试接口
 * <p>按客户端 IP 统计访问次数，用于验证服务可用性。</p>
 */
@RestController
@RequestMapping("hi")
public class HelloController {
    /**
     * 客户端 IP → 访问次数
     */
    private final Map<String, AtomicInteger> countMap = new HashMap<>();

    /**
     * 返回欢迎信息，并统计该 IP 的访问次数
     *
     * @param request 请求，用于获取客户端 IP
     * @return 欢迎信息
     */
    @GetMapping
    public String hello(HttpServletRequest request) throws InterruptedException {
        Thread.sleep(300);
        String ip = request.getRemoteAddr();
        AtomicInteger ai = countMap.get(ip);
        if (ai == null) {
            ai = new AtomicInteger(0);
            countMap.put(ip, ai);
        }
        return String.format("<h5>欢迎访问黑马商城, 这是您第%d次访问<h5>", ai.incrementAndGet());
    }
}
