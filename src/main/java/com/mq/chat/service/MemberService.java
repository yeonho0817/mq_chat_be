package com.mq.chat.service;

import com.mq.chat.data.vo.LoginReqVo;
import com.mq.chat.data.vo.SignUpReqVo;
import com.mq.chat.data.vo.resVo.LoginResVo;
import com.mq.chat.data.vo.resVo.MemberListResVo;

public interface MemberService {
    LoginResVo login(LoginReqVo reqVo);
    void signUp(SignUpReqVo reqVo);
    MemberListResVo list(Long chatRoomId);
}
