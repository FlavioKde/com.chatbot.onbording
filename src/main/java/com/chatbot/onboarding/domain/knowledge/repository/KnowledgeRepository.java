package com.chatbot.onboarding.domain.knowledge.repository;

import com.chatbot.onboarding.domain.knowledge.Knowledge;

import java.util.List;
import java.util.Optional;

public interface KnowledgeRepository {

        Knowledge save(Knowledge knowledge);
        Optional<Knowledge> findById(Long id);
        List<Knowledge> findAll();
        void deletedById(Long id);

}
