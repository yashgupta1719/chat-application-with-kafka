package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.dto.ChatMessageDTO;
import com.example.chat_application_with_kafka.enums.MessageStatus;
import com.example.chat_application_with_kafka.model.ChatMessage;
import com.example.chat_application_with_kafka.model.Conversation;
import com.example.chat_application_with_kafka.repository.ConversationRepository;
import com.example.chat_application_with_kafka.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private ConversationRepository conversationRepository;

    public void persistAndSendMessage(ChatMessageDTO messageDTO){

        Conversation conversation = conversationRepository
                .findById(messageDTO.getConversationId())
                .orElse(null);
        if(conversation == null) {
//            conversation = new Conversation(
//                    messageDTO.getConversationId(),
//                    messageDTO.getSenderId(),
//                    messageDTO.getReceiverId()
//            );
            System.out.println("No conversation found, failed");
            return;
        }
//        else{
            conversation.setUpdatedAt(LocalDateTime.now());
//        }
//        conversationRepository.save(conversation);
        ChatMessage chatMessage = new ChatMessage(
                messageDTO.getConversationId(),
                messageDTO.getSenderId(),
                messageDTO.getReceiverId(),
                messageDTO.getContent(),
                messageDTO.getContentType(),
                conversation.getUpdatedAt()
        );
        chatMessage.setStatus(MessageStatus.SENT);
        ChatMessage savedMessage = messageRepository.save(chatMessage);
        messageDTO.setMessageId(savedMessage.getMessageId());
        System.out.println("Message persisted in the database");
        kafkaProducerService.sendMessage(messageDTO);
    }

}
