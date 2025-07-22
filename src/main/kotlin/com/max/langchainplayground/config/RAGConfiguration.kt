package com.max.langchainplayground.config

import dev.langchain4j.data.segment.TextSegment
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel
import dev.langchain4j.rag.content.retriever.ContentRetriever
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever
import dev.langchain4j.store.embedding.EmbeddingStore
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RAGConfiguration {

    @Value("\${rag.max-results:5}")
     private val maxResults: Int = 5

    @Value("\${rag.min-score:0.7}")
    private val minScore: Double = 0.7

    @Bean
    fun embeddingModel(): EmbeddingModel {
        return BgeSmallEnV15EmbeddingModel()
    }

    @Bean
    fun embeddingStore(): EmbeddingStore<TextSegment> {
        return InMemoryEmbeddingStore()
    }

    @Bean
    fun contentRetriever(
        embeddingModel: EmbeddingModel,
        embeddingStore: EmbeddingStore<TextSegment>
    ): ContentRetriever {
        return EmbeddingStoreContentRetriever.builder()
            .embeddingStore(embeddingStore)
            .embeddingModel(embeddingModel)
            .minScore(minScore)
            .maxResults(maxResults)
            .build()
    }
}