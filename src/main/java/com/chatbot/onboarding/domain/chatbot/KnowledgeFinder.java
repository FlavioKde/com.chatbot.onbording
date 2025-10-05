package com.chatbot.onboarding.domain.chatbot;

import java.util.Optional;

public interface KnowledgeFinder {

        Optional<String> findAnswerFor(String question);
}
