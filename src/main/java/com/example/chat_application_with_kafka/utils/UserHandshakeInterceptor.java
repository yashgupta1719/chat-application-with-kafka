package com.example.chat_application_with_kafka.utils;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class UserHandshakeInterceptor implements HandshakeInterceptor {

    private final ActiveUserRegistry activeUserRegistry;

    public UserHandshakeInterceptor(ActiveUserRegistry activeUserRegistry){
        this.activeUserRegistry = activeUserRegistry;
    }

    public boolean beforeHandshake(ServerHttpRequest req,
                                   ServerHttpResponse res,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes){

        String userId = req.getHeaders().getFirst("userId");

        if(userId != null && !userId.isEmpty()){
            attributes.put("userId", userId);
            activeUserRegistry.addUser(userId);
            System.out.println("Handshake started");
        }else{
            System.out.println("No user ID found in the handshake header");
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
    }

}
