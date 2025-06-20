package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequest {
    public ChatRequest() {
    }

    private String message;

    public ChatRequest(String message) {
        this.message = message;
    }
}

