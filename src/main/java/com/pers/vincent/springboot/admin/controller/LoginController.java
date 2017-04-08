package com.pers.vincent.springboot.admin.controller;

import com.pers.vincent.springboot.demo.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关逻辑控制器
 * <p>
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/8
 */
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登录
     *
     * @return object
     */
    @GetMapping(value = "/to/login")
    public Object toLogin() {
        logger.info("com.pers.vincent.springboot.admin.controller ==> toLogin()");
        return new Result(200, "Please", null);
    }

    /**
     * 执行登录
     *
     * @param username 账号
     * @param password 密码
     * @param request request
     * @return object
     */
    @GetMapping(value = "/login/do")
    public Object loginDo(String username, String password, HttpServletRequest request) {
        if (username != null && password != null) {
            Map<String, Object> user = new HashMap<>();
            user.put("username", username);
            user.put("password", password);
            request.getSession().setAttribute("user", user);
            return new Result(200, "success", user);
        }
        return new Result(500, "error", null);
    }
}
