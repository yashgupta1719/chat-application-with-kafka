package com.example.chat_application_with_kafka.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class PresenceEventListener {

    @Autowired
    private ActiveUserRegistry activeUserRegistry;

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event){

    }

}
