plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.max"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("dev.langchain4j:langchain4j-bom:1.1.0")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("dev.langchain4j:langchain4j-spring-boot-starter")
    implementation("dev.langchain4j:langchain4j-ollama-spring-boot-starter")
    
    // RAG dependencies
    implementation("dev.langchain4j:langchain4j-document-parser-apache-tika")
    implementation("dev.langchain4j:langchain4j-easy-rag")
    implementation("dev.langchain4j:langchain4j-embeddings-bge-small-en-v15")

    // In-memory vector store for simplicity
    implementation("dev.langchain4j:langchain4j-embeddings")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
