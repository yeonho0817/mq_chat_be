package com.mq.chat.data.vo.resVo;

import com.mq.chat.data.entity.ChatRoomMember;
import com.mq.chat.data.entity.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberListResVo {
    private List<MemberItem> memberItems;

    @Getter
    private static class MemberItem {
        private final Long id;
        private final String nickname;
        private final boolean alreadyIn;

        public MemberItem(Long id, String nickname, boolean alreadyIn) {
            this.id = id;
            this.nickname = nickname;
            this.alreadyIn = alreadyIn;
        }
    }

    public MemberListResVo(List<Member> memberList, List<ChatRoomMember> chatRoomMembers) {
        List<MemberItem> items = new ArrayList<>();

        memberList.forEach(item ->  {
            boolean alreadyIn = false;
            if(chatRoomMembers.stream().map(ChatRoomMember::getMember).anyMatch(member -> member.getId().equals(item.getId()))) {
                alreadyIn = true;
            }

            items.add(new MemberItem(item.getId(), item.getNickname(), alreadyIn));
        });
        this.memberItems = items;
    }
}
