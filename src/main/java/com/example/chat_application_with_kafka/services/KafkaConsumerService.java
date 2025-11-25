package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.dto.ChatMessageDTO;
import com.example.chat_application_with_kafka.enums.MessageStatus;
import com.example.chat_application_with_kafka.model.ChatMessage;
import com.example.chat_application_with_kafka.repository.MessageRepository;
import com.example.chat_application_with_kafka.utils.ActiveUserRegistry;
import com.example.chat_application_with_kafka.utils.WebSocketSenderForPendingMessage;
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

    @Autowired
    private ActiveUserRegistry userRegistry;

    @Autowired
    private WebSocketSenderForPendingMessage sender;


    @KafkaListener(topics ="chat-message", groupId = "chat-group")
    public void consume(ChatMessageDTO message){

        String receiverId = message.getReceiverId();

        if(userRegistry.isOnline(receiverId)){
            System.out.println("Receiver is online, sending the message");
            sender.sendToUser(receiverId, message);

            messageRepository.updateStatus(message.getMessageId(), MessageStatus.DELIVERED);
        }
        else{
            System.out.println("Receiver is offline");
        }

    }

}
