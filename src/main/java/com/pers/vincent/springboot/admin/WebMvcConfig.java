package com.pers.vincent.springboot.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义 WebMvc 配置
 * 如果 Spring Boot 提供的 Spring MVC 不符合要求，则可以通过一个配置类（注解有 @Configuration 的类）
 * 加上 @EnableWebMvc 注解来实现完全自己控制的 MVC 配置。
 * 当然，通常情况下，Spring Boot 的自动配置是符合我们大多数需求的。
 * 在你既需要保留 Spring Boot 提供的便利，有需要增加自己的额外的配置的时候，
 * 可以定义一个配置类并继承 WebMvcConfigurerAdapter，无需使用 @EnableWebMvc 注解
 * <p>
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/8
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 静态资源映射器
     * 如果想自定义静态资源映射目录，必须重写该方法，以下写法为默认值。
     * addResourceHandler() 对外暴露的访问路径
     * addResourceLocations() 存放资源的目录，可以为项目中的目录，也可以是本机下的任意目录。
     * 当然也可以传入参数为自定义的路径，例如 "/vincent/**", "classpath:/vincent/"
     * 通过 addResourceHandler("/**") 添加映射路径，然后通过 addResourceLocations("classpath:/") 指定路径
     * 也可以指定外部路径，如：addResourceLocations("file:/Users/vincent/Script/")
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
        super.addResourceHandlers(registry);
    }

    /**
     * 页面跳转控制器
     * 以前访问一个页面，需要写一个 Controller，然后再写一个方法跳转到页面，其实大可不必，我们重写该方法即可达到效果。
     * 这里重写了该方法，并不会覆盖父类的此方法，我们自己定义的配置和 Spring Boot 定义的配置将会同事存在，大可放心重写。
     * 在父类的方法中，Spring Boot 将 "/" 映射至 "index.html"
     * 即访问 http://127.0.0.1:8080/ 会自动跳转至 http://127.0.0.1:8080/index.html
     *
     * @param registry registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /**
         * 访问 http://127.0.0.1:8080/to/index，会直接跳转至 index.html。
         * 不需要再浪费笔墨写一个仅仅用于页面跳转的方法
         */
        registry.addViewController("/to/index").setViewName("index");
        super.addViewControllers(registry);
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * addPathPatterns() 添加拦截规则，"/**" 指的是拦截全部请求。
         * excludePathPatterns() 排除的规则，"/to/login", "/login/do" 指的是排除了 /to/login 和 /login/do 请求。
         */
        registry.addInterceptor(new HandlerInterceptorConfig()).addPathPatterns("/**").excludePathPatterns("/to/login", "/login/do");
        super.addInterceptors(registry);
    }
}
