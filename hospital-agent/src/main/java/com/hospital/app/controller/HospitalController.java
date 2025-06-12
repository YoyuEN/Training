package com.hospital.app.controller;

import com.hospital.app.assistant.Assistant;
import com.hospital.app.domain.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Tag(name = "西京医院挂号小助手")
@RestController
@RequestMapping("/agent")
public class HospitalController {
    @Autowired
    private Assistant assistant;

    @Operation(summary = "预约挂号聊天")
    //输出是设置@PostMapping的输出类型html方式输出
    @PostMapping(value = "/chat",  produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return assistant.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
