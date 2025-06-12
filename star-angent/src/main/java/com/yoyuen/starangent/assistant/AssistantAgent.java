package com.yoyuen.starangent.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/10
 * @Time: 17:16
 * @Description:
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "appointmentTools"
//        contentRetriever = "contentRetriever"
)
public interface AssistantAgent {
    @SystemMessage(fromResource = "lm-prompt-template.txt")
    Flux<String> chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
