package com.person.vincent.repository;

import com.person.vincent.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.person.vincent.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void doBefore() {
        userRepository.deleteAll();
    }

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
