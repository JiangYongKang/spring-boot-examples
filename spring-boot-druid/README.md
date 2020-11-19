# SpringBoot（五）：使用数据库连接池 Druid

**Druid** 与 C3P0，DBCP 一样，都是数据库连接池。不同的地方是 Druid 还提供了强大的监控和扩展功能，本文主要介绍如何在 SpringBoot 项目中级成 Druid。

### 添加依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.3</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

### 配置数据源

```yml
spring:
  datasource:
    url: jdbc:h2:mem:test;MODE=MYSQL
    driver-class-name: org.h2.Driver
    druid:
      filters: stat,wall,log4j2
      web-stat-filter:
        enabled: true
        url-pattern: /*
        exclusions: "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*
        reset-enable: false
        login-username: admin
        login-password: admin
        allow: 127.0.0.1
```

打开浏览器，访问 [http://127.0.0.1:8080/druid](http://127.0.0.1:8080/druid) 账号密码是配置文件中设置的 `admin`

### 参考文档

- [https://www.liaoxuefeng.com/wiki/1252599548343744/1321748528103458](https://www.liaoxuefeng.com/wiki/1252599548343744/1321748528103458)
- [https://juejin.cn/post/6844903583129796621](https://juejin.cn/post/6844903583129796621)
- [http://www.machengyu.net/tech/2020/02/16/database-connection-pool.html](http://www.machengyu.net/tech/2020/02/16/database-connection-pool.html)
- [https://fengberlin.github.io/post/db-connector-pool/](https://fengberlin.github.io/post/db-connector-pool/)
- [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
