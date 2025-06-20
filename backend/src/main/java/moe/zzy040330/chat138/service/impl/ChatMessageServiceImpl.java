/**
 * Package: moe.zzy040330.chat138.service.impl
 * File: ChatMessageServiceImpl.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 10:12
 * Description: implemented message service interface
 */
package moe.zzy040330.chat138.service.impl;

import moe.zzy040330.chat138.entity.ChatMessage;
import moe.zzy040330.chat138.mapper.ChatMessageMapper;
import moe.zzy040330.chat138.mapper.GenericMapper;
import moe.zzy040330.chat138.service.ChatMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl extends GenericCrudServiceImpl<ChatMessage, Long> implements ChatMessageService {

    private final ChatMessageMapper chatMessageMapper;

    public ChatMessageServiceImpl(GenericMapper<ChatMessage, Long> genericMapper, ChatMessageMapper chatMessageMapper) {
        super(genericMapper);
        this.chatMessageMapper = chatMessageMapper;
    }

    /**
     * Retrieves all chat messages associated with a specific conversation ID.
     *
     * @param conversationId the ID of the conversation to retrieve messages from
     * @return a list of ChatMessage objects belonging to the specified conversation
     */
    @Override
    public List<ChatMessage> findByConversationId(Long conversationId) {
        return chatMessageMapper.findByConversationId(conversationId);
    }

    /**
     * Deletes all chat messages associated with a specific conversation ID.
     *
     * @param conversationId the ID of the conversation whose messages are to be deleted
     * @return true if deletion is successful, false otherwise
     */
    @Override
    public Boolean deleteByConversationId(Long conversationId) {
        return chatMessageMapper.deleteByConversationId(conversationId) > 0;
    }
}
