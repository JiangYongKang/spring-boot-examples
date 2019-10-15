package com.vincent.configuration.example.tests;

import com.vincent.configuration.example.WeChatProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Author: vincent
 * Date: 2019/8/29 3:33 下午
 * Comment:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigurationExampleApplicationTests {

    @Resource
    private WeChatProperties weChatProperties;

    @Test
    public void configurationPropertiesTest() {
        Assert.assertNotNull(weChatProperties.getAppId());
        Assert.assertNotNull(weChatProperties.getSecretKey());
    }
}
