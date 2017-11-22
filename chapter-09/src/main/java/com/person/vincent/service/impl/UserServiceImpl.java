package com.person.vincent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.person.vincent.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("INSERT INTO `user` (`name` , `age`) VALUES (? ,?)", name, age);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("DELETE FROM `user` WHERE `name` = ?", name);
    }

    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `user`", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("DELETE FROM `user`");
    }
}
