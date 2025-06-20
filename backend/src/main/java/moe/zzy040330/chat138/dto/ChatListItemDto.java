package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatListItemDto {
    private String uuid;
    private String summary;
    private String createTime;
    private String updateTime;

    public ChatListItemDto() {
    }

    public ChatListItemDto(String uuid, String summary, String createTime, String updateTime) {
        this.uuid = uuid;
        this.summary = summary;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
