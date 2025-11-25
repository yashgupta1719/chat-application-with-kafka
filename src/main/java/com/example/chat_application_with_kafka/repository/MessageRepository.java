package com.example.chat_application_with_kafka.repository;

import com.example.chat_application_with_kafka.enums.MessageStatus;
import com.example.chat_application_with_kafka.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("SELECT m FROM ChatMessage m WHERE m.receiverId = :userId AND m.conversationId = :conversationId AND m.status = 'SENT'")
    List<ChatMessage> findPendingMessagesForConversation(@Param("userId") String userId, @Param("conversationId") String conversationId);

    @Transactional
    @Modifying
    @Query("UPDATE ChatMessage m SET m.status = :status WHERE m.messageId = :id" )
    void updateStatus(@Param("id") Long messageId, @Param("status") MessageStatus status);
}
