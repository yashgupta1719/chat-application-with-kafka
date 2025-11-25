package com.example.chat_application_with_kafka.utils;

import com.example.chat_application_with_kafka.services.PendingMessageDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class StompSubscribeEventListener {

    @Autowired
    private PendingMessageDeliveryService pendingMessageDeliveryService;

    @Autowired
    private ActiveUserRegistry activeUserRegistry;

    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("EVENT SUBSCRIBE: session=" + accessor.getSessionId()
                + " principal=" + accessor.getUser()
                + " nativeHeaders=" + accessor.toNativeHeaderMap()
                + " sessionAttrs=" + accessor.getSessionAttributes());
        String destination = accessor.getDestination();

        System.out.println("SUBSCRIBE PRINCIPAL = " + accessor.getUser());

        if (!destination.startsWith("/topic/chat/")) {
            return; // ignore inbox subscriptions (/user/queue/messages)
        }

        if (destination == null) {
            return;
        }

        String userId = null;

        if (accessor.getUser() != null) {
            userId = accessor.getUser().getName();  // case 1
        }

        if (userId == null && accessor.getSessionAttributes() != null) {
            userId = (String) accessor.getSessionAttributes().get("userId"); // case 2
        }

        if (userId == null) {
            System.out.println("SUBSCRIBE event had no user");
            return;
        }

        String conversationId = extractConversationId(destination);

        System.out.println(
                "SUBSCRIBE event: user " + userId +
                        " subscribed to conversation " + conversationId
        );

        // Deliver pending messages safely (POST-SUBSCRIBE)
        pendingMessageDeliveryService.deliverPendingMessage(userId, conversationId);
    }

    private String extractConversationId(String destination) {
        String[] parts = destination.split("/");
        return parts[parts.length - 1];
    }
}
