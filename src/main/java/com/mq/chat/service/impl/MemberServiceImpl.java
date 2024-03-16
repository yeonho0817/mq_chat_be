package com.mq.chat.service.impl;

import com.mq.chat.data.entity.ChatRoom;
import com.mq.chat.data.entity.ChatRoomMember;
import com.mq.chat.data.entity.Member;
import com.mq.chat.data.repository.ChatRoomMemberQueryRepository;
import com.mq.chat.data.repository.ChatRoomRepository;
import com.mq.chat.data.repository.MemberRepository;
import com.mq.chat.data.vo.LoginReqVo;
import com.mq.chat.data.vo.SignUpReqVo;
import com.mq.chat.data.vo.resVo.LoginResVo;
import com.mq.chat.data.vo.resVo.MemberListResVo;
import com.mq.chat.error.Error;
import com.mq.chat.error.ErrorSpec;
import com.mq.chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.encoder.org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberQueryRepository chatRoomMemberQueryRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public LoginResVo login(LoginReqVo reqVo) {
        Member member = memberRepository.findByMemberId(reqVo.getMemberId())
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistMemberId));

        if(!bCryptPasswordEncoder.matches(reqVo.getPassword(), member.getPassword())) {
            throw Error.of(ErrorSpec.NotExistPassword);
        }

        return new LoginResVo(member.getId());
    }

    @Transactional
    @Override
    public void signUp(SignUpReqVo reqVo) {
        Member member = memberRepository.findByMemberIdOrNickname(reqVo.getMemberId(), reqVo.getNickname())
                .orElse(null);

        if(ObjectUtils.isNotEmpty(member)) {
            if(member.getMemberId().equals(reqVo.getMemberId())) {
                throw Error.of(ErrorSpec.AlreadyExistMemberId);
            }
            if(member.getNickname().equals(reqVo.getNickname())) {
                throw Error.of(ErrorSpec.AlreadyExistNickname);
            }
        }

        memberRepository.save(new Member(reqVo.getMemberId(), bCryptPasswordEncoder.encode(reqVo.getPassword()),
                reqVo.getName(), reqVo.getNickname(), reqVo.getPhoneNumber()));
    }

    @Transactional(readOnly = true)
    @Override
    public MemberListResVo list(Long chatRoomId) {
        List<Member> memberList = memberRepository.findAllByOrderById();

        ChatRoom chatRoom = chatRoomRepository.findByIdAndDeleted(chatRoomId, false)
                .orElseThrow(() -> Error.of(ErrorSpec.NotExistChatRoom));
        List<ChatRoomMember> chatRoomMembers = chatRoomMemberQueryRepository.findByChatRoomAndDeleted(chatRoom, false);

        return new MemberListResVo(memberList, chatRoomMembers);
    }
}
