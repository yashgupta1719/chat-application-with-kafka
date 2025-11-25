package com.example.chat_application_with_kafka.dto;

import com.example.chat_application_with_kafka.enums.ContentType;
import com.example.chat_application_with_kafka.enums.MessageStatus;

public class ChatMessageDTO {

    private Long messageId;
    private String conversationId;
    private String senderId;
    private String receiverId;
    private String content;

    public ChatMessageDTO(){}

    public ChatMessageDTO(Long messageId, String conversationId, String senderId, String receiverId,
                          String content, ContentType contentType){
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.contentType = contentType;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    private ContentType contentType;

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
}
