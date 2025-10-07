package com.chatbot.onboarding.domain.chatbot.repository;

import com.chatbot.onboarding.domain.knowledge.Knowledge;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnowledgeFinder {

        Optional<String> findAnswerFor(String question);
        List<Knowledge> findByQuestionContainingIgnoreCase(String keyword);
}
