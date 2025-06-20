/**
 * Package: moe.zzy040330.chat138.service
 * File: AIChatService.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 11:11
 * Description: This interface defines the AI-related business logic for handling chat interactions.
 * It provides methods for building conversation context and generating AI responses using
 * underlying language model APIs.
 */

package moe.zzy040330.chat138.service;

/**
 * AIChatService is responsible for managing AI chat functionalities within the application.
 * It encapsulates operations such as generating conversation context prompts and invoking
 * the AI model to generate responses.
 */
public interface AIChatService {

    /**
     * Builds the complete conversation prompt by fetching historical messages of the specified conversation.
     * The prompt is formatted appropriately to be fed into the AI model for generating contextual responses.
     *
     * @param conversationUuid the UUID of the conversation for which the context is to be built
     * @return the formatted prompt containing the conversation context
     */
    String buildContextPrompt(String conversationUuid);

    /**
     * Generates an AI response based on the provided user message and existing conversation context.
     * This method handles invoking the AI model and assembling the complete response.
     *
     * @param conversationUuid the UUID of the conversation to maintain context
     * @param userMessage      the message input by the user
     * @return the AI-generated response corresponding to the user input
     */
    String chatWithAI(String conversationUuid, String userMessage);
}
