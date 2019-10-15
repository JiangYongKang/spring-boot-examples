package com.vincent.quickstart.example.tests;

import com.vincent.quickstart.example.SpringBootQuickStartExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootQuickStartExample.class)
public class SpringBootQuickStartExampleTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testRestfulAPI() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/message?name=vincent");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello vincent")));
    }
}
