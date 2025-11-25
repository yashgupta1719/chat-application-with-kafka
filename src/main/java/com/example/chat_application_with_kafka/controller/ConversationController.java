package com.example.chat_application_with_kafka.controller;

import com.example.chat_application_with_kafka.dto.ConversationDTO;
import com.example.chat_application_with_kafka.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/api/chat/conversation")
    public ConversationDTO getOrCreateConversation(@RequestParam String user1,
                                                   @RequestParam String user2){
        return conversationService.getOrCreateConversation(user1, user2);
    }

}
