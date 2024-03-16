/*
package com.mq.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StompHandler extends FilterChannelInterceptor {


    @Override
    public void postSend(Message message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        switch ((accessor.getCommand())) {
            case CONNECT:
                // 유저가 Websocket으로 connect()를 한 뒤 호출됨
                log.info("세션 들어옴 => {}", sessionId);
                break;

            case DISCONNECT:
                // 유저가 Websocket으로 disconnect() 를 한 뒤 호출됨 or 세션이 끊어졌을 때 발생
                log.info("세션 끊음 => {}", sessionId);
                break;

            default:
                break;
        }

    }
}
*/
