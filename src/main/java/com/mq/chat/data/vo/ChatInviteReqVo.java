package com.mq.chat.data.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ChatInviteReqVo {
    @NotNull
    private Long chatRoomId;
    @NotNull
    private List<Long> memberIds;
}
