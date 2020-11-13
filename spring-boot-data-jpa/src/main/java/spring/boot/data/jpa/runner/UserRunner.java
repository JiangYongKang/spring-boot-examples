package spring.boot.data.jpa.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.boot.data.jpa.module.User;
import spring.boot.data.jpa.repository.UserRepository;

import javax.annotation.Resource;

/**
 * Author: vincent
 * Date: 2020-11-13 14:58
 * Comment:
 */

@Slf4j
@Component
public class UserRunner implements CommandLineRunner {

    @Resource
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.save(new User(1L, "Vincent", "Jiang"));
        log.info("User: {}", userRepository.findById(1L));
        log.info("User: {}", userRepository.findByFirstName("Vincent"));
        log.info("User: {}", userRepository.findByLastName("Jiang"));
        userRepository.deleteById(1L);
        log.info("User exists: {}", userRepository.existsById(1L));
    }
}
