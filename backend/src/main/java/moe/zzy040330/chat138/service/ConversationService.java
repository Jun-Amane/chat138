/**
 * Package: moe.zzy040330.chat138.service
 * File: ConversationService.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 12:12
 * Description: This interface defines the service-layer operations specific to
 * the Conversation entity. It extends the GenericCrudService interface for
 * basic CRUD operations and adds additional methods for conversation-specific
 * business logic, including retrieval by UUID and paginated queries by user.
 */

package moe.zzy040330.chat138.service;

import com.github.pagehelper.PageInfo;
import moe.zzy040330.chat138.entity.Conversation;

/**
 * ConversationService is responsible for handling the business logic
 * associated with the Conversation entity. It provides operations for
 * retrieving conversations by their UUID and for querying user-specific
 * conversations with pagination support.
 */
public interface ConversationService extends GenericCrudService<Conversation, Long> {

    /**
     * Retrieves a Conversation by its unique UUID.
     *
     * @param uuid the unique UUID identifying the conversation
     * @return the Conversation object if found, else null
     */
    Conversation findByUuid(String uuid);

    /**
     * Retrieves a paginated list of Conversations associated with a specific user ID.
     *
     * @param userId   the ID of the user whose conversations are being retrieved
     * @param pageNum  the page number for pagination (starting from 1)
     * @param pageSize the number of records per page
     * @return a PageInfo object containing paginated Conversation data
     */
    PageInfo<Conversation> findByUserId(Long userId, Integer pageNum, Integer pageSize);
}
