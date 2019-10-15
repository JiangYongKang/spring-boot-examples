package com.vincent.quickstart.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: vincent
 * Date: 2019/8/28 10:30 下午
 * Comment:
 */

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping
    public ResponseEntity<?> index(@RequestParam("name") String name) {
        return ResponseEntity.ok("Hello " + name);
    }

}
