package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private String role;    // user / assistant
    private String content;

    public ChatMessageDto() {
    }

    public ChatMessageDto(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
