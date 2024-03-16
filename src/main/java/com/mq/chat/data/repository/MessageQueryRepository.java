package com.mq.chat.data.repository;

import com.mq.chat.data.entity.ChatRoom;
import com.mq.chat.data.entity.Message;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mq.chat.data.entity.QChatRoom.chatRoom;
import static com.mq.chat.data.entity.QMember.member;
import static com.mq.chat.data.entity.QMessage.message1;

@Repository
@RequiredArgsConstructor
public class MessageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Message> findByChatRoomOrderByIdAsc(ChatRoom cr) {
        return queryFactory.selectFrom(message1)
                .join(message1.chatRoom, chatRoom)
                .fetchJoin()
                .join(message1.member, member)
                .fetchJoin()
                .where(
                        chatRoom.eq(cr)
                )
                .orderBy(message1.id.asc())
                .fetch();

    }
}
