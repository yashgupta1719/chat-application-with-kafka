package com.example.chat_application_with_kafka.utils;

import com.example.chat_application_with_kafka.dto.ChatMessageDTO;
import com.example.chat_application_with_kafka.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketSenderForPendingMessage {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendToUser(String userId, ChatMessageDTO message){
//        String destination = "/topic/chat/" + message.getConversationId();
//        messagingTemplate.convertAndSend(destination, message);
        messagingTemplate.convertAndSendToUser(
                userId,
                "/queue/messages",
                message
        );
    }

}
