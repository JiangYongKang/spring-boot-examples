package com.vincent.example.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author: vincent
 * Date: 2019/8/31 5:57 下午
 * Comment:
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private String message;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
