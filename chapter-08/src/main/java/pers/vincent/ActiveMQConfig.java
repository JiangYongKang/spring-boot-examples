package pers.vincent;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;
import javax.jms.Queue;

// ActiveMQ 配置类
@EnableJms
@Configuration
public class ActiveMQConfig {

    @Resource
    private JmsTemplate jmsTemplate;

    @Bean
    public Queue logQueue() {
        return new ActiveMQQueue("SAMPLE.QUEUE");
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate () {
        return new JmsMessagingTemplate(jmsTemplate);
    }
}
