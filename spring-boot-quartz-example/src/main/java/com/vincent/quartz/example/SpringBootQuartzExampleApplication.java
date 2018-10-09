package com.vincent.quartz.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@SpringBootApplication
public class SpringBootQuartzExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuartzExampleApplication.class, args);
    }
}
