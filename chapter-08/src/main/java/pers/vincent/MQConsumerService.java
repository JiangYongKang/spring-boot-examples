package pers.vincent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MQConsumerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MQConsumerService.class);

    private String text;

    public String receive() {
        return text;
    }

    @JmsListener(destination = "SAMPLE.QUEUE")
    public void receivedQueue(String text) {
        this.text = text;
        LOGGER.info(text);
    }
}
