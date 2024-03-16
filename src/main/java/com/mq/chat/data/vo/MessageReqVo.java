package com.mq.chat.data.vo;

import com.mq.chat.data.enumerate.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageReqVo {
    private Long memberId;
    private Long chatRoomId;
    private MessageType messageType;
    private String message;
}
