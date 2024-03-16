package com.mq.chat.service;

import com.mq.chat.data.vo.ChatRoomAddReqVo;
import com.mq.chat.data.vo.resVo.ChatRoomListResVo;
import com.mq.chat.data.vo.resVo.ChatRoomMessageListResVo;

public interface ChatRoomService {
    ChatRoomListResVo getList(Long id);
    ChatRoomMessageListResVo getMessages(Long id, Long chatRoomId);
    void add(Long id, ChatRoomAddReqVo reqVo);

}
