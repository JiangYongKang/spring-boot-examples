package com.pers.vincent.springboot.admin.repository;

import com.pers.vincent.springboot.admin.domain.SysUserModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/4/10
 * Comment: 系统用户数据层接口测试类
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SysUserRepositoryTest {

    @Resource
    private SysUserRepository sysUserRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findAllTest() {
        List<SysUserModel> result = sysUserRepository.findAll();
        Assert.assertNotNull(result);
    }

    @Test
    public void findByStatus() {
        List<SysUserModel> results = sysUserRepository.findByStatus(0);
        Assert.assertNotNull(results);
        Assert.assertEquals(100, results.size());
    }
}
