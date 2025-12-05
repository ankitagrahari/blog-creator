package org.dbt.blog_creator.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    @Autowired
    ChatClient chatClient;

    public String generateBlog(String topic) {
        return chatClient.prompt()
                .user(u -> {
                    u.text("Create a high-quality technical blog post on the topic: {topic}");
                    u.param("topic", topic);
                })
                .call()
                .content();
    }
}
