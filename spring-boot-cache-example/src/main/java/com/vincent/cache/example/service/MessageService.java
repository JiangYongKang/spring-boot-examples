package com.vincent.cache.example.service;

import com.vincent.cache.example.model.Message;
import com.vincent.cache.example.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "messages")
public class MessageService {

    @Resource
    private MessageRepository messageRepository;

    @Cacheable
    public List<Message> selectAll() {
        return messageRepository.findAll();
    }

    @Cacheable
    public Message selectOne(Integer id) {
        return messageRepository.findOne(id);
    }

    @CacheEvict
    public void batchInstall(List<Message> messages) {
        messageRepository.save(messages);
    }

    @CacheEvict
    public Message update(Message message) {
        return messageRepository.save(message);
    }

}
