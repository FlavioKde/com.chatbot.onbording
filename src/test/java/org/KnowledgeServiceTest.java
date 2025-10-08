package org;

import com.chatbot.onboarding.application.service.KnowledgeService;
import com.chatbot.onboarding.domain.chatbot.Chatbot;
import com.chatbot.onboarding.domain.knowledge.Knowledge;
import com.chatbot.onboarding.domain.knowledge.repository.KnowledgeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KnowledgeServiceTest {

            @Mock
            private KnowledgeRepository knowledgeRepository;
            @Mock
            private Chatbot chatbot;

            @InjectMocks
            private KnowledgeService knowledgeService;

            @BeforeEach
            public void setUp() {
                MockitoAnnotations.openMocks(this);
            }

            @Test
            public void testSaveKnowledge() {
                Knowledge knowledge = new Knowledge();

                knowledge.setId(1L);
                knowledge.setQuestion("Cual es el horario de la empresa el viernes");
                knowledge.setAnswer("Jornada reducida de 8 a 15");

                when(knowledgeRepository.save(any(Knowledge.class))).thenReturn(knowledge);

                Knowledge savedKnowledge = knowledgeService.save(knowledge);

                Assertions.assertNotNull(savedKnowledge);

                verify(knowledgeRepository).save(knowledge);

            }

            @Test
            public void testFindAll() {
                Knowledge knowledge1 = new Knowledge("pregunta1","respuesta1");
                Knowledge knowledge2 = new Knowledge("pregunta2","respuesta2");

                List<Knowledge> knowledgeList = new ArrayList<>();
                knowledgeList.add(knowledge1);
                knowledgeList.add(knowledge2);

                when(knowledgeRepository.findAll()).thenReturn(knowledgeList);

                List<Knowledge> allKnowledge = knowledgeService.findAll();

                Assertions.assertNotNull(allKnowledge);
                Assertions.assertEquals(2,knowledgeList.size());

                verify(knowledgeRepository).findAll();
            }

            @Test
            public void testGetResponse() throws Exception {

                String question = "Donde queda RRHH";
                String answer = "En la segunda planta";


                when(chatbot.responseTo("question")).thenReturn("answer");

                String result = knowledgeService.getResponse(question);

                Assertions.assertNotNull(answer, result);

                verify(chatbot).responseTo("Donde queda RRHH");
            }
}
