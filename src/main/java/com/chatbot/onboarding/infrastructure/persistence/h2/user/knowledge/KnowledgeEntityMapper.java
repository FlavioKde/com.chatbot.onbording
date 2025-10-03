package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;

import com.chatbot.onboarding.domain.knowledge.Knowledge;

public class KnowledgeEntityMapper {

    public static Knowledge toDomain(KnowledgeEntity knowledgeEntity) {
            if (knowledgeEntity == null) {
                return null;
            }
            Knowledge knowledge = new Knowledge();
            knowledge.setId(knowledgeEntity.getId());
            knowledge.setQuestion(knowledgeEntity.getQuestion());
            knowledge.setResponse(knowledgeEntity.getResponse());

            return knowledge;
    }

    public static KnowledgeEntity toEntity(Knowledge knowledge) {
        if (knowledge == null) {
            return null;
        }
        KnowledgeEntity knowledgeEntity = new KnowledgeEntity();
        knowledgeEntity.setId(knowledge.getId());
        knowledgeEntity.setQuestion(knowledge.getQuestion());
        knowledgeEntity.setResponse(knowledge.getResponse());

        return knowledgeEntity;
    }
}
