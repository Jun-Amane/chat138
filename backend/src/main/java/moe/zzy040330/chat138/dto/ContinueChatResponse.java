package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContinueChatResponse {
    private List<ChatMessageDto> messages;

    public ContinueChatResponse() {
    }

    public ContinueChatResponse(List<ChatMessageDto> messages) {
        this.messages = messages;
    }
}
