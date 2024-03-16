package com.mq.chat.data.vo.resVo;

import com.mq.chat.data.entity.InviteChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class InviteChatRoomListResVo {
    private List<InviteChatRoomItem> inviteChatRoomItems;

    @Getter
    private static class InviteChatRoomItem {
        private final Long id;
        private final Long chatRoomId;
        private final String chatRoomName;
        private final Integer currentCount;
        private final Integer limitCount;
        private final String memberNickname;
        private final boolean accepted;

        public InviteChatRoomItem(InviteChatRoom inviteChatRoom) {
            this.id = inviteChatRoom.getId();
            this.chatRoomId = inviteChatRoom.getChatRoom().getId();
            this.chatRoomName = inviteChatRoom.getChatRoom().getName();
            this.currentCount = inviteChatRoom.getChatRoom().getCurrentCount();
            this.limitCount = inviteChatRoom.getChatRoom().getLimitCount();
            this.memberNickname = inviteChatRoom.getMember().getNickname();
            this.accepted = inviteChatRoom.isAccepted();
        }
    }

    public InviteChatRoomListResVo(List<InviteChatRoom> inviteChatRooms) {
        List<InviteChatRoomItem> item = new ArrayList<>();

        inviteChatRooms.forEach(inviteChatRoom -> item.add(new InviteChatRoomItem(inviteChatRoom)));
        this.inviteChatRoomItems = item;
    }

}
