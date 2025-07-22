package com.max.langchainplayground.controller

import com.max.langchainplayground.model.DocumentRequest
import com.max.langchainplayground.model.TextRequest
import com.max.langchainplayground.service.DocumentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/documents")
class DocumentController(private val documentService: DocumentService) {
    @PostMapping("/ingest")
    fun ingestDocument(@RequestBody body: DocumentRequest): Response {
        val result = documentService.ingestDocument(body.filePath)
        return Response(result)
    }

    @PostMapping("/ingest-text")
    fun ingestText(@RequestBody body: TextRequest): Response {
        val result = documentService.ingestText(body.text, body.metadata)
        return Response(result)
    }

    data class Response(val message: String)
}