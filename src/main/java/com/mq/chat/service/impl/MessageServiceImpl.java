package com.mq.chat.service.impl;

import com.mq.chat.data.entity.ChatRoom;
import com.mq.chat.data.entity.Member;
import com.mq.chat.data.entity.Message;
import com.mq.chat.data.enumerate.MessageType;
import com.mq.chat.data.repository.ChatRoomRepository;
import com.mq.chat.data.repository.MemberRepository;
import com.mq.chat.data.repository.MessageRepository;
import com.mq.chat.data.vo.MessageReqVo;
import com.mq.chat.data.vo.dto.MessageDto;
import com.mq.chat.error.Error;
import com.mq.chat.error.ErrorSpec;
import com.mq.chat.service.MessageService;
import com.mq.chat.util.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
    private final SimpMessagingTemplate template;

    @Transactional
    @Override
    public void enter(Long id, Long chatRoomId) {
//        Member member = memberRepository.findById(id)
//                .orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));
//        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
//                .orElseThrow(() -> Error.of(ErrorSpec.NotExistChatRoom));
//        String text = member.getNickname() + "님 입장하셨습니다.";
//
//        log.info("ChatRoom : {}, MessageType={} Message={}", chatRoom.getId(), MessageType.enter, text);

//        Message message = messageRepository.save(new Message(chatRoom, member, MessageType.enter, text));

//        MessageDto messageDto = new MessageDto(chatRoomId, true, text, member.getId(), member.getNickname(), message.getCreated());
//        template.send(KafkaConstants.KAFKA_TOPIC, messageDto);
    }

    @Transactional
    @Override
    public void send(MessageReqVo reqVo) {
        Member member = memberRepository.findById(reqVo.getMemberId())
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));
        ChatRoom chatRoom = chatRoomRepository.findById(reqVo.getChatRoomId())
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistChatRoom));

        log.info("ChatRoom : {}, MessageType={} Message={}", chatRoom.getId(), reqVo.getMessageType(), reqVo.getMessage());

        Message message = messageRepository.save(new Message(chatRoom, member, reqVo.getMessageType(), reqVo.getMessage()));

        MessageDto messageDto = new MessageDto(message.getChatRoom().getId(), true, message.getMessageType(), message.getMessage(), message.getMember().getId(), message.getMember().getNickname(), message.getCreated());
//        template.convertAndSend("/sub/chat/in/" + reqVo.getChatRoomId(), messageDto);
        kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, messageDto);
    }

    @Override
    public void receive(MessageDto messageDto) {
        log.info("receive : chatRoomId={} message={}", messageDto.getId(), messageDto.getMessage());
        template.convertAndSend("/sub/chat/in/" + messageDto.getId(), messageDto);
    }
}
