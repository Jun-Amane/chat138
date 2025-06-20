/**
 * Package: moe.zzy040330.chat138.entity
 * File: ChatMessage.java
 * Author: Ziyu ZHOU
 * Date: 19/06/2025
 * Time: 12:34
 * Description: This class represents a Conversation (session of messages) entity with basic information.
 */

package moe.zzy040330.chat138.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Conversation {
        private Long id;
        private String uuid;
        private Long userId;
        private Date createTime;
        private Date updateTime;
        private String summary;

        public Conversation() {
        }

        public Conversation(Long id, String uuid, Long userId, Date createTime, Date updateTime, String summary) {
                this.id = id;
                this.uuid = uuid;
                this.userId = userId;
                this.createTime = createTime;
                this.updateTime = updateTime;
                this.summary = summary;
        }


}
