package com.vincent.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * author: vincent
 * date: 2019-03-18 13:49
 * comment:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageTopicControllerTest {

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void beforeAction() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void sendTest() throws Exception {
        String message = "Apache ActiveMQ";
        mockMvc.perform(
                request(HttpMethod.GET, "/messageTopic/" + message)
        ).andExpect(status().isOk()).andExpect(jsonPath("$").value(message));

    }
}
