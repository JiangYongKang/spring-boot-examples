package com.person.vincent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class GlobalExceptionDemoApplication {

    @RequestMapping("/global/error")
    public String error() throws Exception {
        throw new Exception("这是一个测试异常消息");
    }

    @RequestMapping("/global/error/json")
    public String json() throws GlobalException {
        throw new GlobalException("发生错误");
    }

    public static void main(String[] args) {
        SpringApplication.run(GlobalExceptionDemoApplication.class, args);
    }
}
