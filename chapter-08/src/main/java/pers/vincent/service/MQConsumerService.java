package pers.vincent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MQConsumerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MQConsumerService.class);

    private String topicText;

    private String queueText;

    @JmsListener(destination = "SAMPLE.QUEUE")
    public void receivedQueue(String text) {
        this.queueText = text;
        LOGGER.info("Received queue " + text);
    }

    @JmsListener(destination = "SAMPLE.TOPIC")
    public void receivedTopic(String text) {
        this.topicText = text;
        LOGGER.info("Received topic " + text);
    }

    public String getTopicText() {
        return topicText;
    }

    public String getQueueText() {
        return queueText;
    }
}
