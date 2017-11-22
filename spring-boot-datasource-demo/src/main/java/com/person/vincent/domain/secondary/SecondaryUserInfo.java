package com.person.vincent.domain.secondary;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
