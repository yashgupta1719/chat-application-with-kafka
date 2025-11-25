package com.example.chat_application_with_kafka.repository;

import com.example.chat_application_with_kafka.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String> {

    @Query("SELECT c FROM Conversation c " +
            "WHERE (c.createdBy = :user1 AND c.participant = :user2) " +
            "   OR (c.createdBy = :user2 AND c.participant = :user1)")
    Optional<Conversation> findConversationBetweenUsers(@Param("user1") String u1,
                                                        @Param("user2") String u2);


}
