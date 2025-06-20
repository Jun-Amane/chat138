import axios from '@/lib/axios';
import {
    MessageRequest,
    NewChatResponse,
    ContinueChatResponse,
    ChatDetail,
    ChatListResponse,
    ChatListQueryParams,
} from '@/types/chat';

export const chatService = {
    // 1. 创建新对话
    createChat: async (messageReq: MessageRequest): Promise<NewChatResponse> => {
        const response = await axios.post('/chat', messageReq);
        return response.data;
    },

    // 2. 获取对话详情（包含所有消息）
    getChatDetail: async (uuid: string): Promise<ChatDetail> => {
        const response = await axios.get(`/chat/${uuid}`);
        return response.data;
    },

    // 3. 获取对话列表（分页）
    getChatList: async (params?: ChatListQueryParams): Promise<ChatListResponse> => {
        const response = await axios.get('/chat', { params });
        return response.data;
    },

    // 4. 继续对话（追加消息）
    continueChat: async (uuid: string, messageReq: MessageRequest): Promise<ContinueChatResponse> => {
        const response = await axios.post(`/chat/${uuid}`, messageReq);
        return response.data;
    },
};
