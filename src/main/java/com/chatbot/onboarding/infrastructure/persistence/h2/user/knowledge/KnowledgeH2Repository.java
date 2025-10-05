package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;


import com.chatbot.onboarding.domain.knowledge.Knowledge;
import com.chatbot.onboarding.domain.knowledge.repository.KnowledgeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class KnowledgeH2Repository implements KnowledgeRepository {

            private final KnowledgeEntityRepository knowledgeEntityRepository;

            public KnowledgeH2Repository(KnowledgeEntityRepository knowledgeEntityRepository) {
                this.knowledgeEntityRepository = knowledgeEntityRepository;
            }

            @Override
            public Knowledge save(Knowledge knowledge) {
                KnowledgeEntity knowledgeEntity = KnowledgeEntityMapper.toEntity(knowledge);

                return KnowledgeEntityMapper.toDomain(knowledgeEntityRepository.save(knowledgeEntity));

            }

            @Override
            public Optional<Knowledge> findById(Long id) {
                return knowledgeEntityRepository.findById(id).map(KnowledgeEntityMapper::toDomain);
            }

            @Override
            public List<Knowledge> findAll(){
                return knowledgeEntityRepository.findAll().stream().map(KnowledgeEntityMapper::toDomain).collect(Collectors.toList());

            }

            @Override
            public void deletedById(Long id){
                knowledgeEntityRepository.deleteById(id);
            }

            @Override
            public Optional<Knowledge> findByQuestion(String question) {
                return knowledgeEntityRepository.findByQuestion(question).map(KnowledgeEntityMapper::toDomain);
            }


}
