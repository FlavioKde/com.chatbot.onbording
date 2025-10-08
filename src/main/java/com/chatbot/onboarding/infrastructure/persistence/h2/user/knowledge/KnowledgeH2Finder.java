package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;

import com.chatbot.onboarding.domain.chatbot.repository.KnowledgeFinder;
import com.chatbot.onboarding.domain.knowledge.Knowledge;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class KnowledgeH2Finder implements KnowledgeFinder {

        private final KnowledgeEntityRepository knowledgeEntityRepository;

        public KnowledgeH2Finder(KnowledgeEntityRepository knowledgeEntityRepository) {
            this.knowledgeEntityRepository = knowledgeEntityRepository;
        }

        @Override
        public List<Knowledge>findByQuestionContainingIgnoreCase(String keyword) {

            String[] keywords = keyword.split("\\s+");

            return knowledgeEntityRepository.findAll().stream()
                    .filter(k -> {
                        String question = k.getQuestion().toLowerCase();
                        return Arrays.stream(keywords)
                                .allMatch(word -> question.contains(word.toLowerCase()));
                    })
                    .map(KnowledgeEntityMapper::toDomain)
                    .collect(Collectors.toList());
        }
}
