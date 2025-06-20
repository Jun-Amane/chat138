package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatHistoryResponse {
    private String uuid;
    private String summary;
    private List<ChatMessageDto> messages;

    public ChatHistoryResponse(String uuid, String summary, List<ChatMessageDto> messages) {
        this.uuid = uuid;
        this.summary = summary;
        this.messages = messages;
    }

    public ChatHistoryResponse() {
    }

}
