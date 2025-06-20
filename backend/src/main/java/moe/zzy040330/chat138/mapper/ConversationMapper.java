/**
 * Package: moe.zzy040330.chat138.mapper
 * File: ConversationMapper.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 08:09
 * Description: This interface defines the database operations specific to
 * the Conversation entity. It extends the GenericMapper interface for basic CRUD
 * operations and adds additional methods tailored to conversation-specific queries.
 */

package moe.zzy040330.chat138.mapper;

import moe.zzy040330.chat138.entity.Conversation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ConversationMapper is responsible for handling SQL operations for the Conversation entity.
 * It provides functionalities to find conversations by UUID and retrieve conversations by user.
 */
@Mapper
public interface ConversationMapper extends GenericMapper<Conversation, Long> {

    /**
     * Finds a Conversation by its unique UUID.
     *
     * @param uuid the unique UUID associated with the conversation
     * @return the Conversation object if found, else null
     */
    Conversation findByUuid(@Param("uuid") String uuid);

    /**
     * Retrieves all conversations associated with a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of conversations belonging to the specified user
     */
    List<Conversation> findByUserId(@Param("userId") Long userId);
}
