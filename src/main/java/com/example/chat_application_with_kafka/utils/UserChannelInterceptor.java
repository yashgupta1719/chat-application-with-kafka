package com.example.chat_application_with_kafka.utils;

import com.example.chat_application_with_kafka.services.PendingMessageDeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.example.chat_application_with_kafka.utils.WsUserPrincipal;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Component
public class UserChannelInterceptor implements ChannelInterceptor {

    private final ActiveUserRegistry activeUserRegistry;

    public UserChannelInterceptor(ActiveUserRegistry activeUserRegistry){
        this.activeUserRegistry = activeUserRegistry;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {

            accessor.setLeaveMutable(true);

            String userId = accessor.getFirstNativeHeader("userId");
            if (userId != null) {

                accessor.setUser(new WsUserPrincipal(userId));

                accessor.getSessionAttributes().put("userId", userId);

                activeUserRegistry.addUser(userId);
                if(activeUserRegistry.getOnlineUsers().contains(userId))
                    System.out.println("User: " + userId +" online");
                else
                    System.out.println("user not online");
            }
        }
        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            System.out.println("SUBSCRIBE intercepted (session="
                    + accessor.getSessionId()
                    + ") principal=" + accessor.getUser()
                    + " sessionAttrs=" + accessor.getSessionAttributes());
        }

        return MessageBuilder.createMessage(
                message.getPayload(),
                accessor.getMessageHeaders()
        );
    }
}



