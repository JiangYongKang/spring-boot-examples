package com.vincent.exception.example.controller;

import com.vincent.exception.example.config.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: vincent
 * Date: 2019/9/1 4:50 上午
 * Comment:
 */

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @GetMapping
    public ResponseEntity<?> index() {
        throw new BaseException();
    }

}
