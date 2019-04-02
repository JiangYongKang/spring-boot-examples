package com.vincent.cache.example.repository;

import com.vincent.cache.example.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author: vincent
 * date: 2019-03-25 15:38
 * comment:
 */

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
