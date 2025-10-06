package com.chatbot.onboarding.shared.exception.handler;


import com.chatbot.onboarding.shared.exception.exceptions.KnowledgeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KnowledgeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleKnowledgeNotFound(KnowledgeNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("path", "/api/V0.1/ask");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
