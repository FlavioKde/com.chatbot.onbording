package com.chatbot.onboarding.shared.mapper;

import com.chatbot.onboarding.application.dto.KnowledgeCreateDto;
import com.chatbot.onboarding.application.dto.KnowledgeDTO;
import com.chatbot.onboarding.domain.knowledge.Knowledge;

public class KnowledgeMapper {

    public static KnowledgeDTO toDto(Knowledge knowledge) {
        KnowledgeDTO dto = new KnowledgeDTO();

        dto.setId(knowledge.getId());
        dto.setQuestion(knowledge.getQuestion());
        dto.setAnswer(knowledge.getAnswer());

        return dto;

    }

    public static Knowledge toEntity(KnowledgeCreateDto knowledgeCreateDto) {
        Knowledge knowledge = new Knowledge();

        knowledge.setQuestion(knowledgeCreateDto.getQuestion());
        knowledge.setAnswer(knowledgeCreateDto.getAnswer());

        return knowledge;
    }
}
