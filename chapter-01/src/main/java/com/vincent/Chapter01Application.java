package com.vincent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController()
public class Chapter01Application {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "Hello World!!!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter01Application.class, args);
    }
}
