package com.max.langchainplayground.service

import dev.langchain4j.data.document.Document
import dev.langchain4j.data.document.DocumentSplitter
import dev.langchain4j.data.document.Metadata
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser
import dev.langchain4j.data.document.splitter.DocumentSplitters
import dev.langchain4j.data.segment.TextSegment
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.store.embedding.EmbeddingStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class DocumentService(
    private val embeddingModel: EmbeddingModel,
    private val embeddingStore: EmbeddingStore<TextSegment>
) {
    
    @Value("\${rag.chunk-size:500}")
    private val chunkSize: Int = 500
    
    @Value("\${rag.chunk-overlap:50}")
    private val chunkOverlap: Int = 50

    private val documentSplitter: DocumentSplitter = DocumentSplitters.recursive(chunkSize, chunkOverlap)
    
    fun ingestDocument(filePath: String): String {
        return try {
            val path = Paths.get(filePath)
            val document = FileSystemDocumentLoader.loadDocument(path, ApacheTikaDocumentParser())
            
            val segments = documentSplitter.split(document)
            val embeddings = embeddingModel.embedAll(segments)
            
            embeddingStore.addAll(embeddings.content(), segments)
            
            "Successfully ingested document: $filePath with ${segments.size} segments"
        } catch (e: Exception) {
            "Failed to ingest document: ${e.message}"
        }
    }
    
    fun ingestText(text: String, metadata: Map<String, String> = emptyMap()): String {
        return try {
            val document = Document.from(text, Metadata(metadata))
            val segments = documentSplitter.split(document)
            val embeddings = embeddingModel.embedAll(segments)
            
            embeddingStore.addAll(embeddings.content(), segments)
            
            "Successfully ingested text with ${segments.size} segments"
        } catch (e: Exception) {
            "Failed to ingest text: ${e.message}"
        }
    }
} 