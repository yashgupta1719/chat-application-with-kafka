package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.enums.MessageStatus;
import com.example.chat_application_with_kafka.model.ChatMessage;
import com.example.chat_application_with_kafka.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public void persistAndSendMessage(ChatMessage message){
        message.setStatus(MessageStatus.SENT);
        messageRepository.save(message);
        System.out.println("Message persisted in the database");
        kafkaProducerService.sendMessage(message);
    }

}
