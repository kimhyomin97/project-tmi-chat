package com.tmi.chat.dto;

import com.tmi.chat.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    Message.MessageType type;
    Long roomId;
    String detailMessage;
    Long senderId;
    String senderName;

    public Message changeEntity(){
        return Message.builder()
                .type(type)
                .roomId(roomId)
                .senderId(senderId)
                .senderName(senderName)
                .detailMessage(detailMessage)
                .sendTime(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
                .build();
    }
}
