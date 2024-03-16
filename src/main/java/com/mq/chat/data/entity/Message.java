package com.mq.chat.data.entity;

import com.mq.chat.data.enumerate.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Message extends BaseTimeEntity {
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

    @Comment("메시지 타입")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType messageType;

    @Comment("메시지")
    @Column(nullable = false, columnDefinition ="TEXT")
    private String message;

    public Message(ChatRoom chatRoom, Member member, MessageType messageType, String message) {
        this.chatRoom = chatRoom;
        this.member = member;
        this.messageType = messageType;
        this.message = message;
    }
}
