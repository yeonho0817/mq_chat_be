package com.mq.chat.service;

import com.mq.chat.data.vo.ChatInviteReqVo;
import com.mq.chat.data.vo.resVo.InviteChatRoomListResVo;

public interface InviteService {
    InviteChatRoomListResVo getInviteList(Long id);
    void invite(Long id, ChatInviteReqVo reqVo);
    void inviteAccepted(Long id, Long inviteChatRoomId, Boolean accepted);
}
