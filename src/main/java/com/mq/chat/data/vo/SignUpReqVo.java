package com.mq.chat.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class SignUpReqVo {
    @NotBlank(message = "아이디가 빈 칸입니다.")
    private String memberId;
    @NotBlank(message = "비밀번호가 빈 칸입니다.")
    private String password;
    @NotBlank(message = "이름이 빈 칸입니다.")
    private String name;
    @NotBlank(message = "닉네임이 빈 칸입니다.")
    private String nickname;
    @NotBlank(message = "전화번호가 빈 칸입니다.")
    private String phoneNumber;
}
