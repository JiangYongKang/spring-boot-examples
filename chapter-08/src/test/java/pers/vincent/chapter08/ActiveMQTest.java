package pers.vincent.chapter08;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQTest {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMQTest.class);

    @Resource
    private ProducerService producerService;

    @Test
    public void activeMQQueueTest() {
        Destination destination = new ActiveMQQueue("SAMPLE QUEUE");
        for (int i = 1; i <= 10; i++) {
            producerService.sendMessage(destination, "来自生产者的第 " + i + " 队列主题消息条消息");
            logger.info("生产者第 " + i + " 条队列主题消息已发送");
        }
    }

    @Test
    public void activeMQTopicTest() {
        Destination destination = new ActiveMQTempTopic("SAMPLE TOPIC");
        for (int i = 1; i <= 10; i++) {
            producerService.sendMessage(destination, "来自生产者的第 " + i + " 队列主题消息条消息");
            logger.info("生产者第 " + i + " 条队列主题消息已发送");
        }
    }

}
