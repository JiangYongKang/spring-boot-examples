package com.person.vincent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/1
 * Comment:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProFileTest {

    @Resource
    private ProFileConfig proFileConfig;

    @Test
    public void contextLoads(){
        Assert.assertNotNull(proFileConfig.getName());
        Assert.assertEquals("test", proFileConfig.getName());
    }
}
