package com.example.chat_application_with_kafka.utils;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ActiveUserRegistry {

    private final Set<String> activeUsers = ConcurrentHashMap.newKeySet();


    public void addUser(String userId){
        activeUsers.add(userId);
        System.out.println("Added user to registry");
    }

    public void removeUser(String userId){
        activeUsers.remove(userId);
        System.out.println("removed user from the registry");
    }


    public boolean isOnline(String userId){
        return activeUsers.contains(userId);
    }

    public Set<String> getOnlineUsers(){
        return activeUsers;
    }
}
