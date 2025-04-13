package com.tmi.chat.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "chat_messages")
@Getter
@Setter
public class ChatMessage {
    @Id
    private String id;
    private String roomId;
    private String senderId;
    private String content;
    private String type; // TEXT, IMAGE. FILE 등
    private Instant createdAt = Instant.now();
    private Instant deletedAt;
}
