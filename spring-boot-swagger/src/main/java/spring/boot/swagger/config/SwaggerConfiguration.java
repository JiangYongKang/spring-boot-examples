package spring.boot.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("基于 Swagger 构建的 Rest API 文档")
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
