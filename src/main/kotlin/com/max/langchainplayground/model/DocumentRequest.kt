package com.max.langchainplayground.model

data class DocumentRequest(
    val filePath: String
)

data class TextRequest(
    val text: String,
    val metadata: Map<String, String> = emptyMap()
)
