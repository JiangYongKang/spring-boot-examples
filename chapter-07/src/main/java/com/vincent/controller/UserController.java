package com.vincent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/19
 * Comment:
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {




    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String userList(String name) {
        return "hello aop";
    }
}
