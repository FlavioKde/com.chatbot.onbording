package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeEntityRepository extends JpaRepository<KnowledgeEntity,Long> {
}
