package com.vincent.swagger.example.controller;

import com.vincent.swagger.example.model.Message;
import com.vincent.swagger.example.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: vincent
 * Date: 2019/8/29 4:53 下午
 * Comment:
 */

@Api("消息接口")
@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Resource
    private MessageService messageService;

    @GetMapping
    @ApiOperation("消息列表")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(messageService.selectAll());
    }

    @PostMapping
    @ApiOperation("发送消息")
    public ResponseEntity<?> create(@RequestBody Message message) {
        messageService.save(message);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除消息")
    public ResponseEntity<?> delete(@PathVariable("id") @ApiParam(value = "消息ID", defaultValue = "0") Integer id) {
        messageService.delete(id);
        return ResponseEntity.ok("");
    }

}
