/**
 * Package: moe.zzy040330.chat138.mapper
 * File: ChatMessageMapper.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 08:09
 * Description: This interface defines the database operations specific to
 * the ChatMessage entity. It extends the GenericMapper interface for basic CRUD
 * operations and adds additional methods tailored to chat message-specific queries.
 */

package moe.zzy040330.chat138.mapper;

import moe.zzy040330.chat138.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ChatMessageMapper is responsible for handling SQL operations for the ChatMessage entity.
 * It provides functionalities to retrieve and delete chat messages based on conversation context.
 */
@Mapper
public interface ChatMessageMapper extends GenericMapper<ChatMessage, Long> {

    /**
     * Retrieves all chat messages associated with a specific conversation ID.
     *
     * @param conversationId the ID of the conversation
     * @return a list of chat messages belonging to the specified conversation
     */
    List<ChatMessage> findByConversationId(@Param("conversationId") Long conversationId);

    /**
     * Deletes all chat messages associated with a specific conversation ID.
     *
     * @param conversationId the ID of the conversation
     * @return the number of rows affected by the delete operation
     */
    Integer deleteByConversationId(@Param("conversationId") Long conversationId);
}
