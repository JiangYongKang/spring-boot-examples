package com.pers.vincent.springboot;

import com.pers.vincent.springboot.admin.PropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SpringBoot 项目启动类
 * <p>
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/8
 *
 * @SpringBootApplication 启动类的注解
 * @RestController restful 控制层注解，相当于 @Controller + @ResponseBody，该类下的所有接口都将返回 JSON 格式的数据
 * @EnableConfigurationProperties 加载配置文件管理的
 * @RequestMapping 请求地址映射
 */

@SpringBootApplication
@RestController
@EnableConfigurationProperties({PropertiesConfig.class})
@RequestMapping(value = "/admin")
public class LearnApplication {

    @Resource
    private PropertiesConfig propertiesConfig;

    /**
     * 请求配置文件的相关属性接口
     *
     * @return 配置文件相关属性
     */
    @RequestMapping(value = "/properties")
    public Object getProperties() {
        return propertiesConfig.toString();
    }

    /**
     * 启动类
     * SpringBoot 内置了 Tomcat 容器，所以可以通过一个简单的主方法来启动应用
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }
}
