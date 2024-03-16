package com.mq.chat.data.vo.resVo;

import com.mq.chat.data.entity.ChatRoom;
import com.mq.chat.data.entity.ChatRoomMember;
import com.mq.chat.data.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ChatRoomListResVo {
    private List<ChatRoomItem> chatRoomItems;
    private Integer invitedCount;

    @RequiredArgsConstructor
    @Getter
    private static class ChatRoomItem {
        private final Long chatRoomMemberId;
        private final Long chatRoomId;

        private final Integer currentCount;
        private final Integer limitCount;
        private final String chatRoomName;
        private final String chatRoomDescription;

        public ChatRoomItem(ChatRoomMember chatRoomMember) {
            this.chatRoomMemberId = chatRoomMember.getId();
            this.chatRoomId = chatRoomMember.getChatRoom().getId();
            this.currentCount = chatRoomMember.getChatRoom().getCurrentCount();
            this.limitCount = chatRoomMember.getChatRoom().getLimitCount();
            this.chatRoomName = chatRoomMember.getChatRoom().getName();
            this.chatRoomDescription = chatRoomMember.getChatRoom().getDescription();
        }
    }

    public ChatRoomListResVo(List<ChatRoomMember> chatRoomMembers, Integer invitedCount) {
        List<ChatRoomItem> items = new ArrayList<>();

        chatRoomMembers.forEach(chatRoomMember -> {
            items.add(new ChatRoomItem(chatRoomMember));
        });
        this.chatRoomItems = items;
        this.invitedCount = invitedCount;
    }
}
