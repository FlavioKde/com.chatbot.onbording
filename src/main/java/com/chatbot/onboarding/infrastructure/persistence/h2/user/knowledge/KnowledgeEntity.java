package com.chatbot.onboarding.infrastructure.persistence.h2.user.knowledge;


import jakarta.persistence.*;

@Entity
@Table(name = "knowledge")
public class KnowledgeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String response;

    public KnowledgeEntity() {

    }

    public KnowledgeEntity(String question, String response) {
        this.question = question;
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }



}
