package spring.boot.mybatis.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.boot.mybatis.mapper.UserMapper;
import spring.boot.mybatis.module.User;

import javax.annotation.Resource;

/**
 * Author: vincent
 * Date: 2020-11-13 18:13
 * Comment:
 */

@Slf4j
@Component
public class UserRunner implements CommandLineRunner {

    @Resource
    private UserMapper userMapper;

    @Override
    public void run(String... args) {
        int changeRow = userMapper.save(new User(1L, "Vincent", "Jiang"));
        log.info("Change rows: {}", changeRow);
        log.info("User: {}", userMapper.findAll());
        log.info("User: {}", userMapper.findByFirstName("Vincent"));
    }
}
