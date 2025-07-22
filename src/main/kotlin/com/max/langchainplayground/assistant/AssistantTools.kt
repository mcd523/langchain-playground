package com.max.langchainplayground.assistant

import dev.langchain4j.agent.tool.Tool
import io.micrometer.observation.annotation.Observed
import org.slf4j.LoggerFactory

object AssistantTools {
    private val logger = LoggerFactory.getLogger(Assistant::class.java)

    @Tool(
        name = "add",
        value = ["Adds two integers together and returns the result."]
    )
    @Observed
    fun add(a: Int, b: Int): Int {
        logger.info("Calling add tool with parameters: a = $a, b = $b")
        return a + b
    }
}