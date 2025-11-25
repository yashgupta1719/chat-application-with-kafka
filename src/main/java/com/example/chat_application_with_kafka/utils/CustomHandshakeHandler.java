package com.example.chat_application_with_kafka.utils;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        // try to extract userId from query parameter: /ws?userId=YASH-1
        URI uri = request.getURI();
        String query = uri.getQuery(); // may be null
        String userId = null;

        if (query != null) {
            for (String param : query.split("&")) {
                String[] kv = param.split("=", 2);
                if (kv.length == 2 && "userId".equals(kv[0]) && !kv[1].isBlank()) {
                    userId = kv[1];
                    break;
                }
            }
        }

        if (userId == null) {
            // fallback: try header (helpful if your client passes userId header)
            if (request.getHeaders().containsKey("userId")) {
                userId = request.getHeaders().getFirst("userId");
            }
        }

        final String finalUserId = (userId != null && !userId.isBlank()) ? userId : "ANONYMOUS";

        // Return a real Principal instance (not a lambda). Spring will register the session.
        return new Principal() {
            @Override
            public String getName() {
                return finalUserId;
            }

            @Override
            public String toString() {
                return "WSPrincipal[" + finalUserId + "]";
            }
        };
    }

}
