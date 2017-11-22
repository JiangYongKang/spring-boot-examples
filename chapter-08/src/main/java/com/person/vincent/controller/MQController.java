package com.person.vincent.controller;

import com.person.vincent.service.MQProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.person.vincent.service.MQConsumerService;

import javax.annotation.Resource;

@Controller
public class MQController {

    @Resource
    private MQProducerService producerService;

    @Resource
    private MQConsumerService consumerService;

    @ResponseBody
    @RequestMapping(value = "/send/queue")
    public String sendQueue(@RequestParam String message) {
        producerService.sendQueueMessage(message);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/send/topic")
    public String sendTopic(@RequestParam String message) {
        producerService.sendTopicMessage(message);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/receive/queue")
    public String receiveQueue() {
        return consumerService.getQueueText();
    }

    @ResponseBody
    @RequestMapping(value = "/receive/topic")
    public String receiveTopic() {
        return consumerService.getTopicText();
    }
}
