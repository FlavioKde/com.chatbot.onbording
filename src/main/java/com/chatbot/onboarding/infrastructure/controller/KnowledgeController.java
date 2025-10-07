package com.chatbot.onboarding.infrastructure.controller;


import com.chatbot.onboarding.application.dto.KnowledgeCreateDto;
import com.chatbot.onboarding.application.dto.KnowledgeDTO;
import com.chatbot.onboarding.application.service.KnowledgeService;
import com.chatbot.onboarding.config.ApiConfig;
import com.chatbot.onboarding.domain.knowledge.Knowledge;
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
        @Operation(summary = "Puedes crear nuevas preguntas y respuestas")
        @PostMapping("/knowledge")
        public ResponseEntity<KnowledgeDTO>create(@Valid @RequestBody KnowledgeCreateDto knowledgeCreateDto){
            Knowledge knowledge = KnowledgeMapper.toEntity(knowledgeCreateDto);

            Knowledge savedKnowledge = knowledgeService.save(knowledge);

            return ResponseEntity.ok(KnowledgeMapper.toDto(savedKnowledge));

        }
        @Operation(summary = "Interacción con el chatbot")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "OK, operación correcta"),
                @ApiResponse(responseCode = "404", description = "Pregunta que no tiene respuesta")
        })
        @PostMapping("/ask")
        public ResponseEntity<String>ask(@RequestParam String question){
            String response = knowledgeService.getResponse(question);

            return ResponseEntity.ok(response);
        }

        @Operation(summary = "Consulta todas las preguntas/respuestas disponibles")
        @GetMapping("/getAllKnowledge")
        public ResponseEntity<List<Knowledge>> getAllKnowledge(){
            List<Knowledge> knowledge = knowledgeService.findAll();

            return ResponseEntity.ok(knowledge);
        }
}
