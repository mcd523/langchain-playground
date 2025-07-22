package com.max.langchainplayground.service

import com.max.langchainplayground.assistant.AssistantFactory
import com.max.langchainplayground.model.ChatResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatService(
    private val assistantFactory: AssistantFactory
) {
    fun chat(chatId: UUID?, userMessage: String): ChatResponse {
        val id = chatId ?: UUID.randomUUID()
        val assistant = assistantFactory.fromChat(id)

        return ChatResponse(
            id = id,
            message = assistant.chat(userMessage)
        )
    }
} 