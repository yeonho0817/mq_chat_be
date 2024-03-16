package com.mq.chat.controller;

import com.mq.chat.data.vo.MessageReqVo;
import com.mq.chat.data.vo.dto.MessageDto;
import com.mq.chat.service.MessageService;
import com.mq.chat.util.KafkaConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/chat/enter/{id}")
    public void enter(
            @RequestHeader("Authorization") Long id,
            @PathVariable(value = "id") Long chatRoomId
    ) {
        messageService.enter(id, chatRoomId);
    }

    @MessageMapping("/chat/send")
    public void send(
            @RequestBody MessageReqVo reqVo
    ) {
        messageService.send(reqVo);
    }

    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void receive(MessageDto messageDto) {
        messageService.receive(messageDto);
    }

}
