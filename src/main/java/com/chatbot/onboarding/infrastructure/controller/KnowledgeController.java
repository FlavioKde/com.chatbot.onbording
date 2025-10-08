package com.chatbot.onboarding.infrastructure.controller;


import com.chatbot.onboarding.application.dto.KnowledgeCreateDto;
import com.chatbot.onboarding.application.dto.KnowledgeDTO;
import com.chatbot.onboarding.application.service.KnowledgeService;
import com.chatbot.onboarding.config.ApiConfig;
import com.chatbot.onboarding.domain.knowledge.Knowledge;
import com.chatbot.onboarding.shared.exception.exceptions.KnowledgeNotFoundException;
import com.chatbot.onboarding.shared.mapper.KnowledgeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH)
public class KnowledgeController {

        private final KnowledgeService knowledgeService;

        public KnowledgeController(KnowledgeService knowledgeService) {
            this.knowledgeService = knowledgeService;
        }


        @Operation(summary = "You can create new questions and answers")
        @PostMapping("/knowledge")
        public ResponseEntity<KnowledgeDTO>create(@Valid @RequestBody KnowledgeCreateDto knowledgeCreateDto){
            Knowledge knowledge = KnowledgeMapper.toEntity(knowledgeCreateDto);

            Knowledge savedKnowledge = knowledgeService.save(knowledge);

            return ResponseEntity.ok(KnowledgeMapper.toDto(savedKnowledge));

        }
        @Operation(summary = "Interaction with the chatbot")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "OK, correct operation"),
                @ApiResponse(responseCode = "404", description = "A question that has no answer")
        })
        @PostMapping("/ask")
        public ResponseEntity<String> ask(
                @RequestParam(required = false) String question,
                @RequestBody(required = false) KnowledgeCreateDto dto) {

            String finalQuestion = question != null ? question : (dto != null ? dto.getQuestion() : null);

            if (finalQuestion == null || finalQuestion.isBlank()) {
                throw new KnowledgeNotFoundException("No se encontró respuesta: la pregunta está vacía o mal formada");
            }

            String response = knowledgeService.getResponse(finalQuestion);

            if (response == null || response.isBlank()) {
                throw new KnowledgeNotFoundException("No se encontró respuesta para: " + finalQuestion);
            }

            return ResponseEntity.ok(response);
        }

        @Operation(summary = "Check all available questions/answers")
        @GetMapping("/getAllKnowledge")
        public ResponseEntity<List<Knowledge>> getAllKnowledge(){
            List<Knowledge> knowledge = knowledgeService.findAll();

            return ResponseEntity.ok(knowledge);
        }
}
