package com.vincent.jpa.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class User {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.name = name;
        this.age = age;
    }
}
