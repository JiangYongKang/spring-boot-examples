package com.vincent.mybatisplus.example.model;

import lombok.Data;

/**
 * Author: vincent
 * Date: 2020/1/16 1:33 下午
 * Comment:
 */

@Data
public class MemberUser {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
