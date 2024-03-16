package com.mq.chat.data.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
public class InviteChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("채팅방 ID")
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ChatRoom chatRoom;

    @Comment("회원 ID")
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    @Comment("초대 회원 ID")
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member invitedMember;

    @Comment("수락 여부")
    @Column(nullable = false) @ColumnDefault(value = "false")
    private boolean accepted = false;

    @Comment("삭제 여부")
    @Column(nullable = false) @ColumnDefault(value = "false")
    private boolean deleted = false;

    public InviteChatRoom(ChatRoom chatRoom, Member member, Member invitedMember, boolean accepted) {
        this.chatRoom = chatRoom;
        this.member = member;
        this.invitedMember = invitedMember;
        this.accepted = accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
