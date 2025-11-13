package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "chat-message";

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, ChatMessage> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ChatMessage message){
        kafkaTemplate.send(TOPIC, message.getConversationId(), message);
        System.out.println("Sent message to Kafka");
    }


}
