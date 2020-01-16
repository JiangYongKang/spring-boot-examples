package com.vincent.mybatisplus.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: vincent
 * Date: 2020/1/16 1:28 下午
 * Comment:
 */

@SpringBootApplication
@MapperScan("com.vincent.mybatisplus.example.mapper")
public class MybatisPlusExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusExampleApplication.class, args);
    }
}
