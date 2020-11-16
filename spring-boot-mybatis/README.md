# SpringBoot（四）：使用 MyBatis 访问数据库

**MyBatis** 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。它避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。因为 MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs (Plain Old Java Objects，普通的 Java 对象)映射成数据库中的记录。

### 编写表结构

因为 MyBatis 是无法像 JPA 那样根据 Java 代码自动生成表结构的，所以这里我们需要手动创建。创建 `resources/db/schema.sql` 文件，填写以下内容

```mysql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id`         bigint(20) AUTO_INCREMENT NOT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
```

### 引入依赖

MyBatis 的依赖文件并不在 SpringBoot 的自动依赖关系的配置中，所以这里需要手动指定版本号：

```xml
<dependencys>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.3</version>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>
</dependencys>
```

### 配置数据源

`schema` 和 `initialization-mode` 两个选项将自动读取并执行 `resources/db/schema.sql` 文件中的内容。

```yml
spring:
  datasource:
    url: jdbc:h2:mem:test;MODE=MYSQL
    driver-class-name: org.h2.Driver
    schema: classpath:db/schema.sql
    initialization-mode: always
```

### 编写查询接口

首先创建一个实体类 `User`，它的属性应该是和数据库中的字段对应的。

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
}
```

创建查询接口 `UserMapper` 和对应的 `resources/mybatis/user_mapper.xml`

```java
public interface UserMapper {
    List<User> findByFirstName(@Param("firstName") String firstName);
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="spring.boot.mybatis.mapper.UserMapper">
    <resultMap id="User" type="spring.boot.mybatis.module.User">
        <id column="id" property="id" javaType="java.lang.Long" jdbcType="INTEGER"/>
        <result column="first_name" property="firstName" javaType="java.lang.String" jdbcType="VARCHAR"/>
        <result column="last_name" property="lastName" javaType="java.lang.String" jdbcType="VARCHAR"/>
    </resultMap>
    <select id="findByFirstName" resultMap="User">
        select *
        from user
        where first_name = #{firstName}
    </select>
</mapper>
```

添加配置，指定 `mapper.xml` 所在路径

```yml
mybatis:
  mapper-locations: classpath:mybatis/*.xml
```

在启动类上添加注解，扫描 `mapper` 接口

```java
@MapperScan("spring.boot.mybatis.*")
```

虽然 MyBatis 设计之初是一个 XML 驱动的 ORM 框架，其配置信息都是基于 XML 的，但是从 MyBatis3 开始它基于强大的 Java 语言的配置 API 之上，支持使用注解来配置 SQL 以及查询结果与实体之间的映射关系。那么我们下面就来了解一下如何使用注解来使用 MyBatis。

```java
// 插入一条用户数据
@Insert("insert into user(id, first_name, last_name) values(#{id}, #{firstName}, #{lastName})")
int save(User user);

// 查询全部用户数据
@Select("select * from user")
@Results({
        @Result(property = "firstName", column = "first_name", javaType = String.class),
        @Result(property = "lastName", column = "last_name", javaType = String.class),
})
List<User> findAll();
```

创建一个 `CommandLineRunner` 的实现类来测试一下查询接口：

```java
@Slf4j
@Component
public class UserRunner implements CommandLineRunner {
    @Resource
    private UserMapper userMapper;
    @Override
    public void run(String... args) {
        int changeRow = userMapper.save(new User(1L, "Vincent", "Jiang"));
        log.info("Change rows: {}", changeRow);
        log.info("User: {}", userMapper.findAll());
        log.info("User: {}", userMapper.findByFirstName("Vincent"));
    }
}
```

### 参考文档

- [https://juejin.im/post/6844904176149856264](https://juejin.im/post/6844904176149856264)
- [https://developer.ibm.com/zh/tutorials/j-spring-boot-integrate-with-mybatis/](https://developer.ibm.com/zh/tutorials/j-spring-boot-integrate-with-mybatis/)
