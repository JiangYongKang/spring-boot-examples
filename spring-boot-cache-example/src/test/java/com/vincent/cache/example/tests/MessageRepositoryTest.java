package com.vincent.cache.example.tests;

import com.vincent.cache.example.model.Message;
import com.vincent.cache.example.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * author: vincent
 * date: 2019-03-25 15:42
 * comment:
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageRepositoryTest {

    @Resource
    private MessageService messageService;

    @Before
    public void beforeAction() {
        List<Message> messages = Arrays.asList(
                new Message("Billie", "敬业"),
                new Message("Nathalia", "和谐"),
                new Message("Katharine", "团结"),
                new Message("Perry", "友善"),
                new Message("Howard", "富强")
        );
        messageService.batchInstall(messages);
    }

    @Test
    public void saveTest() {
        messageService.selectAll();
        Message message = messageService.selectOne(1);
        message.setContent("更新消息");
        messageService.update(message);
        messageService.selectOne(2);
        messageService.selectOne(3);
    }

}
