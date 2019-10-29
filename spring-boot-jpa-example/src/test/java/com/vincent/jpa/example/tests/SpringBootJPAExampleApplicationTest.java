package com.vincent.jpa.example.tests;

import com.vincent.jpa.example.domain.User;
import com.vincent.jpa.example.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: vincent
 * Date: 2019/10/15 6:50 下午
 * Comment:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootJPAExampleApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        userRepository.save(new User("AAA", 18));
        userRepository.save(new User("BBB", 19));
        userRepository.save(new User("CCC", 20));
        userRepository.save(new User("DDD", 21));
        userRepository.save(new User("EEE", 22));
        Assert.assertEquals(5, userRepository.count());
        Assert.assertEquals(19, userRepository.findByName("BBB").getAge().longValue());
    }
}
