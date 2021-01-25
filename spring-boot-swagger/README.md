# SpringBoot（七）：SpringBoot 项目中使用 Swagger
`Swagger` 是一套基于 `OpenAPI` 规范构建的开源工具，可以帮助我们设计、构建、记录以及使用 Rest API。Swagger 主要包含了三个部分：`Swagger Editor` 基于浏览器的编辑器，我们可以使用它编写我们 OpenAPI 规范。`Swagger UI` 它会将我们编写的 OpenAPI 规范呈现为交互式的 API 文档，后文我将使用浏览器来查看并且操作我们的 Rest API。`Swagger Codegen` 它可以通过为 OpenAPI（以前称为 Swagger）规范定义的任何 API 生成服务器存根和客户端 SDK 来简化构建过程。

### 添加依赖
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
```

### 配置 Swagger
Springfox 提供了一个 Docket 对象，让我们可以灵活的配置 Swagger 的各项属性。下面我们新建一个 SwaggerConfiguration 类，并增加如下内容:
```java
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("基于Swagger构建的Rest API文档")
                                .description("更多请咨询服务开发者")
                                .contact(
                                        new Contact("vincent", "https://blog.jiangyongkang.com", "vincent4502237@gmail.com")
                                )
                                .termsOfServiceUrl("https://blog.jiangyongkang.com")
                                .version("1.0")
                                .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("spring.boot.swagger"))
                .paths(PathSelectors.any())
                .build();
    }

}
```

### 添加测试接口
```java
@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    @ApiOperation("POST请求")
    public ResponseEntity<?> create(@ApiParam(name = "name", required = true, example = "vincent") @RequestParam("name") String name) {
        return ResponseEntity.ok("success");
    }

}
```
其实就只需要添加一下依赖就可以了，我们重新启动一下项目，然后在浏览器中访问 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 就可以看到结果了
