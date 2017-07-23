package pers.vincent.chapter08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/23
 * Comment: 消费者
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    @Override
    @JmsListener(destination = "QUEUE")
    public String receiveQueue(String text) {
        logger.info("消费者接收到一条队列主题消息: " + text);
        return text;
    }

    @Override
    @JmsListener(destination = "TOPIC")
    public String receiveTopic(String text) {
        logger.info("消费者接收到一条主题消息: " + text);
        return text;
    }


}
