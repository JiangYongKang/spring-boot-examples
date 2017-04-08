package com.pers.vincent.springboot.demo.controller;

import com.pers.vincent.springboot.demo.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制器
 * <p>
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/8
 */
@RestController
@RequestMapping(value = "/demo")
public class Controller {

    /**
     * 回显数据
     *
     * @param code    状态码
     * @param message 消息
     * @return 包含数据和视图路径的对象
     */
    @GetMapping(value = "/result")
    public ModelAndView result(int code, String message) {
        ModelAndView mv = new ModelAndView("/demo/result");
        mv.addObject("result", new Result(code, message, new Object()));
        return mv;
    }
}
