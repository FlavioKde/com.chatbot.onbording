package com.chatbot.onboarding.shared.exception.exceptions;

public class KnowledgeNotFoundException extends RuntimeException {
    public KnowledgeNotFoundException(String question) {
        super("No se encontró respuesta para: " + question + " intentelo de nuevo");
    }
}
