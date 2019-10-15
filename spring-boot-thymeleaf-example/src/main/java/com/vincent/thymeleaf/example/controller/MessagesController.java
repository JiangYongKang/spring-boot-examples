package com.vincent.thymeleaf.example.controller;

import com.vincent.thymeleaf.example.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * Author: vincent
 * Date: 2019/8/31 4:58 下午
 * Comment:
 */

@Controller
@RequestMapping("/messages")
public class MessagesController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/messages/index");
        List<Message> messages = Arrays.asList(
                new Message("vincent", "富强"),
                new Message("vincent", "民主"),
                new Message("vincent", "和谐")
        );
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

}
