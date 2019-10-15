package com.vincent.thymeleaf.example.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Random;

/**
 * author: vincent
 * date: 2019-03-25 15:34
 * comment:
 */

@Data
public class Message implements Serializable {

    private Integer id;

    private String nickName;

    private String content;

    private Integer state;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Message(String nickName, String content) {
        this.id = new Random().nextInt();
        this.nickName = nickName;
        this.content = content;
        this.state = 1;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
