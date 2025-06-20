## 关于git

- 仓库地址：https://github.com/Jun-Amane/chat138

### 目录组织

```bash
.
├── backend     # 后端
├── databases    # 数据库
├── doc         # 文档
├── frontend    # 前端
├── LICENSE     # 开源许可
├── README.md
└── scripts     # 脚本
```

## 开发规范

- ***使用UTF-8！***
- ***变量、函数、类名等命名使用英文，并遵循所使用编程语言中的命名规则（例如Java中的驼峰命名法）。***
- Windows系统下新建的文件使用LF而不是CRLF。
- 尽量用可用的设计模式
- 保持代码的自动格式化，使用IDE或工具提供的格式化功能，保持缩进、空格等风格的一致。
- 在关键位置添加注释，**特别是在可能造成困惑或需要解释的地方**，尽量**使用英文**注释。
- 使用**JavaDoc**风格的注释，为类、方法和重要逻辑块提供明确的描述。（IntelliJ IDEA可以自动生成）
- 文件头
    * Intellij IDEA中可以自动生成，添加File Template模板
    ```java
    /**
     * Package: ${PACKAGE_NAME}
     * File: ${NAME}.java
     * Author: Ziyu ZHOU
     * Date: ${DAY}/${MONTH}/${YEAR}
     * Time: ${TIME}
     * Description: TODO: change me
     */
    ```
- 每一个后端模块都需要编写相应的单元测试，提交前进行测试。
- 对于RESTful API，可以撰写脚本进行测试，放入`./scripts`目录中。

---

# 技术栈与版本
- 前端
    * Next.js (最新稳定版)
    * React (最新稳定版)
    * Typescript
- 后端
    * Spring Boot (最新稳定版)
    * Java (OpenJDK 24)
    * Maven (最新稳定版)
- 数据库
    * MariaDB (MySQL)

---

## 关于后端开发

### 目录结构
```bash
backend/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── moe
│   │   │       └── zzy040330
│   │   │           └── chat138
│   │   │               ├── Chat138Application.java
│   │   │               ├── config
│   │   │               │   ├── ApplicationConfiguration.java
│   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │               │   └── SecurityConfiguration.java
│   │   │               ├── controller
│   │   │               │   ├── AuthenticationController.java
│   │   │               │   ├── ChatController.java
│   │   │               │   └── UserController.java
│   │   │               ├── dto
│   │   │               │   ├── ChatHistoryResponse.java
│   │   │               │   ├── ChatListItemDto.java
│   │   │               │   ├── ChatListResponse.java
│   │   │               │   ├── ChatMessageDto.java
│   │   │               │   ├── ChatRequest.java
│   │   │               │   ├── ContinueChatResponse.java
│   │   │               │   ├── CreateChatResponse.java
│   │   │               │   ├── DeleteChatResponse.java
│   │   │               │   ├── ErrorResponse.java
│   │   │               │   ├── LoginRequest.java
│   │   │               │   ├── LoginResponse.java
│   │   │               │   ├── PasswordUpdateRequest.java
│   │   │               │   └── UserDto.java
│   │   │               ├── entity
│   │   │               │   ├── ChatMessage.java
│   │   │               │   ├── Conversation.java
│   │   │               │   ├── SecurityUser.java
│   │   │               │   └── User.java
│   │   │               ├── mapper
│   │   │               │   ├── ChatMessageMapper.java
│   │   │               │   ├── ConversationMapper.java
│   │   │               │   ├── GenericMapper.java
│   │   │               │   └── UserMapper.java
│   │   │               ├── service
│   │   │               │   ├── AIChatService.java
│   │   │               │   ├── AuthenticationService.java
│   │   │               │   ├── ChatMessageService.java
│   │   │               │   ├── ConversationService.java
│   │   │               │   ├── GenericCrudService.java
│   │   │               │   ├── impl
│   │   │               │   │   ├── AIChatServiceImpl.java
│   │   │               │   │   ├── AuthenticationServiceImpl.java
│   │   │               │   │   ├── ChatMessageServiceImpl.java
│   │   │               │   │   ├── ConversationServiceImpl.java
│   │   │               │   │   ├── GenericCrudServiceImpl.java
│   │   │               │   │   └── UserServiceImpl.java
│   │   │               │   ├── JwtService.java
│   │   │               │   └── UserService.java
│   │   │               └── utils
│   │   │                   └── DateUtils.java
│   │   └── resources
│   │       ├── application.yaml
│   │       ├── moe
│   │       │   └── zzy040330
│   │       │       └── chat138
│   │       │           └── mapper
│   │       │               ├── ChatMessageMapper.xml
│   │       │               ├── ConversationMapper.xml
│   │       │               └── UserMapper.xml
│   │       ├── static
│   │       └── templates
│   └── test
│       ├── java
│       │   └── moe
│       │       └── zzy040330
│       │           └── chat138
│       │               ├── mapper
│       │               │   └── UserMapperTest.java
│       │               └── service
│       │                   ├── AuthenticationServiceTest.java
│       │                   ├── ChatMessageServiceTest.java
│       │                   ├── ConversationServiceTest.java
│       │                   └── UserServiceTest.java
│       └── resources
│           ├── application-test.yaml
│           └── schema.sql
└── target
```

### 注意事项

- `User`相关实体类、mapper类、service类、controller类已经开发完成，可作参考。

- ***注意所有的TODO注释，可能需要采取操作***
    * 例如`application.yaml`中需要按需配置数据库驱动、地址、用户名和密码。
    * 这类个人设置文件编辑后，***不要上传到git仓库***

- 注意类的继承关系
    * 例如mapper模块中，`GenericMapper`定义了基础的CRUD操作，各实体类的mappers需要继承`GenericMapper`，复用其中的CRUD操作。
    * service模块中，首先需要定义实体类的Service接口，实现`GenericGrudService`接口（同样里面定义了基础的可复用的CRUD操作），再于`service.impl`中实现具体类，并继承`GenericCrudServiceImpl`。具体参见`UserService` 接口与`UserServiceImpl`实现类。

- 注意RESTful API的路径
    * 根路径是`/api`，按照实体分类
    * 例如用户（User）相关API：`/api/user`
    * etc.
    * 用户认证相关路径：
        + 登录：`/auth/login`
        + 退出：`/auth/logout`
    * ***！！！具体细节参见API文档！！！***

- 注意用户认证
    * 除了登录以外，所有API操作都需要认证
    * 使用请求头Authorization中附加的token认证
    * 认证相关已经使用相关Filter实现，不需要额外操作
    * 用户操作需要特别的管理员权限，使用@PreAuthorize注解实现（已经实现）
    * **某些操作需要获取登录用户的身份（例如需要modifiedBy、createdBy参数时）**：
        + 通过`@RequestHeader("Authorization") String authHeader`参数获取请求头的参数
        + 使用`String token = authHeader.substring(7);`获取token字符串
        + 使用`jwtService.extractUserId(token)`获取用户ID

- 注意编写单元测试
    * 每个模块开发完成后，编写单元测试，测试成功后提交至git
    * controller层的RESTful API可以使用Python脚本测试，编写后放到`./scripts`目录中

- 注意使用`org.slf4j.Logger`打印日志，而不是使用`System.out.println()`。

- ***详细设计文档已给出***，参见`./doc/*`


---

rev. 1906 by Jun.
