package com.yoyuen;

import com.yoyuen.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/9
 * @Time: 10:41
 * @Description:
 */
@SpringBootTest
public class Demo6 {

    /*
    * 通过例子知道访问模型三个步骤
    * 1 提供访问模型的名称
    * 2 提供模型的访问地址（有些可以不提供，本地安装模型可以不提供）
    * 3 提供模型的访问密钥
    * */

    @Autowired
    private QwenChatModel  qwenChatModel;

    @Test
    public void testLLM() {
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String answer = assistant.chat("请介绍一下自己");
        System.out.println(answer);
    }
}
