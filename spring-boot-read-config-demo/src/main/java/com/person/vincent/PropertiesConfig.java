package com.person.vincent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/1
 * Comment: 读取配置文件的内容
 */
@Component
public class PropertiesConfig {
    @Value("${com.vincent.name}")
    private String name;

    @Value("${com.vincent.age}")
    private long age;

    @Value("${com.vincent.desc}")
    private String desc;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
