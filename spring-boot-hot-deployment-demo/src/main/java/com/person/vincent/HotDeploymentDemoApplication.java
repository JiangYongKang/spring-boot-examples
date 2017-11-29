package com.person.vincent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class HotDeploymentDemoApplication {

    @ResponseBody
    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say(@RequestParam String name) {
        return "hello " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(HotDeploymentDemoApplication.class, args);
    }
}
