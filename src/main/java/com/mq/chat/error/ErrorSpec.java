package com.mq.chat.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorSpec {
    NotFoundError(NOT_FOUND, "해당 내용을 찾을 수 없습니다."),
    ParameterError(BAD_REQUEST, "파라미터 오류입니다. %s"),

    AlreadyExistMemberId(BAD_REQUEST, "이미 존재하는 아이디입니다."),
    AlreadyExistNickname(BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    NotExistMemberId(BAD_REQUEST, "회원 아이디가 일치하지 않습니다."),
    NotExistPassword(BAD_REQUEST, "회원 비밀번호가 일치하지 않습니다."),
    NotExistLoginAccount(BAD_REQUEST, "계정이 존재하지 않습니다."),

    AlreadyInviteChatRoom(BAD_REQUEST, "이미 초대된 채팅방입니다."),
    NotExistChatRoom(BAD_REQUEST, "존재하지 않는 채팅방입니다."),
    NotExistInviteChatRoom(BAD_REQUEST, "초대된 채팅방이 존재하지 않습니다."),
    NotEqualInviteMember(BAD_REQUEST, "초대된 회원과 일치하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
