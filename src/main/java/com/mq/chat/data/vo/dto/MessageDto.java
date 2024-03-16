package com.mq.chat.data.vo.dto;

import com.mq.chat.data.enumerate.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private Boolean me;
    private Long memberId;
    private String memberNickname;
    private MessageType messageType;
    private String message;
    private LocalDateTime createdTime;

    public MessageDto(Long id, Boolean me, MessageType messageType, String message, Long memberId, String memberNickname, LocalDateTime createdTime) {
        this.id = id;
        this.me = me;
        this.messageType = messageType;
        this.message = message;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.createdTime = createdTime;
    }
}
