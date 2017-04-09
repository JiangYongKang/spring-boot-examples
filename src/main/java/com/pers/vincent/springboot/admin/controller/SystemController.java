package com.pers.vincent.springboot.admin.controller;

import com.pers.vincent.springboot.admin.repository.SysUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/9
 */
@RestController
@RequestMapping(value = "/system")
public class SystemController {

    @Resource
    private SysUserRepository sysUserRepository;

    @GetMapping(value = "/user/list")
    public Object users() {
        return sysUserRepository.findAll();
    }
}
