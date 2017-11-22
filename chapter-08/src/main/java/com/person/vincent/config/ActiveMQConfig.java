package com.person.vincent.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

// ActiveMQ 配置类
@EnableJms
@Configuration
public class ActiveMQConfig {

    @Resource
    private JmsTemplate jmsTemplate;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("SAMPLE.QUEUE");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("SAMPLE.TOPIC");
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate () {
        return new JmsMessagingTemplate(jmsTemplate);
    }
}
