package com.example.chat_application_with_kafka.controller;



import com.example.chat_application_with_kafka.dto.ChatMessageDTO;
import com.example.chat_application_with_kafka.model.ChatMessage;
import com.example.chat_application_with_kafka.services.ChatService;
import com.example.chat_application_with_kafka.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


@Controller
public class ChatWebSocketController {

    @Autowired
    KafkaProducerService producerService;

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessageDTO message){
        chatService.persistAndSendMessage(message);
    }

}
