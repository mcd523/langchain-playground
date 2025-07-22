package com.max.langchainplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LangchainPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<LangchainPlaygroundApplication>(*args)
}
