package com.tmi.chat.service;

import com.tmi.chat.dto.MessageDto;
import com.tmi.chat.entity.Message;
import com.tmi.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        messageRepository.save(message.changeEntity());
        return message;
    }
}
