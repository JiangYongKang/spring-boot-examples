package com.person.vincent.domain;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
