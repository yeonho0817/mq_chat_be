package com.mq.chat.data.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("아이디")
    @Column(nullable = false, length = 128, unique = true)
    private String memberId;

    @Comment("비밀번호")
    @Column(nullable = false, length = 64)
    private String password;

    @Comment("이름")
    @Column(nullable = false)
    private String name;

    @Comment("별명")
    @Column(nullable = false, unique = true)
    private String nickname;

    @Comment("전화번호")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    public Member(String memberId, String password, String name, String nickname, String phoneNumber) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
