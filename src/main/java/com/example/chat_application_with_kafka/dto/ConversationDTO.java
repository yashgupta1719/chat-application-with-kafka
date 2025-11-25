package com.example.chat_application_with_kafka.dto;

import java.time.LocalDateTime;

public class ConversationDTO {

    private String conversationId;
    private String creator;
    private String participant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConversationDTO(){}

    public ConversationDTO(String conversationId, String creator, String participant,
                            LocalDateTime createdAt, LocalDateTime updatedAt){
        this.conversationId = conversationId;
        this.creator = creator;
        this.participant = participant;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}
