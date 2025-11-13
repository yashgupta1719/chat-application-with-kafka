package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.model.ChatMessage;
import com.example.chat_application_with_kafka.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;



    @KafkaListener(topics ="chat-message", groupId = "chat-group")
    public void consume(ChatMessage message){
        System.out.println("Processing message from kafka to send to receipient " + message);
        String destination = "/topic/chat/" + message.getConversationId();
        messagingTemplate.convertAndSend(destination, message);
    }

}
