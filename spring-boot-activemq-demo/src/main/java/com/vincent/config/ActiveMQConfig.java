package com.vincent.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.concurrent.Executors;

/**
 * author: vincent
 * date: 2019-03-18 11:31
 * comment:
 */

@EnableJms
@Configuration
public class ActiveMQConfig {

    @Bean
    public Queue defaultQueue() {
        return new ActiveMQQueue("defaultQueue");
    }

    @Bean
    public Topic defaultTopic() {
        return new ActiveMQTopic("defaultTopic");
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        defaultJmsListenerContainerFactory.setConcurrency("10");
        defaultJmsListenerContainerFactory.setTaskExecutor(Executors.newFixedThreadPool(10));
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setPubSubDomain(false);
        defaultJmsListenerContainerFactory.setConcurrency("10");
        defaultJmsListenerContainerFactory.setTaskExecutor(Executors.newFixedThreadPool(10));
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;
    }

}
