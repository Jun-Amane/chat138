DROP TABLE IF EXISTS chat138_user;

CREATE TABLE chat138_user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userCode VARCHAR(15) DEFAULT NULL,
    userName VARCHAR(15) DEFAULT NULL,
    userPassword VARCHAR(255) DEFAULT NULL,
    gender INT DEFAULT NULL,
    birthday DATE DEFAULT NULL,
    phone VARCHAR(15) DEFAULT NULL,
    address VARCHAR(30) DEFAULT NULL,
    userRole INT DEFAULT NULL,
    createdBy BIGINT DEFAULT NULL,
    creationDate DATETIME DEFAULT NULL,
    modifyBy BIGINT DEFAULT NULL,
    modifyDate DATETIME DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE conversation (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              uuid CHAR(36) NOT NULL UNIQUE COMMENT '对话唯一标识，UUID',
                              user_id BIGINT NOT NULL COMMENT '用户外键',
                              create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              summary VARCHAR(255) DEFAULT NULL COMMENT '对话摘要，可选',

                              CONSTRAINT fk_conversation_user FOREIGN KEY (user_id) REFERENCES chat138_user(id)
);



CREATE TABLE chat_message (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              conversation_id BIGINT NOT NULL COMMENT '关联的对话ID',
                              role ENUM('user', 'assistant', 'system') NOT NULL COMMENT '消息角色',
                              content TEXT NOT NULL COMMENT '消息内容',
                              create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',

    -- 外键约束
                              CONSTRAINT fk_chat_message_conversation FOREIGN KEY (conversation_id) REFERENCES conversation(id)
                                  ON DELETE CASCADE
);
