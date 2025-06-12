package com.yoyuen.starangent.config;

import com.yoyuen.starangent.store.StarChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/10
 * @Time: 9:03
 * @Description:
 */
@Configuration
public class AssistantConfig {

    @Autowired
    private StarChatMemoryStore starChatMemoryStore;

    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(starChatMemoryStore)
                .build() ;
    }
}
