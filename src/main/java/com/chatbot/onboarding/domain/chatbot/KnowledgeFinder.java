package com.chatbot.onboarding.domain.chatbot;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KnowledgeFinder {

        Optional<String> findAnswerFor(String question);
}
