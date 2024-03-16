package com.mq.chat.data.repository;

import com.mq.chat.data.entity.InviteChatRoom;
import com.mq.chat.data.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mq.chat.data.entity.QChatRoom.chatRoom;
import static com.mq.chat.data.entity.QInviteChatRoom.inviteChatRoom;
import static com.mq.chat.data.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class InviteChatRoomQueryRepository {
    private final JPAQueryFactory queryFactory;

    public InviteChatRoom findById(Long id) {
        return queryFactory.selectFrom(inviteChatRoom)
                .join(inviteChatRoom.chatRoom, chatRoom)
                .fetchJoin()
                .join(inviteChatRoom.member, member)
                .fetchJoin()
                .where(
                        inviteChatRoom.id.eq(id)
                )
                .fetchFirst();
    }

    public List<InviteChatRoom> findByInvitedMemberIdAndAccepted(Member invitedM, boolean accepted) {
        return queryFactory.selectFrom(inviteChatRoom)
                .join(inviteChatRoom.chatRoom, chatRoom)
                .fetchJoin()
                .join(inviteChatRoom.invitedMember, member)
                .fetchJoin()
                .join(inviteChatRoom.member, member)
                .fetchJoin()
                .where(
                        inviteChatRoom.invitedMember.eq(invitedM),
                        inviteChatRoom.accepted.eq(accepted),

                        chatRoom.currentCount.lt(chatRoom.limitCount)
                )
                .fetch();
    }
}
