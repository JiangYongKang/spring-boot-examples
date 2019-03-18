package com.vincent.example.controller;

import com.vincent.example.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * author: vincent
 * date: 2019-03-18 22:46
 * comment: 延时队列
 */

@RestController
@RequestMapping("/messageDelayQueue")
public class MessageDelayQueueController {

    @Resource
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> send(@RequestBody Map<String, String> body) {
        messageService.sendToQueue(body.get("message"), Long.valueOf(body.get("delay")));
        return ResponseEntity.ok(body);
    }

}
