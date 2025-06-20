package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteChatResponse {
    private Boolean success;
    private String message;

    public DeleteChatResponse() {
    }

    public DeleteChatResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
