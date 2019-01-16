package com.vincent.springboot.configuration.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Author: vincent
 * Date: 2019-01-16 16:03:00
 * Comment:
 */

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationApplication.class, args);
    }

}
