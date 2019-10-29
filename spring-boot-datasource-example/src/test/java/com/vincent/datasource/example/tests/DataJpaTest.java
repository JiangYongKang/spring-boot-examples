package com.vincent.datasource.example.tests;

import com.vincent.datasource.example.domain.primary.PrimaryUserInfo;
import com.vincent.datasource.example.domain.primary.PrimaryUserRepository;
import com.vincent.datasource.example.domain.secondary.SecondaryUserInfo;
import com.vincent.datasource.example.domain.secondary.SecondaryUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJpaTest {

    @Autowired
    private PrimaryUserRepository primaryUserRepository;

    @Autowired
    private SecondaryUserRepository secondaryUserRepository;

    @Test
    public void test() throws Exception {
        primaryUserRepository.save(new PrimaryUserInfo("AAA", 1));
        primaryUserRepository.save(new PrimaryUserInfo("BBB", 2));
        primaryUserRepository.save(new PrimaryUserInfo("CCC", 3));
        primaryUserRepository.save(new PrimaryUserInfo("DDD", 4));
        primaryUserRepository.save(new PrimaryUserInfo("EEE", 5));
        Assert.assertEquals(5, primaryUserRepository.count());

        secondaryUserRepository.save(new SecondaryUserInfo("AAA", 1));
        secondaryUserRepository.save(new SecondaryUserInfo("BBB", 2));
        secondaryUserRepository.save(new SecondaryUserInfo("CCC", 3));
        Assert.assertEquals(3, secondaryUserRepository.count());
    }

}
