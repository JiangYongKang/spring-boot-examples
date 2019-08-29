package com.vincent.example.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * author: vincent
 * date: 2019-03-25 15:34
 * comment:
 */

@Data
@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 1, columnDefinition = "tinyint default 0")
    private Integer state;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public Message(String nickName, String content) {
        this.nickName = nickName;
        this.content = content;
    }
}
