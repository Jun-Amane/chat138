/**
 * Package: moe.zzy040330.chat138.service
 * File: ChatMessageService.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 12:34
 * Description: This interface defines the service operations specific to
 * the ChatMessage entity. It extends the GenericCrudService interface for basic CRUD
 * operations and adds additional methods tailored to chat message-specific business logic.
 */

package moe.zzy040330.chat138.service;

import moe.zzy040330.chat138.entity.ChatMessage;

import java.util.List;

/**
 * ChatMessageService provides business-level operations for managing chat messages.
 * It supports CRUD operations as well as additional methods for managing messages
 * within a specific conversation context.
 */
public interface ChatMessageService extends GenericCrudService<ChatMessage, Long> {

    /**
     * Retrieves all chat messages associated with a specific conversation ID.
     *
     * @param conversationId the ID of the conversation to retrieve messages from
     * @return a list of ChatMessage objects belonging to the specified conversation
     */
    List<ChatMessage> findByConversationId(Long conversationId);

    /**
     * Deletes all chat messages associated with a specific conversation ID.
     *
     * @param conversationId the ID of the conversation whose messages are to be deleted
     * @return true if deletion is successful, false otherwise
     */
    Boolean deleteByConversationId(Long conversationId);
}
