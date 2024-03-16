package com.mq.chat.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ChatRoomAddReqVo {
    @NotBlank(message = "채팅방 이름이 빈 칸입니다.")
    private String name;
    @NotBlank(message = "채팅방 설명이 빈 칸입니다.")
    private String description;
    @NotNull(message = "비밀 여부가 빈 칸입니다.")
    private Boolean secret;
    private String password;
    @NotNull(message = "채팅방 제한 인원이 빈 칸입니다.")
    private Integer limitCount;
}
