package com.chatbot.onboarding.domain.chatbot;

import com.chatbot.onboarding.domain.knowledge.Knowledge;
import com.chatbot.onboarding.shared.exception.exceptions.KnowledgeNotFoundException;

import java.util.List;

public class Chatbot {

        private final KnowledgeFinder knowledgeFinder;

        public Chatbot(KnowledgeFinder knowledgeFinder) {
            this.knowledgeFinder = knowledgeFinder;
        }

        public String responseTo(String question) {

            List<Knowledge> matches = knowledgeFinder.findByQuestionContainingIgnoreCase(question);

            if (matches.isEmpty()) {
                throw new KnowledgeNotFoundException(question);

            }
            return matches.get(0).getAnswer();
        }
}
