package pers.vincent.chapter08;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jms.Destination;

@SpringBootApplication
@Controller
public class Application {

    @Resource
    private ProducerService producerService;

    @Resource
    private ConsumerService consumerService;

    private Destination destination = new ActiveMQQueue("SAMPLE QUEUE");

    @RequestMapping(value = "/send/message")
    public void sendMessage(@RequestParam String message) {
        producerService.sendMessage(destination, message);
    }

    @RequestMapping(value = "/receive/queue/message")
    private String receiveQueueMessage() {
        return consumerService.receiveQueue("");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
