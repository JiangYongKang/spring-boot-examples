package com.vincent.example.config;

import lombok.Data;

/**
 * Author: vincent
 * Date: 2019/8/31 5:56 下午
 * Comment:
 */

@Data
public class ErrorMessage<T> {
    private Integer code;
    private String message;
    private String url;
    private T data;
}
