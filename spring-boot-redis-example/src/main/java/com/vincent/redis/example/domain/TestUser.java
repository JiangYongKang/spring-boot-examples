package com.vincent.redis.example.domain;

import lombok.Data;

import java.io.Serializable;

@Data
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
}
