/**
 * Package: moe.zzy040330.chat138.controller
 * File: ChatController.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 10:41
 * Description: RESTful API for AI chat.
 */
package moe.zzy040330.chat138.controller;

import moe.zzy040330.chat138.dto.*;
import moe.zzy040330.chat138.entity.ChatMessage;
import moe.zzy040330.chat138.entity.Conversation;
import moe.zzy040330.chat138.service.AIChatService;
import moe.zzy040330.chat138.service.ChatMessageService;
import moe.zzy040330.chat138.service.ConversationService;
import moe.zzy040330.chat138.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ConversationService conversationService;

    private final ChatMessageService chatMessageService;

    private final AIChatService aiChatService;

    private final JwtService jwtService;

    public ChatController(ConversationService conversationService, ChatMessageService chatMessageService, AIChatService aiChatService, JwtService jwtService) {
        this.conversationService = conversationService;
        this.chatMessageService = chatMessageService;
        this.aiChatService = aiChatService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> createChat(@RequestBody ChatRequest request, @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            var currentUser = jwtService.extractUserId(token);

            // new Conversation
            Conversation conversation = new Conversation();
            conversation.setUuid(UUID.randomUUID().toString());
            conversation.setSummary(request.getMessage());
            conversation.setUserId(currentUser);
            conversationService.insert(conversation);

            // call AI
            String aiReply = aiChatService.chatWithAI(conversation.getUuid(), request.getMessage());

            // save response
            CreateChatResponse response = new CreateChatResponse();
            response.setUuid(conversation.getUuid());
            response.setMessages(List.of(
                    new ChatMessageDto("user", request.getMessage()),
                    new ChatMessageDto("assistant", aiReply)
            ));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(500, "Internal Server Errror") + e.getMessage());
        }

    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getChatHistory(@PathVariable String uuid, @RequestHeader("Authorization") String authHeader) {
        try {

            String token = authHeader.substring(7);
            var currentUser = jwtService.extractUserId(token);

            Conversation conversation = conversationService.findByUuid(uuid);
            if (conversation == null) {
                return ResponseEntity.notFound().build();
            }

            if (!conversation.getUserId().equals(currentUser)) {
                return ResponseEntity.notFound().build();
            }

            List<ChatMessage> messageList = chatMessageService.findByConversationId(conversation.getId());

            ChatHistoryResponse response = new ChatHistoryResponse();
            response.setUuid(conversation.getUuid());
            response.setSummary(conversation.getSummary());
            response.setMessages(messageList.stream()
                    .map(msg -> new ChatMessageDto(msg.getRole(), msg.getContent()))
                    .collect(Collectors.toList()));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(500, "Internal Server Error") + e.getMessage());
        }

    }

    @GetMapping
    public ChatListResponse getChatList(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestHeader("Authorization") String authHeader) {


        String token = authHeader.substring(7);
        var currentUser = jwtService.extractUserId(token);

        var pageInfo = conversationService.findByUserId(currentUser, page, size);

        var chatListResponse = new ChatListResponse();
        chatListResponse.setPage(pageInfo.getPageNum());
        chatListResponse.setSize(pageInfo.getPageSize());
        chatListResponse.setTotal(pageInfo.getTotal());
        var allConversations = pageInfo.getList();

        List<ChatListItemDto> data = allConversations.stream()
                .map(c -> {
                    ChatListItemDto dto = new ChatListItemDto();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                    dto.setUuid(c.getUuid());
                    dto.setSummary(c.getSummary());
                    dto.setCreateTime(sdf.format(c.getCreateTime()));
                    dto.setUpdateTime(sdf.format(c.getUpdateTime()));

                    return dto;
                }).collect(Collectors.toList());

        chatListResponse.setData(data);

        return chatListResponse;
    }


    @PostMapping("/{uuid}")
    public ResponseEntity<?> continueChat(@PathVariable String uuid,
                                             @RequestBody ChatRequest request,
                                          @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            var currentUser = jwtService.extractUserId(token);

            Conversation conversation = conversationService.findByUuid(uuid);
            if (conversation == null) {
                return ResponseEntity.notFound().build();
            }

            if (!conversation.getUserId().equals(currentUser)) {
                return ResponseEntity.notFound().build();
            }

            String aiReply = aiChatService.chatWithAI(uuid, request.getMessage());

            ContinueChatResponse response = new ContinueChatResponse();
            response.setMessages(List.of(
                    new ChatMessageDto("user", request.getMessage()),
                    new ChatMessageDto("assistant", aiReply)
            ));

            return ResponseEntity.ok(response);
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(500, "Internal Server Error") + e.getMessage());
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteChat(@PathVariable String uuid, @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            var currentUser = jwtService.extractUserId(token);

            Conversation conversation = conversationService.findByUuid(uuid);
            if (conversation == null) {
                return ResponseEntity.notFound().build();
            }

            if (!conversation.getUserId().equals(currentUser)) {
                return ResponseEntity.notFound().build();
            }

            chatMessageService.deleteByConversationId(conversation.getId());
            conversationService.deleteById(conversation.getId());

            DeleteChatResponse response = new DeleteChatResponse();
            response.setSuccess(true);
            response.setMessage("success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(500, "Internal server error: ") + e.getMessage());
        }

    }
}
