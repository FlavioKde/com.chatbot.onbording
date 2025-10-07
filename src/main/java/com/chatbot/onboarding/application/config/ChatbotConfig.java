package com.chatbot.onboarding.application.config;


import com.chatbot.onboarding.domain.chatbot.Chatbot;
import com.chatbot.onboarding.domain.chatbot.repository.KnowledgeFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatbotConfig {

        @Bean
    public Chatbot chatbot(KnowledgeFinder knowledgeFinder) {
            return new Chatbot(knowledgeFinder);
        }

}
