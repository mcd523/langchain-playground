# LangChain4j RAG Playground

A Spring Boot application demonstrating Retrieval-Augmented Generation (RAG) using LangChain4j and Ollama.

## Features

- **RAG Chat**: Enhanced chat interface powered by Ollama with document retrieval capabilities
- **Document Ingestion**: Support for both file and text ingestion
- **Vector Embeddings**: Uses BGE Small EN v1.5 embedding model
- **In-Memory Vector Store**: For development and testing
- **Semi-Persistent Conversations**: Maintains context across interactions through provided ID's

## Prerequisites

- Java 21+
- Kotlin
- Ollama running locally with llama3.2 model
- Gradle

## Setup

1. **Install and Start Ollama**:
   ```bash
   # Install Ollama (macOS)
   brew install ollama
   
   # Start Ollama service
   ollama serve
   
   # Pull the llama3.2 model
   ollama pull llama3.2
   ```

2. **Clone and Run the Application**:
   ```bash
   git clone <your-repo>
   cd langchain-playground
   ./gradlew bootRun
   ```

## API Endpoints

### Chat
```http
POST /chat
Content-Type: application/json

{
  "id": "Your ID (or null)",
  "message": "Your question here"
}
```

### Document Ingestion

#### Ingest Text
```http
POST /documents/ingest-text
Content-Type: application/json

{
  "text": "Your document content here",
  "metadata": {
    "source": "document_name",
    "type": "category"
  }
}
```

#### Ingest File
```http
POST /documents/ingest
Content-Type: application/json

{
  "filePath": "/path/to/your/document.pdf"
}
```

## How It Works

1. **Document Ingestion**: Documents are split into chunks and converted to embeddings
2. **Vector Storage**: Embeddings are stored in an in-memory vector store
3. **Retrieval**: When a question is asked, relevant document segments are retrieved
4. **Generation**: The LLM generates answers using both the retrieved context and the question

## Configuration

Key configuration properties in `application.properties`:

```properties
# Ollama Configuration
langchain4j.ollama.chat-model.base-url=http://localhost:11434
langchain4j.ollama.chat-model.model-name=llama3.2

# Embedding Model
langchain4j.embedding-model.provider=all-minilm-l6-v2

# RAG Settings
rag.chunk-size=500
rag.chunk-overlap=50
rag.max-results=5
```

## Example Usage

1. **Ingest some company information**:
   ```json
   {
     "text": "Acme Corp is a tech company founded in 2010...",
     "metadata": {"source": "company_info"}
   }
   ```

2. **Ask questions about the ingested content**:
   ```json
   {
     "message": "When was Acme Corp founded?",
     "useRAG": true
   }
   ```
   
For more examples, check out [chat.http](chat.http).

## Architecture

- **DocumentService**: Handles document ingestion and vector storage
- **ChatService**: Orchestrates conversation flow
- **Assistant**: LangChain4j AI service interface
- **AssistantFactory**: Manages AI service instances
- **ChatController**: REST API endpoints

## Dependencies

- LangChain4j Spring Boot Starter
- LangChain4j Ollama Integration
- Apache Tika for document parsing
- BGE Small EN v1.5 embeddings
- In-memory vector store

## Next Steps

- Replace in-memory store with persistent vector database (Chroma, Pinecone, etc.)
- Add support for more document formats
- Implement user session management
- Add document metadata filtering
- Enhance retrieval with hybrid search 