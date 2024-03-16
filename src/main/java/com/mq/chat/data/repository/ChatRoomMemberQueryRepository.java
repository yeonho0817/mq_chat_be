package com.mq.chat.data.repository;

import com.mq.chat.data.entity.ChatRoom;
import com.mq.chat.data.entity.ChatRoomMember;
import com.mq.chat.data.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mq.chat.data.entity.QChatRoom.chatRoom;
import static com.mq.chat.data.entity.QChatRoomMember.chatRoomMember;
import static com.mq.chat.data.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class ChatRoomMemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<ChatRoomMember> findByMemberAndDeleted(Member m, boolean deleted) {
        return queryFactory.selectFrom(chatRoomMember)
                .leftJoin(chatRoomMember.member, member)
                .fetchJoin()
                .leftJoin(chatRoomMember.chatRoom, chatRoom)
                .fetchJoin()
                .where(
                        member.eq(m),
                        chatRoom.deleted.eq(deleted),
                        chatRoomMember.leaved.eq(false)
                )
                .fetch();
    }

    public List<ChatRoomMember> findByChatRoomAndDeleted(ChatRoom cr, boolean deleted) {
        return queryFactory.selectFrom(chatRoomMember)
                .leftJoin(chatRoomMember.member, member)
                .fetchJoin()
                .leftJoin(chatRoomMember.chatRoom, chatRoom)
                .fetchJoin()
                .where(
                        chatRoom.eq(cr),
                        chatRoom.deleted.eq(deleted),
                        chatRoomMember.leaved.eq(false)
                )
                .fetch();
    }
}
