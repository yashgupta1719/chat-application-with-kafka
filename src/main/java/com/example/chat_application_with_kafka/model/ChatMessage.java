package com.example.chat_application_with_kafka.model;


import com.example.chat_application_with_kafka.enums.ContentType;
import com.example.chat_application_with_kafka.enums.MessageStatus;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    private String messageId;
    private String conversationId;
    private String senderId;
    private String receiverId;
    private String content;
    private String timeStamp;

    @Enumerated(EnumType.STRING)
    private MessageStatus status = MessageStatus.SENT;

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Enumerated(EnumType.STRING)
    private ContentType contentType = ContentType.TEXT;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}


