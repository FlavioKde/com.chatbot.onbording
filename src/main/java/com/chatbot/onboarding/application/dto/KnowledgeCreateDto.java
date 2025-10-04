package com.chatbot.onboarding.application.dto;

import jakarta.validation.constraints.NotBlank;

public class KnowledgeCreateDto {

        @NotBlank
        private String question;
        @NotBlank
        private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
