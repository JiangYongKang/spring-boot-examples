package com.vincent.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * author: vincent
 * date: 2019-03-18 10:30
 * comment: 主题消息的消费者类
 */

@Component
public class TopicListener {

    private static final Logger logger = LoggerFactory.getLogger(TopicListener.class);

    /**
     * 订阅 defaultTopic 的消息
     * @param message
     */
    @JmsListener(destination = "defaultTopic", containerFactory = "topicListenerContainerFactory")
    public void receiveMessageTopicOne(String message) {
        logger.info(message);
    }

    /**
     * 订阅 defaultTopic 的消息
     * @param message
     */
    @JmsListener(destination = "defaultTopic", containerFactory = "topicListenerContainerFactory")
    public void receiveMessageTopicTwo(String message) {
        logger.info(message);
    }


}
