package com.mq.chat.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Emoticon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이모티콘 그룹 ID")
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EmoticonGroup emoticonGroup;

    @Comment("이모티콘 이름")
    @Column(nullable = false, length = 128)
    private String name;

    @Comment("이모티콘 이미지 URL")
    @Column(columnDefinition ="TEXT")
    private String imageUrl;

    @Comment("삭제 여부")
    @Column(nullable = false) @ColumnDefault(value = "false")
    private boolean deleted = false;
}
