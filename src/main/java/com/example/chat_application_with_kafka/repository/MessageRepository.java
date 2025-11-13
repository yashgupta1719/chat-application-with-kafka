package com.example.chat_application_with_kafka.repository;

import com.example.chat_application_with_kafka.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, String> {

    

}
