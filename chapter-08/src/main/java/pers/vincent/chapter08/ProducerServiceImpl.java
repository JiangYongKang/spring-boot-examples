package pers.vincent.chapter08;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/23
 * Comment:
 */
@Component
@EnableScheduling
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private JmsMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(Destination destination, final String message) {
        messagingTemplate.convertAndSend(destination, message);
    }
}
