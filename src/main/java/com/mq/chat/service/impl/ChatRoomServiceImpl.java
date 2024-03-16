package com.mq.chat.service.impl;

import com.mq.chat.data.entity.*;
import com.mq.chat.data.repository.*;
import com.mq.chat.data.vo.ChatRoomAddReqVo;
import com.mq.chat.data.vo.resVo.ChatRoomListResVo;
import com.mq.chat.data.vo.resVo.ChatRoomMessageListResVo;
import com.mq.chat.error.Error;
import com.mq.chat.error.ErrorSpec;
import com.mq.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomMemberQueryRepository chatRoomMemberQueryRepository;
    private final InviteChatRoomQueryRepository inviteChatRoomQueryRepository;
    private final MessageQueryRepository messageQueryRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public ChatRoomListResVo getList(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));

        List<ChatRoomMember> chatRoomMembers = chatRoomMemberQueryRepository.findByMemberAndDeleted(member, false);
        List<InviteChatRoom> inviteChatRooms = inviteChatRoomQueryRepository.findByInvitedMemberIdAndAccepted(member, false);

        return new ChatRoomListResVo(chatRoomMembers, inviteChatRooms.size());
    }

    @Transactional(readOnly = true)
    @Override
    public ChatRoomMessageListResVo getMessages(Long id, Long chatRoomId) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistChatRoom));

        List<Message> messages = messageQueryRepository.findByChatRoomOrderByIdAsc(chatRoom);

        return new ChatRoomMessageListResVo(member, chatRoom, messages);
    }

    @Transactional
    @Override
    public void add(Long id, ChatRoomAddReqVo reqVo) {
        Member member = memberRepository.findById(id).orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));

        String password = null;
        if(reqVo.getSecret()) {
            password = bCryptPasswordEncoder.encode(reqVo.getPassword());
        }
        ChatRoom chatRoom = new ChatRoom(reqVo.getName(), reqVo.getDescription(),
                reqVo.getSecret(), password, 1, reqVo.getLimitCount(), false);
        ChatRoomMember chatRoomMember = new ChatRoomMember(chatRoom, member, false);

        chatRoomRepository.save(chatRoom);
        chatRoomMemberRepository.save(chatRoomMember);
    }


}
