package com.max.langchainplayground.controller

import com.max.langchainplayground.model.ChatRequest
import com.max.langchainplayground.model.ChatResponse
import com.max.langchainplayground.service.ChatService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatService: ChatService
) {

    
    @PostMapping
    fun chat(@RequestBody body: ChatRequest): ChatResponse {
        return chatService.chat(body.id, body.message)
    }
}