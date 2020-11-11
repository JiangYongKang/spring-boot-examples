package spring.boot.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Author: vincent
 * Date: 2020-11-11 19:08
 * Comment:
 */

@EnableScheduling
@SpringBootApplication
public class SpringBootScheduling {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduling.class, args);
    }
}
