package com.vincent.api.example.controller;

import com.vincent.api.example.model.Message;
import com.vincent.api.example.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: vincent
 * Date: 2019/8/29 4:53 下午
 * Comment:
 */

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Resource
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(messageService.selectAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Message message) {
        messageService.save(message);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        messageService.delete(id);
        return ResponseEntity.ok("");
    }

}
