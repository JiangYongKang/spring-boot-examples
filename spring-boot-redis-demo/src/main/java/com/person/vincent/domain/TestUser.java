package com.person.vincent.domain;

import java.io.Serializable;

public class TestUser implements Serializable {
    private final static long serialVersionUID = -1L;

    private String name;
    private Integer age;

    public TestUser() {
    }

    public TestUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
