package com.chatbot.onboarding.application.service;


import com.chatbot.onboarding.domain.chatbot.Chatbot;
import com.chatbot.onboarding.domain.knowledge.Knowledge;
import com.chatbot.onboarding.domain.knowledge.repository.KnowledgeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeService {

            private final KnowledgeRepository knowledgeRepository;
            private Chatbot chatbot;

            public KnowledgeService(KnowledgeRepository knowledgeRepository, Chatbot chatbot) {
                this.knowledgeRepository = knowledgeRepository;
                this.chatbot = chatbot;
            }

            public String getResponse(String question) {
                return chatbot.responseTo(question);
            }

            public Knowledge save(Knowledge knowledge) {
                return knowledgeRepository.save(knowledge);
            }

            public List<Knowledge> findAll() {
                return knowledgeRepository.findAll();
            }

            public Knowledge findById(Long id) {
                return knowledgeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Knowledge with id " + id + " not found"));
            }

            public void deletedById(Long id) {
                Knowledge existing = knowledgeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Knowledge with id " + id + " not found"));

                knowledgeRepository.deletedById(id);

            }

            public Optional<Knowledge> findByQuestion(String question) {
                return knowledgeRepository.findByQuestion(question);
            }


}
