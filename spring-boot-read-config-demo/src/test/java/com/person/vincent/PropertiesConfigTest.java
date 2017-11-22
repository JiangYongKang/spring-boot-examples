package com.person.vincent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesConfigTest {

    @Resource
    private PropertiesConfig propertiesConfig;

    @Test
    public void contextLoads() {
        Assert.assertEquals("vincent", propertiesConfig.getName());
        Assert.assertEquals(25L, propertiesConfig.getAge());
        Assert.assertEquals("vincent, 25", propertiesConfig.getDesc());
    }

}
