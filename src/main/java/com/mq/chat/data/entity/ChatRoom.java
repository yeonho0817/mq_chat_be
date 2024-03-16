package com.mq.chat.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("채팅방 이름")
    @Column(nullable = false, length = 128)
    private String name;

    @Comment("채팅방 설명")
    @Column(nullable = false)
    private String description;

    @Comment("비밀여부")
    @Column(nullable = false)
    private boolean secret;

    @Comment("비밀번호")
    @Column(length = 64)
    private String password;

    @Comment("현재 인원")
    @Column(nullable = false) @ColumnDefault(value = "1")
    private Integer currentCount;

    @Comment("제한 인원")
    private Integer limitCount;

    @Comment("삭제 여부")
    @Column(nullable = false) @ColumnDefault(value = "false")
    private boolean deleted = false;

    public ChatRoom(String name, String description, boolean secret, String password, Integer currentCount, Integer limitCount, boolean deleted) {
        this.name = name;
        this.description = description;
        this.secret = secret;
        this.password = password;
        this.currentCount = currentCount;
        this.limitCount = limitCount;
        this.deleted = deleted;
    }

    public void setPlusCurrentCount() {
        this.currentCount++;
    }
}
