package com.mq.chat.data.vo;

import lombok.Getter;

@Getter
public class ChatInviteAcceptedReqVo {
    private Long inviteChatRoomId;
    private Boolean accepted;
}
