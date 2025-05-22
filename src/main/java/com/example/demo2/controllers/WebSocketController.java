package com.example.demo2.controllers;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.demo2.models.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    
    @MessageMapping("/chat")
    @SendTo("/topic/group")
    public ChatMessage sendMessage(@Payload ChatMessage message, Principal principal){
        message.setSender(principal.getName());
        log.info(message.toString());
        return message;
    }

    @MessageMapping("/chat.private.{username}")
    // @SendToUser("/queue/private") // this won't send to target user, just echo back the message to sender
    public ChatMessage sendPrivateMessage(@DestinationVariable String username, @Payload ChatMessage message, Principal principal){
        message.setSender(principal.getName());
        log.info(principal.getName()+" sent to "+ username);
        messagingTemplate.convertAndSendToUser(username, "/queue/private", message);
        return message;
    }

}
