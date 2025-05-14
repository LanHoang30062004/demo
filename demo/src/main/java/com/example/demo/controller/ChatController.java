package com.example.demo.controller;

import com.example.demo.dto.request.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage/{auctionId}")
    @SendTo("/topic/auction/{auctionId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage,
                                   @DestinationVariable String auctionId) {
        log.info("Sending auction message to {}", auctionId);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{auctionId}")
    @SendTo("/topic/auction/{auctionId}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               @DestinationVariable String auctionId,
                               SimpMessageHeaderAccessor headerAccessor) {
        return chatMessage;
    }

}

