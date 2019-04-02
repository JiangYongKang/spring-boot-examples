package com.vincent.cache.example.test;

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
                new Message("敬业"),
                new Message("和谐"),
                new Message("团结"),
                new Message("友善"),
                new Message("富强")
        );
        messageService.batchInstall(messages);
    }

    @Test
    public void saveTest() {
        messageService.selectAll();
        messageService.selectAll();
        messageService.selectAll();
    }

}
