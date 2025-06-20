CREATE TABLE conversation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid CHAR(36) NOT NULL UNIQUE COMMENT '对话唯一标识，UUID',
    user_id BIGINT NOT NULL COMMENT '用户外键',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    summary VARCHAR(255) DEFAULT NULL COMMENT '对话摘要，可选',

    CONSTRAINT fk_conversation_user FOREIGN KEY (user_id) REFERENCES chat138_user(id)
) collate = utf8mb4_unicode_ci;



CREATE TABLE chat_message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conversation_id BIGINT NOT NULL COMMENT '关联的对话ID',
    role ENUM('user', 'assistant', 'system') NOT NULL COMMENT '消息角色',
    content TEXT NOT NULL COMMENT '消息内容',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',

    -- 外键约束
    CONSTRAINT fk_chat_message_conversation FOREIGN KEY (conversation_id) REFERENCES conversation(id)
        ON DELETE CASCADE
) collate = utf8mb4_unicode_ci;
