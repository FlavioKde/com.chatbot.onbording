package org;

import com.chatbot.onboarding.application.dto.KnowledgeCreateDto;
import com.chatbot.onboarding.application.service.KnowledgeService;
import com.chatbot.onboarding.domain.knowledge.Knowledge;
import com.chatbot.onboarding.infrastructure.controller.KnowledgeController;
import com.chatbot.onboarding.shared.exception.exceptions.KnowledgeNotFoundException;
import com.chatbot.onboarding.shared.exception.handler.GlobalExceptionHandler;
import com.chatbot.onboarding.shared.mapper.KnowledgeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(GlobalExceptionHandler.class)
public class KnowledgeControllerTest {

            private MockMvc mockMvc;

            private ObjectMapper mapper;

            @Mock
            private KnowledgeService knowledgeService;

            @InjectMocks
            private KnowledgeController knowledgeController;

            @BeforeEach
            public void setUp() {
                MockitoAnnotations.openMocks(this);
                mockMvc = MockMvcBuilders.standaloneSetup(knowledgeController)
                        .setControllerAdvice(new GlobalExceptionHandler())
                        .build();

                mapper = new ObjectMapper();
            }

            @Test
            public void testCreateKnowledge() throws Exception {
                KnowledgeCreateDto knowledgeCreateDto = new KnowledgeCreateDto();

                knowledgeCreateDto.setQuestion("Cual es la manera de solicitar las vacacines");
                knowledgeCreateDto.setAnswer("Debes hablarlo con RRHH");

                Knowledge knowledge = KnowledgeMapper.toEntity(knowledgeCreateDto);

                knowledge.setId(1L);

                Mockito.when(knowledgeService.save(Mockito.any())).thenReturn(knowledge);

                mockMvc.perform(post("/api/V0.1/knowledge")
                                .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(knowledgeCreateDto)))
                        .andExpect(status().isOk())

                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.question").value("Cual es la manera de solicitar las vacacines"))
                        .andExpect(jsonPath("$.answer").value("Debes hablarlo con RRHH"));



            }

             @Test
            public void testAsk_shouldReturn404_whenServiceThrows() throws Exception {
                Mockito.when(knowledgeService.getResponse(Mockito.anyString()))
                    .thenThrow(new KnowledgeNotFoundException("No se encontró respuesta para la pregunta intentelo de nuevo"));

                mockMvc.perform(post("/api/V0.1/ask")
                        .param("question", "Cual es la manera de solicitar las vacacines"))
                        .andExpect(status().isNotFound());
            }

            @Test
            public void test_getAllKnowledge() throws Exception {
                Knowledge knowledge1 = new Knowledge("Como solicitar vacaciones", "Contacta con RRHH");
                knowledge1.setId(1L);
                Knowledge knowledge2 = new Knowledge("question2", "answer2");
                knowledge2.setId(2L);

                List<Knowledge> knowledgeList = Arrays.asList(knowledge1, knowledge2);

                Mockito.when(knowledgeService.findAll()).thenReturn(knowledgeList);

                mockMvc.perform(get("/api/V0.1/getAllKnowledge"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.length()").value(knowledgeList.size()))
                        .andExpect(jsonPath("$[0].question").value("Como solicitar vacaciones"))
                        .andExpect(jsonPath("$[0].answer").value("Contacta con RRHH"));







            }

}
