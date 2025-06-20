package moe.zzy040330.chat138.service;

import moe.zzy040330.chat138.entity.ChatMessage;
import moe.zzy040330.chat138.entity.Conversation;
import moe.zzy040330.chat138.entity.User;
import moe.zzy040330.chat138.service.ChatMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ChatMessageServiceTest {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ChatMessageService  chatMessageService;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Long conversationId;
    private final User modifiedByUser = new User();

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("DELETE FROM chat_message");
        jdbcTemplate.execute("DELETE FROM conversation");
        jdbcTemplate.execute("DELETE FROM chat138_user");

        User testUser = new User();
        testUser.setCode("testCode");
        testUser.setName("Test User");
        testUser.setPassword(passwordEncoder.encode("password"));
        testUser.setGender(1);
        testUser.setBirthday(new Date(946684800000L));
        testUser.setPhone("1234567890");
        testUser.setAddress("Test Address");
        modifiedByUser.setId(1L);
        testUser.setModifiedBy(modifiedByUser);
        testUser.setModificationDate(new Date());
        testUser.setCreatedBy(modifiedByUser);
        testUser.setCreationDate(new Date());

        userService.insert(testUser);
        Long testUserId = testUser.getId();

        Conversation conversation = new Conversation();
        conversation.setUuid(UUID.randomUUID().toString());
        conversation.setUserId(testUserId);
        conversation.setCreateTime(new Date());
        conversation.setUpdateTime(new Date());
        conversation.setSummary("Test Conversation");
        conversationService.insert(conversation);
        conversationId = conversation.getId();

        assertNotNull(conversationId);
    }

    @Test
    public void testInsertAndFindById() {
        ChatMessage message = new ChatMessage();
        message.setConversationId(conversationId);
        message.setRole("user");
        message.setContent("Hello AI");
        message.setCreateTime(new Date());

        Boolean result = chatMessageService.insert(message);
        assertTrue(result);
        assertNotNull(message.getId());

        ChatMessage fetched = chatMessageService.findById(message.getId());
        assertNotNull(fetched);
        assertEquals("Hello AI", fetched.getContent());
    }

    @Test
    public void testFindByConversationId() {
        ChatMessage message = new ChatMessage();
        message.setConversationId(conversationId);
        message.setRole("assistant");
        message.setContent("Hi, how can I help you?");
        message.setCreateTime(new Date());
        chatMessageService.insert(message);

        List<ChatMessage> messages = chatMessageService.findByConversationId(conversationId);
        assertFalse(messages.isEmpty());
    }

    @Test
    public void testUpdate() {
        ChatMessage message = new ChatMessage();
        message.setConversationId(conversationId);
        message.setRole("user");
        message.setContent("Original Content");
        message.setCreateTime(new Date());
        chatMessageService.insert(message);

        message.setContent("Updated Content");
        Boolean result = chatMessageService.update(message);
        assertTrue(result);

        ChatMessage updated = chatMessageService.findById(message.getId());
        assertEquals("Updated Content", updated.getContent());
    }

    @Test
    public void testDeleteById() {
        ChatMessage message = new ChatMessage();
        message.setConversationId(conversationId);
        message.setRole("user");
        message.setContent("To be deleted");
        message.setCreateTime(new Date());
        chatMessageService.insert(message);

        Boolean result = chatMessageService.deleteById(message.getId());
        assertTrue(result);

        ChatMessage deleted = chatMessageService.findById(message.getId());
        assertNull(deleted);
    }

    @Test
    public void testDeleteByConversationId() {
        ChatMessage message1 = new ChatMessage();
        message1.setConversationId(conversationId);
        message1.setRole("user");
        message1.setContent("Message 1");
        message1.setCreateTime(new Date());

        ChatMessage message2 = new ChatMessage();
        message2.setConversationId(conversationId);
        message2.setRole("assistant");
        message2.setContent("Message 2");
        message2.setCreateTime(new Date());

        chatMessageService.insert(message1);
        chatMessageService.insert(message2);

        Boolean result = chatMessageService.deleteByConversationId(conversationId);
        assertTrue(result);

        List<ChatMessage> remaining = chatMessageService.findByConversationId(conversationId);
        assertTrue(remaining.isEmpty());
    }

    @Test
    public void testFindAll() {
        ChatMessage message = new ChatMessage();
        message.setConversationId(conversationId);
        message.setRole("user");
        message.setContent("Test Find All");
        message.setCreateTime(new Date());
        chatMessageService.insert(message);

        List<ChatMessage> all = chatMessageService.findAll();
        assertFalse(all.isEmpty());
    }
}
