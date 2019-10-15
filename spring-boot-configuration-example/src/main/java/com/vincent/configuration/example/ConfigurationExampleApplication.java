package com.vincent.configuration.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ConfigurationExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigurationExampleApplication.class, args);
    }
}
