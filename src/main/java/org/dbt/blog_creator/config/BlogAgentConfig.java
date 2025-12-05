package org.dbt.blog_creator.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class BlogAgentConfig {

    @Value("${blog.prompt:classpath:blog-prompt}")
    Resource prompt;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem(prompt)
                .build();
    }
}
