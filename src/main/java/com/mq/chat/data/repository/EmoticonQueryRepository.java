package com.mq.chat.data.repository;

import com.mq.chat.data.entity.Emoticon;
import com.mq.chat.data.entity.EmoticonGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mq.chat.data.entity.QEmoticon.emoticon;
import static com.mq.chat.data.entity.QEmoticonGroup.emoticonGroup;

@Repository
@RequiredArgsConstructor
public class EmoticonQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Emoticon> findByDeletedOrderById(boolean deleted) {
        return queryFactory.selectFrom(emoticon)
                .leftJoin(emoticon.emoticonGroup, emoticonGroup)
                .fetchJoin()
                .where(
                        emoticonGroup.deleted.eq(deleted),
                        emoticon.deleted.eq(deleted)
                )
                .orderBy(emoticonGroup.id.asc())
                .fetch();
    }
}
