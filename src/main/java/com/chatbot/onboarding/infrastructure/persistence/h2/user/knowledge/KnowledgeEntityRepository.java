package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KnowledgeEntityRepository extends JpaRepository<KnowledgeEntity,Long> {
    Optional<KnowledgeEntity> findByQuestion(String question);
}
