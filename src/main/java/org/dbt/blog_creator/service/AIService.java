package org.dbt.blog_creator.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    @Autowired
    ChatClient chatClient;

    public String generateBlog(String topic, int size) {
        return chatClient.prompt()
                .user(u -> {
                    u.text("Create a high-quality technical blog post on the topic: {topic}. Keep the post {size} words maximum.");
                    u.param("topic", topic);
                    u.param("size", size);
                })
                .call()
                .content();
    }
}
