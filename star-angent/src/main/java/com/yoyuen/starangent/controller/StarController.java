package com.yoyuen.starangent.controller;

import com.yoyuen.starangent.assistant.AssistantAgent;
import com.yoyuen.starangent.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/10
 * @Time: 17:26
 * @Description:
 */
@Tag(name = "崩坏", description = "StarController")
@RestController
@RequestMapping("/agent")
public class StarController {
    @Autowired
    private AssistantAgent  assistantAgent;

    @Operation(summary = "对话")
    /**
     * 处理聊天请求，通过处理提供的ChatForm数据。
     * 该方法将聊天操作委托给AssistantAgent，
     * 使用请求体中的memory ID和消息生成响应。
     */
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return assistantAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
