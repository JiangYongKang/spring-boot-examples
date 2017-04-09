### SpringBoot 学习项目

#### 工程目录结构

```java
spring-boot-learn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.pers.vincent.springboot
│   │   │       ├── admin
│   │   │       │   ├── controller
│   │   │       │   │   ├── LoginController.java
│   │   │       │   │   └── SystemController.java
│   │   │       │   ├── domain
│   │   │       │   │   └── SysUserModel.java
│   │   │       │   ├── repository
│   │   │       │   │   └── SysUserRepository.java
│   │   │       │   ├── service
│   │   │       │   ├── HandlerInterceptorConfig.java
│   │   │       │   ├── PropertiesConfig.jaba
│   │   │       │   ├── RedisCacheConfig.java
│   │   │       │   └── WebMvcConfig.java
│   │   │       ├── demo
│   │   │       │   ├── controller
│   │   │       │   │   └── Controller.java
│   │   │       │   ├── domain
│   │   │       │   │   └── Result.java
│   │   │       │   ├── repository
│   │   │       │   └── service
│   │   │       └── LearnApplication.java
│   │   └── resources
│   │       ├── META-INF.resources
│   │       │   └── index.html
│   │       ├── public
│   │       ├── resources
│   │       ├── static
│   │       ├── templates
│   │       │   ├── demo
│   │       │   │   └── result.html
│   │       │   └── index.html
│   │       └── application.properties
│   └── test
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml
```

#### 技术选型
|名称|版本|官网|
|--|--|--|
|IntelliJ IDEA|2016.3.6|[https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)|
|Java|1.8.0_111|[https://www.java.com/zh_CN/](https://www.java.com/zh_CN/)|
|Maven|3.3.9|[http://maven.apache.org/](http://maven.apache.org/)|
|MySQL|5.7.17|[https://www.mysql.com/](https://www.mysql.com/)|
|Redis|3.2.8|[https://redis.io/](https://redis.io/)|
|Spring Boot|1.5.3|[http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/)|
