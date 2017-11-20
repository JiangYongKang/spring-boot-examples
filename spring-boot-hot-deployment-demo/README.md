# Spring boot 热部署
启动 Spring boot，访问 [http://localhost:8080/say?name=vincent](http://localhost:8080/say?name=vincent)。任意修改对应方法的返回值，然后重新 Builder Project(Command + F9 或者点击启动类旁边的按钮)。
刷新页面。

### 依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```