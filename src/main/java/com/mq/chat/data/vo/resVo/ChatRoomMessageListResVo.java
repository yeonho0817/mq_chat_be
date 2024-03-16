package com.mq.chat.data.vo.resVo;

import com.mq.chat.data.entity.ChatRoom;
import com.mq.chat.data.entity.Member;
import com.mq.chat.data.entity.Message;
import com.mq.chat.data.enumerate.MessageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ChatRoomMessageListResVo {
    private Long chatRoomId;
    private List<MessageItem> messageItems;

    @Getter
    private static class MessageItem {
        private final Long id;
        private final boolean me;
        private final Long memberId;
        private final String memberNickname;
        private final String message;
        private final boolean emoticon;
        private final LocalDateTime createdTime;

        public MessageItem(Message message, Member member) {
            this.id = message.getId();
            this.me = message.getMember().getId().equals(member.getId());
            this.memberId = message.getMember().getId();
            this.memberNickname = message.getMember().getNickname();
            this.message = message.getMessage();
            this.emoticon = MessageType.emoticon.equals(message.getMessageType());
            this.createdTime = message.getCreated();
        }
    }

    public ChatRoomMessageListResVo(Member member, ChatRoom chatRoom, List<Message> messages) {
        this.chatRoomId = chatRoom.getId();

        List<MessageItem> items = new ArrayList<>();
        messages.forEach(message -> items.add(new MessageItem(message, member)));
        this.messageItems = items;
    }
}
