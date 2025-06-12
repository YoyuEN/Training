package com.yoyuen.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/10
 * @Time: 8:39
 * @Description:
 */

/*
    @AiService 装饰器常用属性
    String chatModel() default "";指定那个大模型
    String streamingChatModel() default "";指定使用流式输出，默认使用非流式输出，后续使用
    String chatMemory() default "";配置模型记忆属性，默认在内容中记忆
    String chatMemoryProvider() default "";配置模型记忆提供者，默认使用MemoryProvider 配置模型隔离属性，默认在内存中隔离
*/
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        chatMemory = "chatMemory",
        tools = "calculatorTools"
)
public interface Assistant {
    @UserMessage("你是我的好朋友，请用陕北话回答问题，并且添加一些表情符号。 {{message}}")
    String chat(@V("message") String message);

    @SystemMessage(fromResource = "my-prompt-template.txt")
    String chat(@MemoryId  int memoryId, @UserMessage String message);

    @SystemMessage("你是我的好朋友，我是{{name}}，我的年龄是{{age}}，请用陕北话回答问题，回答问题的时候适当添加表\n" +
            "情符号。")
    String chat(@MemoryId  int memoryId, @UserMessage String message,  @V("name") String name,  @V("age") int age);
}
