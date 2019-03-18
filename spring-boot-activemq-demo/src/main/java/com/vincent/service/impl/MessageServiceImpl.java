package com.vincent.service.impl;

import com.vincent.service.MessageService;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * author: vincent
 * date: 2019-03-18 11:23
 * comment:
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private Queue defaultQueue;

    @Resource
    private Topic defaultTopic;

    @Resource
    private JmsMessagingTemplate messagingTemplate;

    @Override
    public void sendToQueue(String message) {
        messagingTemplate.convertAndSend(this.defaultQueue, message);
    }

    @Override
    public void sendToTopic(String message) {
        messagingTemplate.convertAndSend(this.defaultTopic, message);
    }

}
