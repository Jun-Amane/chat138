package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateChatResponse {
    private String uuid;
    private List<ChatMessageDto> messages;

    public CreateChatResponse() {
    }

    public CreateChatResponse(String uuid, List<ChatMessageDto> messages) {
        this.uuid = uuid;
        this.messages = messages;
    }
}
