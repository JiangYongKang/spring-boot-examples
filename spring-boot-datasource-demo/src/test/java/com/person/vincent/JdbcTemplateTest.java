package com.person.vincent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateTest {

    private final static String INS_USER_SQL = "INSERT INTO `user`(`id`, `name`, `age`) VALUES (?, ?, ?)";
    private final static String SEL_USER_COUNT_SQL = "SELECT COUNT(*) FROM `user`";
    private final static String DEL_USER_SQL = "DELETE FROM `user`";

    @Autowired
    @Qualifier(value = "primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier(value = "secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Before
    public void doBefore() {
        primaryJdbcTemplate.update(DEL_USER_SQL);
        secondaryJdbcTemplate.update(DEL_USER_SQL);
    }

    @Test
    public void test() {
        primaryJdbcTemplate.update(INS_USER_SQL, UUID.randomUUID().toString().replace("-",""), "AAA", 18);
        primaryJdbcTemplate.update(INS_USER_SQL, UUID.randomUUID().toString().replace("-",""), "BBB", 19);
        primaryJdbcTemplate.update(INS_USER_SQL, UUID.randomUUID().toString().replace("-",""), "CCC", 20);
        Assert.assertEquals(3, primaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());

        secondaryJdbcTemplate.update(INS_USER_SQL, UUID.randomUUID().toString().replace("-",""), "DDD", 21);
        secondaryJdbcTemplate.update(INS_USER_SQL, UUID.randomUUID().toString().replace("-",""), "EEE", 22);
        secondaryJdbcTemplate.update(INS_USER_SQL, UUID.randomUUID().toString().replace("-",""), "FFF", 23);
        Assert.assertEquals(3, secondaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());

        primaryJdbcTemplate.update(DEL_USER_SQL);
        Assert.assertEquals(0, primaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());

        secondaryJdbcTemplate.update(DEL_USER_SQL);
        Assert.assertEquals(0, secondaryJdbcTemplate.queryForObject(SEL_USER_COUNT_SQL, Long.class).longValue());
    }

}
