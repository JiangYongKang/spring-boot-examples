package com.vincent.springboot.configuration.example.test;

import com.vincent.springboot.configuration.example.config.ProjectMetaInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Author: vincent
 * Date: 2019-01-16 16:25:00
 * Comment:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootConfigurationApplicationTest {

    @Resource
    private ProjectMetaInfo metaInfo;

    @Test
    public void notEmptyTests() {
        Assert.assertEquals(metaInfo.name(), "spring-boot-configuration-demo");
        Assert.assertEquals(metaInfo.author(), "vincent");
        Assert.assertEquals(metaInfo.version(), "1.0.0");
        Assert.assertEquals(metaInfo.license(), "MIT");
    }

}
