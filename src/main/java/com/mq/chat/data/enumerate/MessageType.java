package com.mq.chat.data.enumerate;

import lombok.Getter;

@Getter
public enum MessageType {
    enter("입장"),
    text("문자"),
    emoticon("이모티콘");

    private final String description;

    MessageType(String description) {
        this.description = description;
    }
}
