package spring.boot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: vincent
 * Date: 2020-11-13 18:08
 * Comment:
 */

@SpringBootApplication
@MapperScan("spring.boot.mybatis.*")
public class SpringBootMybatis {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatis.class, args);
    }
}
