package com.yoyuen;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/9
 * @Time: 10:41
 * @Description:
 */
public class Demo1 {

    /*
    * 通过例子知道访问模型三个步骤
    * 1 提供访问模型的名称
    * 2 提供模型的访问地址（有些可以不提供，本地安装模型可以不提供）
    * 3 提供模型的访问密钥
    * */
    @Test
    public void testLLM() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();
        String str = model.chat("请介绍一下自己");
        System.out.println(str);
    }
}
