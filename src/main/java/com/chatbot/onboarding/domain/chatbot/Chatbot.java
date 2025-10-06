package com.chatbot.onboarding.domain.chatbot;

import com.chatbot.onboarding.shared.exception.exceptions.KnowledgeNotFoundException;

public class Chatbot {

        private final KnowledgeFinder knowledgeFinder;

        public Chatbot(KnowledgeFinder knowledgeFinder) {
            this.knowledgeFinder = knowledgeFinder;
        }

        public String responseTo(String question) {

            return knowledgeFinder.findAnswerFor(question)
                    .orElseThrow(() -> new KnowledgeNotFoundException(question));

        }
}
