package com.chatbot.onboarding.domain.knowledge.repository;

import com.chatbot.onboarding.domain.knowledge.Knowledge;

import java.util.List;

public interface KnowledgeRepository {

        Knowledge save(Knowledge knowledge);
        Knowledge findById(long id);
        List<Knowledge> findAll();
        void delete(Knowledge knowledge);

}
