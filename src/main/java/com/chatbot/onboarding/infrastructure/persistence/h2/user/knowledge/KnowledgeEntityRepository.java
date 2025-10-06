package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KnowledgeEntityRepository extends JpaRepository<KnowledgeEntity,Long> {
    Optional<KnowledgeEntity> findByQuestion(String question);
    List<KnowledgeEntity> findByQuestionContainingIgnoreCase(String question);
}
