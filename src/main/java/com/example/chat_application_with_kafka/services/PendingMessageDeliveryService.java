package com.example.chat_application_with_kafka.services;

import com.example.chat_application_with_kafka.dto.ChatMessageDTO;
import com.example.chat_application_with_kafka.enums.MessageStatus;
import com.example.chat_application_with_kafka.model.ChatMessage;
import com.example.chat_application_with_kafka.repository.MessageRepository;
import com.example.chat_application_with_kafka.utils.WebSocketSenderForPendingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PendingMessageDeliveryService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private WebSocketSenderForPendingMessage sender;

    @Transactional
    public void deliverPendingMessage(String userId, String conversationId){

        List<ChatMessage> pendingMessages = messageRepository.findPendingMessagesForConversation(
                userId,
                conversationId
        );
        if(pendingMessages.isEmpty()){
            System.out.println("No pending messages");
            return;
        }
        for(ChatMessage message : pendingMessages){
            ChatMessageDTO messageDTO = new ChatMessageDTO(
                    message.getMessageId(),
                    message.getConversationId(),
                    message.getSenderId(),
                    message.getReceiverId(),
                    message.getContent(),
                    message.getContentType()
            );
            sender.sendToUser(userId, messageDTO);

            message.setStatus(MessageStatus.DELIVERED);
            messageRepository.save(message);
        }

        System.out.println("Delivered: " + pendingMessages.size() +" pending messages to the user" + userId);
    }

}
