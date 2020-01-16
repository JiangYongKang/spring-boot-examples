package com.vincent.mybatisplus.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.mybatisplus.example.mapper.MemberUserMapper;
import com.vincent.mybatisplus.example.model.MemberUser;
import com.vincent.mybatisplus.example.service.MemberUserService;
import org.springframework.stereotype.Service;

/**
 * Author: vincent
 * Date: 2020/1/16 1:35 下午
 * Comment:
 */

@Service
public class MemberUserServiceImpl extends ServiceImpl<MemberUserMapper, MemberUser> implements MemberUserService {
}
