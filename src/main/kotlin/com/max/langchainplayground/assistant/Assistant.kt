package com.max.langchainplayground.assistant

interface Assistant {
    fun chat(userMessage: String): String
}