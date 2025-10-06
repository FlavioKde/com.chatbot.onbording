package com.chatbot.onboarding.infrastructure.controller;


import com.chatbot.onboarding.application.dto.KnowledgeCreateDto;
import com.chatbot.onboarding.application.dto.KnowledgeDTO;
import com.chatbot.onboarding.application.service.KnowledgeService;
import com.chatbot.onboarding.config.ApiConfig;
import com.chatbot.onboarding.domain.knowledge.Knowledge;
import com.chatbot.onboarding.shared.mapper.KnowledgeMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH)
public class KnowledgeController {

        private final KnowledgeService knowledgeService;

        public KnowledgeController(KnowledgeService knowledgeService) {
            this.knowledgeService = knowledgeService;
        }

        @PostMapping("/knowledge")
        public ResponseEntity<KnowledgeDTO>create(@Valid @RequestBody KnowledgeCreateDto knowledgeCreateDto){
            Knowledge knowledge = KnowledgeMapper.toEntity(knowledgeCreateDto);

            Knowledge savedKnowledge = knowledgeService.save(knowledge);

            return ResponseEntity.ok(KnowledgeMapper.toDto(savedKnowledge));

        }

        @PostMapping("/ask")
        public ResponseEntity<String>ask(@RequestParam String question){
            String response = knowledgeService.getResponse(question);

            return ResponseEntity.ok(response);
        }
}
