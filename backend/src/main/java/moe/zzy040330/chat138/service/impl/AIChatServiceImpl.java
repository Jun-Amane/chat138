/**
 * Package: moe.zzy040330.chat138.service.impl
 * File: AIChatServiceServiceImpl.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 10:12
 * Description: implemented ai chat service interface
 */
package moe.zzy040330.chat138.service.impl;

import moe.zzy040330.chat138.entity.ChatMessage;
import moe.zzy040330.chat138.entity.Conversation;
import moe.zzy040330.chat138.service.AIChatService;
import moe.zzy040330.chat138.service.ChatMessageService;
import moe.zzy040330.chat138.service.ConversationService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AIChatServiceImpl implements AIChatService {

    private final ConversationService conversationService;

    private final ChatMessageService chatMessageService;

    private final ChatClient chatClient;

    public AIChatServiceImpl(ConversationService conversationService, ChatMessageService chatMessageService, OllamaChatModel chatModel) {
        this.conversationService = conversationService;
        this.chatMessageService = chatMessageService;
        this.chatClient = ChatClient.create(chatModel);
    }

    /**
     * Builds the complete conversation prompt by fetching historical messages of the specified conversation.
     * The prompt is formatted appropriately to be fed into the AI model for generating contextual responses.
     *
     * @param conversationUuid the UUID of the conversation for which the context is to be built
     * @return the formatted prompt containing the conversation context
     */
    @Override
    public String buildContextPrompt(String conversationUuid) {
        Conversation conversation = conversationService.findByUuid(conversationUuid);
        if (conversation == null) return "";

        List<ChatMessage> messages = chatMessageService.findByConversationId(conversation.getId());
        return messages.stream()
                .map(msg -> (msg.getRole().equals("user") ? "User: " : "AI: ") + msg.getContent())
                .collect(Collectors.joining("\n"));
    }


    /**
     * Generates an AI response based on the provided user message and existing conversation context.
     * This method handles invoking the AI model and assembling the complete response.
     *
     * @param conversationUuid the UUID of the conversation to maintain context
     * @param userMessage      the message input by the user
     * @return the AI-generated response corresponding to the user input
     */
    @Override
    public String chatWithAI(String conversationUuid, String userMessage) {
        String context = buildContextPrompt(conversationUuid);

        String finalPrompt = context + "\nUser: " + userMessage + "\nAI:";

        String aiResponse = chatClient.prompt().user(finalPrompt).call().content();

        // save user message
        Conversation conversation = conversationService.findByUuid(conversationUuid);
        ChatMessage userMsg = new ChatMessage();
        userMsg.setConversationId(conversation.getId());
        userMsg.setRole("user");
        userMsg.setContent(userMessage);
        chatMessageService.insert(userMsg);

        // save ai message
        ChatMessage aiMsg = new ChatMessage();
        aiMsg.setConversationId(conversation.getId());
        aiMsg.setRole("assistant");
        aiMsg.setContent(aiResponse);
        chatMessageService.insert(aiMsg);

        return aiResponse;
    }
}
