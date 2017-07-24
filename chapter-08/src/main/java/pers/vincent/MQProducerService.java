package pers.vincent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;

@Service
public class MQProducerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MQProducerService.class);

    @Resource
    private JmsMessagingTemplate template;

    @Resource
    private Queue queue;

    public void send(String message) {
        template.convertAndSend(queue, message);
        LOGGER.info(message);
    }
}
