package com.vincent.example.service.impl;

import com.vincent.example.service.MessageService;
import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * author: vincent
 * date: 2019-03-18 11:23
 * comment: 消息生产者，通过 JmsMessagingTemplate 向不同的队列投放消息
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private Queue defaultQueue;

    @Resource
    private Topic defaultTopic;

    @Resource
    private JmsTemplate template;

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

    @Override
    public void sendToQueue(String message, Long delay) {
        template.send(this.defaultQueue, session -> {
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
            return textMessage;
        });
    }

}
