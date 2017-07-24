package pers.vincent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

@Service
public class MQProducerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MQProducerService.class);

    @Resource
    private JmsMessagingTemplate template;

    @Resource
    private Queue queue;

    @Resource
    private Topic topic;

    public void sendQueueMessage(String message) {
        template.convertAndSend(queue, message);
        LOGGER.info("Queue " + message);
    }

    public void sendTopicMessage(String message) {
        template.convertAndSend(topic, message);
        LOGGER.info("Topic " + message);
    }
}
