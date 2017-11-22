package com.person.vincent;

import com.person.vincent.domain.secondary.SecondaryUserInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.person.vincent.domain.primary.PrimaryUserInfo;
import com.person.vincent.domain.primary.PrimaryUserRepository;
import com.person.vincent.domain.secondary.SecondaryUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJpaTest {

    @Autowired
    private PrimaryUserRepository primaryUserRepository;

    @Autowired
    private SecondaryUserRepository secondaryUserRepository;

    @Before
    public void doBefore() {
        primaryUserRepository.deleteAll();
        secondaryUserRepository.deleteAll();
    }

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
