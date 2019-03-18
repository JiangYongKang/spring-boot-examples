package com.vincent.example.controller;

import com.vincent.example.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author: vincent
 * date: 2019-03-18 13:41
 * comment:
 */

@RestController
@RequestMapping("/messageTopic")
public class MessageTopicController {

    @Resource
    private MessageService messageService;

    @GetMapping("/{message}")
    public ResponseEntity<?> send(@PathVariable String message) {
        messageService.sendToTopic(message);
        return ResponseEntity.ok(message);
    }

}
