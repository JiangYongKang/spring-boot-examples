package com.vincent.example.service;

import com.vincent.example.model.Message;
import com.vincent.example.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: vincent
 * date: 2019-03-25 16:22
 * comment:
 */

@Slf4j
@Service
public class MessageService {

    @Resource
    private MessageRepository messageRepository;

    public List<Message> selectAll() {
        return messageRepository.findAll();
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public void delete(Integer id) {
        messageRepository.delete(id);
    }

}
