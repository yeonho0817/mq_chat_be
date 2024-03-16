package com.mq.chat.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class ChatRoomMember extends BaseTimeEntity {
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

    @Comment("탈퇴 여부")
    @Column(nullable = false) @ColumnDefault(value = "false")
    private Boolean leaved = false;

    public ChatRoomMember(ChatRoom chatRoom, Member member, Boolean leaved) {
        this.chatRoom = chatRoom;
        this.member = member;
        this.leaved = leaved;
    }
}
