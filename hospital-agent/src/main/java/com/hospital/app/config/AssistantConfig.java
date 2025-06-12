package com.hospital.app.config;

import com.hospital.app.store.HospitalChatMemoryStore;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig {
    @Autowired
    private HospitalChatMemoryStore mongoChatMemoryStore;


    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memorId ->
                MessageWindowChatMemory.builder()
                        .id(memorId)
                        .chatMemoryStore(mongoChatMemoryStore) //  使用MongoDB存储
                        .maxMessages(20)
                        .build();
    }
}
