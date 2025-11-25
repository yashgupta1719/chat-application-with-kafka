package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.dto.ConversationDTO;
import com.example.chat_application_with_kafka.model.Conversation;
import com.example.chat_application_with_kafka.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;


    public ConversationDTO getOrCreateConversation(String user1, String user2){
        Conversation conversation = conversationRepository.findConversationBetweenUsers(user1, user2)
                .orElse(null);

        if(conversation == null) {
            conversation = new Conversation(
                    UUID.randomUUID().toString(),
                    user1,
                    user2
            );
            conversation = conversationRepository.save(conversation);
        }
        else{
            System.out.println("Found existing conversation");
        }

        return new ConversationDTO(
                conversation.getConversationId(),
                conversation.getCreatedBy(),
                conversation.getParticipant(),
                conversation.getCreatedAt(),
                conversation.getUpdatedAt()
        );


    }

}
