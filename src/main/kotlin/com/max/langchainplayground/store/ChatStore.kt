package com.max.langchainplayground.store

import dev.langchain4j.memory.ChatMemory
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Component
class ChatStore {
    private val chats = ConcurrentHashMap<UUID, ChatMemory>()

    fun getChatMemory(chatId: UUID): ChatMemory {
        return chats.computeIfAbsent(chatId) { MessageWindowChatMemory.withMaxMessages(10) }
    }
}