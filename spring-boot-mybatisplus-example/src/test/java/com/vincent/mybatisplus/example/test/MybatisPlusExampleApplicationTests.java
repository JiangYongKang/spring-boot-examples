package com.vincent.mybatisplus.example.test;

import com.vincent.mybatisplus.example.mapper.MemberUserMapper;
import com.vincent.mybatisplus.example.model.MemberUser;
import com.vincent.mybatisplus.example.service.MemberUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: vincent
 * Date: 2020/1/16 1:35 下午
 * Comment:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusExampleApplicationTests {

    @Resource
    private MemberUserMapper memberUserMapper;

    @Resource
    private MemberUserService memberUserService;

    @Test
    public void contextLoad() {
        List<MemberUser> memberUsers = memberUserMapper.selectList(null);
        Assert.assertEquals(5, memberUsers.size());
        MemberUser memberUser = memberUserService.lambdaQuery().eq(MemberUser::getName, "Jack").one();
        Assert.assertEquals("Jack", memberUser.getName());
    }
}
