package com.mq.chat.data.repository;

import com.mq.chat.data.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByIdAndDeleted(Long id, boolean deleted);
}
