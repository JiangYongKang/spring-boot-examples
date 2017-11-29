package com.person.vincent.controller;

import com.person.vincent.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testRestfulAPI() throws Exception {
        RequestBuilder request = null;
        String id = UUID.randomUUID().toString();

        request = MockMvcRequestBuilders.get("/users/");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        request = MockMvcRequestBuilders.post("/users/")
                .param("id", id)
                .param("name", "vincent")
                .param("age", "25");
        mockMvc.perform(request).andExpect(content().string(equalTo("success")));

        request = MockMvcRequestBuilders.get("/users/");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":\"" + id + "\",\"name\":\"vincent\",\"age\":25}]")));

        request = MockMvcRequestBuilders.put("/users/" + id)
                .param("name", "vincent")
                .param("age", "30");
        mockMvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        request = MockMvcRequestBuilders.get("/users/" + id);
        mockMvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":\"" + id + "\",\"name\":\"vincent\",\"age\":30}")));

        request = MockMvcRequestBuilders.delete("/users/" + id);
        mockMvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        request = MockMvcRequestBuilders.get("/users/");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }

}
