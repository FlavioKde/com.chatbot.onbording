package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;

import com.chatbot.onboarding.domain.knowledge.Knowledge;
import org.springframework.data.repository.CrudRepository;

public interface KnowledgeRepository extends CrudRepository<Knowledge,Long> {
}
