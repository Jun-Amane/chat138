package moe.zzy040330.chat138.service;

import moe.zzy040330.chat138.entity.Conversation;
import moe.zzy040330.chat138.entity.User;
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
public class ConversationServiceTest {

    @Autowired
    private ConversationService conversationService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Long testConversationId;
    private String testConversationUuid;
    private Long testUserId;
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
        testUserId = testUser.getId();

        Conversation conversation = new Conversation();
        conversation.setUuid(UUID.randomUUID().toString());
        conversation.setUserId(testUserId);
        conversation.setCreateTime(new Date());
        conversation.setUpdateTime(new Date());
        conversation.setSummary("Test Conversation");

        conversationService.insert(conversation);
        testConversationId = conversation.getId();
        testConversationUuid = conversation.getUuid();

        assertNotNull(testConversationId);
    }

    @Test
    public void testFindById() {
        Conversation conversation = conversationService.findById(testConversationId);
        assertNotNull(conversation);
        assertEquals("Test Conversation", conversation.getSummary());
    }

    @Test
    public void testFindByUuid() {
        Conversation conversation = conversationService.findByUuid(testConversationUuid);
        assertNotNull(conversation);
        assertNotNull(conversation.getUserId());
        assertEquals(testConversationId, conversation.getId());
    }

    @Test
    public void testFindByUserId() {
        List<Conversation> conversations = conversationService.findByUserId(testUserId, 1, 10).getList();
        assertFalse(conversations.isEmpty());
    }

    @Test
    public void testUpdate() {
        Conversation conversation = conversationService.findById(testConversationId);
        conversation.setSummary("Updated Summary");
        conversation.setUpdateTime(new Date());

        Boolean result = conversationService.update(conversation);
        assertTrue(result);

        Conversation updated = conversationService.findById(testConversationId);
        assertEquals("Updated Summary", updated.getSummary());
    }

    @Test
    public void testDeleteById() {
        Boolean result = conversationService.deleteById(testConversationId);
        assertTrue(result);

        Conversation deleted = conversationService.findById(testConversationId);
        assertNull(deleted);
    }

    @Test
    public void testFindAll() {
        List<Conversation> conversations = conversationService.findAll();
        assertFalse(conversations.isEmpty());
    }
}
