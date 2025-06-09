package com.yoyuen;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/9
 * @Time: 11:13
 * @Description:
 */
@SpringBootTest
public class Demo2 {
    @Autowired
    private OpenAiChatModel openAiChatModel ;
    @Test
    public void testSpringBoot() {
        //向模型提问
        String answer = openAiChatModel .chat("你好") ;
        //输出结果
        System.out .println(answer) ;
    }
}
