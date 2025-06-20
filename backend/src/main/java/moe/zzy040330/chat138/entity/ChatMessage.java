/**
 * Package: moe.zzy040330.chat138.entity
 * File: ChatMessage.java
 * Author: Ziyu ZHOU
 * Date: 19/06/2025
 * Time: 12:34
 * Description: This class represents a Chat Message (single) entity with basic information.
 */

package moe.zzy040330.chat138.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ChatMessage {
    private Long id;
    private Long conversationId;
    private String role;         // user / assistant / system
    private String content;
    private Date createTime;

    public ChatMessage() {
    }

    public ChatMessage(Long id, Long conversationId, String role, String content, Date createTime) {
        this.id = id;
        this.conversationId = conversationId;
        this.role = role;
        this.content = content;
        this.createTime = createTime;
    }

}
