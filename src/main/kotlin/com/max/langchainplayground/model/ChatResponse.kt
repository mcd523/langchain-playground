package com.max.langchainplayground.model

import java.util.UUID

data class ChatResponse(
    val id: UUID,
    val message: String
)