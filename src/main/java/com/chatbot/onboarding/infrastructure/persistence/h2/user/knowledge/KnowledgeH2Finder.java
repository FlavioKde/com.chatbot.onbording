package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;

import com.chatbot.onboarding.domain.chatbot.KnowledgeFinder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class KnowledgeH2Finder implements KnowledgeFinder {

        private final KnowledgeEntityRepository knowledgeEntityRepository;

        public KnowledgeH2Finder(KnowledgeEntityRepository knowledgeEntityRepository) {
            this.knowledgeEntityRepository = knowledgeEntityRepository;
        }

        @Override
    public Optional<String> findAnswerFor(String question) {
            return knowledgeEntityRepository.findByQuestion(question)
                    .map(KnowledgeEntity::getAnswer);
        }
}
