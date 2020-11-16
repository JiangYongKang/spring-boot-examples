package spring.boot.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import spring.boot.mybatis.module.User;

import java.util.List;

/**
 * Author: vincent
 * Date: 2020-11-13 18:11
 * Comment:
 */

public interface UserMapper {

    @Insert("insert into user(id, first_name, last_name) values(#{id}, #{firstName}, #{lastName})")
    int save(User user);

    @Select("select * from user")
    @Results({
            @Result(property = "firstName", column = "first_name", javaType = String.class),
            @Result(property = "lastName", column = "last_name", javaType = String.class),
    })
    List<User> findAll();

    List<User> findByFirstName(@Param("firstName") String firstName);

}
