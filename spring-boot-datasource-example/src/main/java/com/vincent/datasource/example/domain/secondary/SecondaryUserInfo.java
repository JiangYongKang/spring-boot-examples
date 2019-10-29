package com.vincent.datasource.example.domain.secondary;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class SecondaryUserInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    public SecondaryUserInfo() {
    }

    public SecondaryUserInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
