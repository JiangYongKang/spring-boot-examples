package com.vincent.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * author: vincent
 * date: 2019-03-18 11:44
 * comment: 队列消息的消费者
 */

@Component
public class QueueListener {

    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    /**
     * 监听 defaultQueue 队列的消息
     * @param text 消息内容
     */
    @JmsListener(destination = "defaultQueue", containerFactory = "queueListenerContainerFactory")
    public void receiveMessageQueue(String text) {
        logger.info(text);
    }

}
