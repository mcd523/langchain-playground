package com.max.langchainplayground.model

import java.util.UUID

data class ChatRequest(
    val id: UUID? = null,
    val message: String = ""
)