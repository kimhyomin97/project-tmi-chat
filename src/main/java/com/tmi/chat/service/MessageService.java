package com.tmi.chat.service;

import com.tmi.chat.dto.MessageDto;
import com.tmi.chat.entity.Message;
import com.tmi.chat.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageDto messageType(MessageDto message) {
        if (Message.MessageType.ENTER.equals(message.getType())){
            message.setDetailMessage(message.getSenderId() + "님이 입장하셨습니다.");
        }
        // save message
        return message;
    }
}
