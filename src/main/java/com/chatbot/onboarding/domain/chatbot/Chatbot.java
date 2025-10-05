package com.chatbot.onboarding.domain.chatbot;

public class Chatbot {

        private final KnowledgeFinder knowledgeFinder;

        public Chatbot(KnowledgeFinder knowledgeFinder) {
            this.knowledgeFinder = knowledgeFinder;
        }

        public String responseTo(String question) {

            return knowledgeFinder.findAnswerFor(question)
                    .orElseThrow(() -> new RuntimeException("No answer found for question " + question));

        }
}
