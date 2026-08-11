package com.hmall.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 用户信息拦截器
 * <p>从请求头 {@code user-info} 中读取网关传递的用户 id，保存到 {@link UserContext}（ThreadLocal），
 * 请求结束后清理。网关负责解析 JWT 并注入该请求头，微服务无需自行解析 token。</p>
 */
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    /**
     * 请求处理前，解析并保存用户 id
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.从请求头中获取用户信息
        String userInfo = request.getHeader("user-info");

        // 2.判断用户信息是否存在，解析为用户 id
        if (StrUtil.isNotBlank(userInfo)) {
            try {
                UserContext.setUser(Long.valueOf(userInfo));
            } catch (NumberFormatException e) {
                // 请求头被篡改或格式非法，按未登录处理，不阻断请求
                log.warn("非法的 user-info 请求头: {}", userInfo);
            }
        }

        // 3.放行
        return true;
    }

    /**
     * 请求完成后，清理用户信息，避免线程复用导致串号
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户信息
        UserContext.removeUser();
    }
}
