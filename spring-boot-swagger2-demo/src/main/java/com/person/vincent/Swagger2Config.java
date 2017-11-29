package com.person.vincent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by IDEA.
 * User: e
 * Date: 2017/7/10
 * Comment: Swagger2 的配置类
 * @Configuration 让 Spring 来加载该类配置
 * @EnableSwagger2 启用 Swagger2
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vincent"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
                .title("Spring Boot 中使用 Swagger2 构建 RESTful APIs ")
                .description("接口的简单说明")
                .termsOfServiceUrl("接口说明地址")
                .contact("这里写上作者的大名")
                .version("这里写版本号")
                .build();
    }
}
