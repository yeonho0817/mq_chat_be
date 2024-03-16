package com.mq.chat.service.impl;

import com.mq.chat.data.entity.ChatRoom;
import com.mq.chat.data.entity.ChatRoomMember;
import com.mq.chat.data.entity.InviteChatRoom;
import com.mq.chat.data.entity.Member;
import com.mq.chat.data.repository.*;
import com.mq.chat.data.vo.ChatInviteReqVo;
import com.mq.chat.data.vo.resVo.InviteChatRoomListResVo;
import com.mq.chat.error.Error;
import com.mq.chat.error.ErrorSpec;
import com.mq.chat.service.InviteService;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.encoder.org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final InviteChatRoomRepository inviteChatRoomRepository;
    private final InviteChatRoomQueryRepository inviteChatRoomQueryRepository;

    @Transactional(readOnly = true)
    @Override
    public InviteChatRoomListResVo getInviteList(Long id) {
        Member invitedMember = memberRepository.findById(id)
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));

        return new InviteChatRoomListResVo(inviteChatRoomQueryRepository.findByInvitedMemberIdAndAccepted(invitedMember, false));
    }

    @Transactional
    @Override
    public void invite(Long id, ChatInviteReqVo reqVo) {
        List<InviteChatRoom> existInvites = inviteChatRoomRepository.findByChatRoomIdAndAccepted(reqVo.getChatRoomId(),  false);
        reqVo.getMemberIds().forEach(memberId -> {
            if(existInvites.stream().anyMatch(inviteChatRoom -> inviteChatRoom.getInvitedMember().getId().equals(memberId))) {
                throw Error.of(ErrorSpec.AlreadyInviteChatRoom);
            }
        });

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));
        ChatRoom chatRoom = chatRoomRepository.findById(reqVo.getChatRoomId())
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistChatRoom));


        List<InviteChatRoom> items = new ArrayList<>();

        reqVo.getMemberIds().forEach(memberId -> {
            Member invitedMember = memberRepository.findById(memberId)
                    .orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));

            items.add(new InviteChatRoom(chatRoom, member, invitedMember, false));
        });

        inviteChatRoomRepository.saveAll(items);
    }

    @Transactional
    @Override
    public void inviteAccepted(Long id, Long inviteChatRoomId, Boolean accepted) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistLoginAccount));
        InviteChatRoom inviteChatRoom = inviteChatRoomQueryRepository.findById(inviteChatRoomId);
        if(ObjectUtils.isEmpty(inviteChatRoom)) throw Error.of(ErrorSpec.NotExistInviteChatRoom);
        if(!member.equals(inviteChatRoom.getInvitedMember())) throw Error.of(ErrorSpec.NotEqualInviteMember);

        inviteChatRoom.setAccepted(accepted);
        if(accepted) {
            chatRoomMemberRepository.save(new ChatRoomMember(inviteChatRoom.getChatRoom(), member, false));
            inviteChatRoom.getChatRoom().setPlusCurrentCount();
        }

    }
}
