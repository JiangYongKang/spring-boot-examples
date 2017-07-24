package pers.vincent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MQController {

    @Resource
    private MQProducerService producerService;

    @Resource
    private MQConsumerService consumerService;

    @ResponseBody
    @RequestMapping(value = "/send")
    public String send(@RequestParam String message) {
        producerService.send(message);
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping(value = "/receive")
    public String receive() {
        return consumerService.receive();
    }
}
