package com.pers.vincent.springboot.admin.base;

import com.pers.vincent.springboot.admin.domain.SysUserModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IDEA.
 * User: e
 * Date: 2017/4/10
 * Comment: 数据层基类接口测试
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseRepositoryTest {

    @Resource
    private BaseRepository<SysUserModel, String> baseRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findByStatusTest() {
        List<SysUserModel> results = baseRepository.findByStatus(0);
        Assert.assertNotNull(results);
        Assert.assertEquals(100, results.size());
    }
}
