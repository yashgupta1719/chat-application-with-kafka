package com.example.chat_application_with_kafka.model;


import com.example.chat_application_with_kafka.enums.ContentType;
import com.example.chat_application_with_kafka.enums.MessageStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "messages",
        indexes = {
                @Index(name = "idx_conversation_created_at", columnList = "conversation_id, created_at"),
                @Index(name = "idx_sender_created_at", columnList = "sender_id, created_at")
        })
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "conversation_id", nullable = false, columnDefinition = "CHAR(36)")
    private String conversationId;

    @Column(name = "sender_id", nullable = false, columnDefinition = "CHAR(36)")
    private String senderId;

    @Column(name = "receiver_id", nullable = false, columnDefinition = "CHAR(36)")
    private String receiverId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", columnDefinition = "ENUM('text','image','video','file','system') DEFAULT 'text'")
    private ContentType contentType = ContentType.TEXT;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deleted = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('SENT','DELIVERED','READ') DEFAULT 'SENT'")
    private MessageStatus status = MessageStatus.SENT;

    public ChatMessage(){}

    public ChatMessage(Long messageId, String conversationId, String senderId,
                         String receiverId, String content, ContentType contentType, MessageStatus status) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.contentType = contentType;
        this.status = status;
    }



    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}


