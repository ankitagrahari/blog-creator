package org.dbt.blog_creator.controller;

import org.dbt.blog_creator.service.AIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogCreatorController.class)
class BlogCreatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AIService aiService;

    @Test
    void testCreateBlog_Success() throws Exception {
        String topic = "Java";
        String expectedResponse = "Java Blog";

        when(aiService.generateBlog(topic, 500)).thenReturn(expectedResponse);

        mockMvc.perform(get("/blog-creator/create").param("topic", topic).param("size", "500"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void testCreateBlog_MissingParam() throws Exception {
        mockMvc.perform(get("/blog-creator/create"))
                .andExpect(status().isBadRequest());
    }
}
