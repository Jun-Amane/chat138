/**
 * Package: moe.zzy040330.chat138.service.impl
 * File: GenericCrudServiceImpl.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 10:08
 * Description: implemented conversation service interface
 */
package moe.zzy040330.chat138.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import moe.zzy040330.chat138.entity.Conversation;
import moe.zzy040330.chat138.mapper.ConversationMapper;
import moe.zzy040330.chat138.mapper.GenericMapper;
import moe.zzy040330.chat138.service.ConversationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl extends GenericCrudServiceImpl<Conversation, Long> implements ConversationService {

    private final ConversationMapper conversationMapper;

    public ConversationServiceImpl(GenericMapper<Conversation, Long> genericMapper, ConversationMapper conversationMapper) {
        super(genericMapper);
        this.conversationMapper = conversationMapper;
    }

    /**
     * Retrieves a Conversation by its unique UUID.
     *
     * @param uuid the unique UUID identifying the conversation
     * @return the Conversation object if found, else null
     */
    @Override
    public Conversation findByUuid(String uuid) {
        return conversationMapper.findByUuid(uuid);
    }

    /**
     * Retrieves a paginated list of Conversations associated with a specific user ID.
     *
     * @param userId   the ID of the user whose conversations are being retrieved
     * @param pageNum  the page number for pagination (starting from 1)
     * @param pageSize the number of records per page
     * @return a PageInfo object containing paginated Conversation data
     */
    @Override
    public PageInfo<Conversation> findByUserId(Long userId, Integer pageNum, Integer pageSize) {
        // Set up the pagination configuration
        PageHelper.startPage(pageNum, pageSize);

        // Execute the query with the given parameters
        List<Conversation> conversaions = conversationMapper.findByUserId(userId);

        // Wrap the result with PageInfo for pagination information
        return new PageInfo<>(conversaions);
    }
}
