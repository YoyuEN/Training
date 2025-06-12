package com.yoyuen.config;

import com.yoyuen.store.MongoChatMemoryStore;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
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
    private MongoChatMemoryStore  mongoChatMemoryStore;

    @Bean
    ChatMemory  chatMemory() {
        // 设置聊天记忆记录的message数量
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(mongoChatMemoryStore)
                .build() ;
    }
}
