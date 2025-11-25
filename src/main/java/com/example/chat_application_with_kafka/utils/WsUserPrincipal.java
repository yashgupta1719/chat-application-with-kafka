package com.example.chat_application_with_kafka.utils;

import java.security.Principal;

public class WsUserPrincipal implements Principal {

    private final String name;

    public WsUserPrincipal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "WsUserPrincipal[" + name + "]";
    }

}
