package com.vincent.cache.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * author: vincent
 * date: 2019-03-25 15:33
 * comment:
 */

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootCacheExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheExampleApplication.class, args);
    }
}
