package pers.vincent.chapter08;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/23
 * Comment: ActiveMQ 相关 Bean 配置类
 */
@Component
public class ActiveMQConfig {

    @Resource
    private ActiveMQConnectionFactory connectionFactory;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("SAMPLE QUEUE");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTempTopic("SAMPLE TOPIC");
    }

    @Bean
    public JmsMessagingTemplate messagingTemplate() {
        return new JmsMessagingTemplate(connectionFactory);
    }
}
