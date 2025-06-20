# chat138：基于Qwen 2.5 LLM的农业病害问答诊断系统
SDAU 2025 Summer Training Project: Spring AI &amp; Java Web

## 项目组成员

- 陈文强（组长）：计算机2201
- 周子钰（Git仓库所有者）：计算机2201
- 宋扬：计算机2202
- 孙熙鉴：计算机2201
- 于安吉：计算机2201

本项目采用GPG签名，欢迎验证签名：

- UID: `Ziyu Zhou (Nyan~) <jun@zzy040330.moe>`
- 公钥指纹：`EF19615B0C1530190EAA34616DF9993ADD071EFE`
- 公钥地址：https://www.zzy040330.moe/ziyu_zhou_pubkey.asc

## 项目简介
Chat138 是一个基于 Spring Boot + SpringAI 的 Web AI交互系统，用于农业病害智能诊断，支持多轮对话、上下文管理和 AI 自动回复。

本项目作为山东农业大学2025年夏季实训的成果，展示了前后端分离架构设计，数据库操作，AI 模型调用等综合能力。

- 仓库地址：https://github.com/Jun-Amane/chat138

## 技术栈与版本
- 前端
    * Next.js (最新稳定版)
    * React (最新稳定版)
    * Typescript
- 后端
    * Spring Boot (最新稳定版)
    * SpringAI (1.0.0)
    * MyBatis (最新稳定版)
    * Java (OpenJDK 24)
    * Maven (最新稳定版)
- 数据库
    * MariaDB (MySQL)
    * H2 (单元测试用)

## 目录组织

```bash
.
├── backend     # 后端
├── databases    # 数据库
├── doc         # 文档
│   └── memo.md # 开发规范
├── frontend    # 前端
├── LICENSE     # 开源许可
├── README.md
└── scripts     # 脚本
```

## 数据库设计

| 表名            | 描述   |
| ------------- | ---- |
| chat138_user  | 用户信息 |
| conversation  | 对话会话 |
| chat\_message | 消息内容 |

``` sql
create table chat138db.chat138_user
(
    id           bigint auto_increment comment '主键ID'
        primary key,
    userCode     varchar(15)  null comment '用户编码',
    userName     varchar(15)  null comment '用户名称（唯一）',
    userPassword varchar(255) null comment '用户密码',
    gender       int(10)      null comment '性别（0:未知、1:女、2:男）',
    birthday     date         null comment '出生日期',
    phone        varchar(15)  null comment '手机',
    address      varchar(30)  null comment '地址',
    userRole     int(10)      null comment '用户角色（0:管理员、1:普通用户）',
    createdBy    bigint       null comment '创建者（userId）',
    creationDate datetime     null comment '创建时间',
    modifyBy     bigint       null comment '更新者（userId）',
    modifyDate   datetime     null comment '更新时间'
) collate = utf8mb4_unicode_ci;

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
drop table if exists chat138db.chat138_user;
```

## 开发文档

> 参见`./doc/`

## 部署说明

1. 克隆项目

``` bash
git clone https://github.com/yourname/chat138.git
cd chat138
```

2. 初始化数据库与Ollama模型
3. 修改后端配置文件`application.yaml`
4. 修改前后端端口号（或使用反向代理）
5. 在前端`./frontend`使用`npm`启动
``` bash
npm run
```
6. 启动后端

``` bash
mvn spring-boot:run
```

7. 默认管理员用户名为`admin`，密码为`admin`


## 运行截图

## 开源许可

本软件是自由软件，按照GNU GPL-3.0许可证开源，可以自由分发。

具体参考`LICENSE`文件


rev. 1906 by Jun.
