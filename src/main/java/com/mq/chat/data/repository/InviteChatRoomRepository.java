package com.mq.chat.data.repository;

import com.mq.chat.data.entity.InviteChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteChatRoomRepository extends JpaRepository<InviteChatRoom, Long> {
    List<InviteChatRoom> findByChatRoomIdAndAccepted(Long chatRoomId, boolean accepted);
}
