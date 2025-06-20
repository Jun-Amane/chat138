// 消息结构，用户发送请求时用
export interface MessageRequest {
    message: string;
}

// 单条消息 DTO，后端返回给前端
export interface MessageDto {
    role: 'user' | 'assistant' | 'error';
    content: string;
}

// 新建对话接口返回
export interface NewChatResponse {
    uuid: string;
    messages: MessageDto[];
}

// 继续对话返回（不带 uuid）
export interface ContinueChatResponse {
    messages: MessageDto[];
}

// 对话详情
export interface ChatDetail {
    uuid: string;
    summary: string;
    messages: MessageDto[];
}

// 对话列表项
export interface ChatListItem {
    uuid: string;
    summary: string;
    createTime: string; // ISO 时间字符串
    updateTime: string;
}

// 对话列表分页响应
export interface ChatListResponse {
    page: number;
    size: number;
    total: number;
    data: ChatListItem[];
}

// 分页查询参数
export interface ChatListQueryParams {
    page?: number;
    size?: number;
}
