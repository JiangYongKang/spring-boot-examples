package com.vincent.api.example.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SpringBootApiExampleApplicationTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void messagesControllerTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/messages")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/messages")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"nickName\":\"vincent\",\"content\":\"富强\"}")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("")))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/messages")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nickName").value("vincent"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("富强"))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/messages/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("")))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/messages")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());
    }

}
