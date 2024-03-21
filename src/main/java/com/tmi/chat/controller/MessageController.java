package com.tmi.chat.controller;

import com.tmi.chat.dto.MessageDto;
import com.tmi.chat.service.MessageService;
import jakarta.security.auth.message.MessageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;
    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/message") // prefix : /app -> /app/message
    public void sendMessage(MessageDto message) {
        log.info("message : {}", message);
        MessageDto messageDto = messageService.messageType(message);
        sendingOperations.convertAndSend("/topic/chat/room/"+messageDto.getRoomId(),message);
    }
}
