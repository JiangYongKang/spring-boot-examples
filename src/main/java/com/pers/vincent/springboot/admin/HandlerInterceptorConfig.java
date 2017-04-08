package com.pers.vincent.springboot.admin;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器配置
 * 创建自定义拦截器类，实现 HandlerInterceptor 接口，重写接口中的全部方法。
 * 需要在自定义的 WebMvc 配置类中重写 addInterceptors() 方法。
 * <p>
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/8
 */
public class HandlerInterceptorConfig implements HandlerInterceptor {

    /**
     * 拦截请求，获取并判断 session 中是否存在 "user" 对象。
     * 如果 session 中不存在 "user" 对象，会跳转到重定向到 "/to/login"。
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return 是否登录，登录返回 true，未登录返回 false
     * @throws Exception exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isSignIn = false;
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/to/login");
        } else {
            isSignIn = true;
        }
        return isSignIn;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
