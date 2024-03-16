package com.mq.chat.service.impl;

import com.mq.chat.data.entity.Emoticon;
import com.mq.chat.data.entity.EmoticonGroup;
import com.mq.chat.data.repository.EmoticonQueryRepository;
import com.mq.chat.data.vo.resVo.EmoticonListResVo;
import com.mq.chat.service.EmoticonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmoticonServiceImpl implements EmoticonService {
    private final EmoticonQueryRepository emoticonQueryRepository;

    @Transactional(readOnly = true)
    @Override
    public EmoticonListResVo get() {
        Map<EmoticonGroup, List<Emoticon>> emoticonGroupList = emoticonQueryRepository.findByDeletedOrderById(false).stream().collect(Collectors.groupingBy(Emoticon::getEmoticonGroup));

        return new EmoticonListResVo(emoticonGroupList);
    }
}