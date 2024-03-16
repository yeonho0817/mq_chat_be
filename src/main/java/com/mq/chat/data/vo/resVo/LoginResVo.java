package com.mq.chat.data.vo.resVo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginResVo {
    private Long id;

    public LoginResVo(Long id) {
        this.id = id;
    }
}
