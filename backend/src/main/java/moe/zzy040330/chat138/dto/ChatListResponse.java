package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatListResponse {
    private Integer page;
    private Integer size;
    private Long total;
    private List<ChatListItemDto> data;

    public ChatListResponse() {
    }

    public ChatListResponse(Integer page, Integer size, Long total, List<ChatListItemDto> data) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
    }
}
