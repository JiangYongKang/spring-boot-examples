package com.vincent.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * author: vincent
 * date: 2019-03-18 11:44
 * comment:
 */

@Component
public class QueueListener {

    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @JmsListener(destination = "defaultQueue", containerFactory = "queueListenerContainerFactory")
    public void receiveMessageQueue(String text) {
        logger.info(text);
    }

}
