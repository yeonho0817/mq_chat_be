package com.mq.chat.data.vo.resVo;

import com.mq.chat.data.entity.Emoticon;
import com.mq.chat.data.entity.EmoticonGroup;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class EmoticonListResVo {
    private List<EmoticonGroupItem> emoticonGroupItems;

    @Getter
    private static class EmoticonGroupItem {
        private Long id;
        private String name;
        private List<EmoticonItem> emoticonItems;

        @Getter
        private static class EmoticonItem {
            private Long id;
            private String name;
            private String imageUrl;

            public EmoticonItem(Emoticon emoticon) {
                this.id = emoticon.getId();
                this.name = emoticon.getName();
                this.imageUrl = emoticon.getImageUrl();
            }
        }

        public EmoticonGroupItem(EmoticonGroup emoticonGroup, List<Emoticon> emoticons) {
            this.id = emoticonGroup.getId();
            this.name = emoticonGroup.getName();
            this.emoticonItems = emoticons.stream().map(EmoticonItem::new).collect(Collectors.toList());
        }
    }

    public EmoticonListResVo(Map<EmoticonGroup, List<Emoticon>> emoticonGroupList) {
        this.emoticonGroupItems = emoticonGroupList.entrySet()
                .stream().map(entry -> new EmoticonGroupItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
