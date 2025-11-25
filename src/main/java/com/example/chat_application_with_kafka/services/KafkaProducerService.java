package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.dto.ChatMessageDTO;
import com.example.chat_application_with_kafka.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "chat-message";

    private final KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, ChatMessageDTO> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ChatMessageDTO message){
        kafkaTemplate.send(TOPIC, message.getConversationId(), message);
        System.out.println("Sent message to Kafka");
    }


}
