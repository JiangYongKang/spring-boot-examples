package com.vincent.datasource.example.domain.primary;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class PrimaryUserInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    public PrimaryUserInfo() {
    }

    public PrimaryUserInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
