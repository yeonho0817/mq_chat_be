package com.mq.chat.service;

import com.mq.chat.data.vo.MessageReqVo;
import com.mq.chat.data.vo.dto.MessageDto;

public interface MessageService {
    void enter(Long id, Long chatRoomId);
    void send(MessageReqVo reqVo);
    void receive(MessageDto messageDto);
}
