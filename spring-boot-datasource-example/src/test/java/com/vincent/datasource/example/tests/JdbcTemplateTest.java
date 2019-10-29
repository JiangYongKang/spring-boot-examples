package com.vincent.datasource.example.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateTest {

    private final static String INS_USER_SQL = "INSERT INTO `user_info`(`name`, `age`) VALUES (?, ?)";
    private final static String SEL_USER_COUNT_SQL = "SELECT COUNT(*) FROM `user_info`";
    private final static String DEL_USER_SQL = "DELETE FROM `user_info`";

    @Autowired
    @Qualifier(value = "primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier(value = "secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void test() {
        primaryJdbcTemplate.update(INS_USER_SQL, "AAA", 18);
        primaryJdbcTemplate.update(INS_USER_SQL, "BBB", 19);
        primaryJdbcTemplate.update(INS_USER_SQL, "CCC", 20);
        Assert.assertEquals(3, primaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());

        secondaryJdbcTemplate.update(INS_USER_SQL, "DDD", 21);
        secondaryJdbcTemplate.update(INS_USER_SQL, "EEE", 22);
        secondaryJdbcTemplate.update(INS_USER_SQL, "FFF", 23);
        Assert.assertEquals(3, secondaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());

        primaryJdbcTemplate.update(DEL_USER_SQL);
        Assert.assertEquals(0, primaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());

        secondaryJdbcTemplate.update(DEL_USER_SQL);
        Assert.assertEquals(0, secondaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());
    }

}
