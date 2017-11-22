package com.person.vincent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/1
 * Comment: 多配置文件下的内容读取
 */
@Component
public class ProFileConfig {

    @Value("${com.vincent.properties.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
