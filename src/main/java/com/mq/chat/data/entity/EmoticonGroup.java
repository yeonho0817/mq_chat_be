package com.mq.chat.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class EmoticonGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("그룹 이름")
    @Column(nullable = false, length = 128)
    private String name;

    @Comment("삭제 여부")
    @Column(nullable = false) @ColumnDefault(value = "false")
    private boolean deleted = false;

//    @Comment("유료 여부")
//    @Column(nullable = false) @ColumnDefault(value = "false")
//    private boolean pay = false;
//
//    @Comment("가격")
//    private Integer price;
}
