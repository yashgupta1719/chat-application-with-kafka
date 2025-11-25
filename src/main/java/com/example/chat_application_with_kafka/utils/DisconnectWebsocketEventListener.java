package com.example.chat_application_with_kafka.utils;


import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class DisconnectWebsocketEventListener {

    private final ActiveUserRegistry activeUserRegistry;

    public DisconnectWebsocketEventListener(ActiveUserRegistry activeUserRegistry){
        this.activeUserRegistry = activeUserRegistry;
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        if(userId != null){
            activeUserRegistry.removeUser(userId);
            System.out.println("User disconnected" + userId);
        }
    }

}
