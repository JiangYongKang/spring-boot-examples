package com.vincent.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @ApiModelProperty("消息ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("发送人昵称")
    @Column(nullable = false)
    private String nickName;

    @ApiModelProperty("消息内容")
    @Column(nullable = false)
    private String content;

    @ApiModelProperty("消息状态")
    @Column(nullable = false, length = 1, columnDefinition = "tinyint default 0")
    private Integer state;

    @CreationTimestamp
    @ApiModelProperty("消息创建时间")
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @ApiModelProperty("消息更新时间")
    private Timestamp updatedAt;

    public Message(String nickName, String content) {
        this.nickName = nickName;
        this.content = content;
    }
}
