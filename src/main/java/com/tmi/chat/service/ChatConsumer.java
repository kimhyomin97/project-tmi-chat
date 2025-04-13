package com.tmi.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmi.chat.entity.ChatMessage;
import com.tmi.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatConsumer {
    private final ChatMessageRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "chat-messages", groupId = "chat-consumer-group")
    public void consume(String messageJson) throws Exception {
        ChatMessage message = objectMapper.readValue(messageJson, ChatMessage.class);
        repository.save(message);
        System.out.println("메시지 저장 성공 : " + message.getContent());
    }
}
