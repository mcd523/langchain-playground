package com.max.langchainplayground.assistant

import com.max.langchainplayground.store.ChatStore
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.rag.content.retriever.ContentRetriever
import dev.langchain4j.service.AiServices
import org.springframework.stereotype.Component

@Component
class AssistantFactory(
    private val chatModel: ChatModel,
    private val chatStore: ChatStore,
    private val contentRetriever: ContentRetriever
) {

    fun fromChat(chatId: String): Assistant {
        return AiServices.builder(Assistant::class.java)
            .chatModel(chatModel)
            .chatMemory(chatStore.getChatMemory(chatId))
            .contentRetriever(contentRetriever)
//            .tools(AssistantTools)
            .systemMessageProvider {
                "You are a helpful assistant. Use the available tools if they are relevant. Respond in plain English."
            }
            .build()
    }
}