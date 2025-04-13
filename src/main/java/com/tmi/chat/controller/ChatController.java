package com.tmi.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmi.chat.entity.ChatMessage;
import com.tmi.chat.repository.ChatMessageRepository;
import com.tmi.chat.service.ChatProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageRepository repository;

    private final ChatProducer chatProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody ChatMessage message) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(message);
        chatProducer.sendMessage(json);
        return ResponseEntity.ok("Message saved");
    }

    @GetMapping("/room/{roomId}")
    public List<ChatMessage> getMessages(@PathVariable String roomId) {
        return repository.findByRoomIdOrderByCreatedAtAsc(roomId);
    }
}
