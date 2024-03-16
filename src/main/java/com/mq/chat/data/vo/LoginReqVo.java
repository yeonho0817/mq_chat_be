package com.mq.chat.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class LoginReqVo {
    @NotBlank(message = "아이디가 빈 칸입니다.")
    private String memberId;
    @NotBlank(message = "비밀번호가 빈 칸입니다.")
    private String password;
}
