package com.yoyuen;

import com.yoyuen.assistant.Assistant;
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
public class Demo9 {

    /*
    * 通过例子知道访问模型三个步骤
    * 1 提供访问模型的名称
    * 2 提供模型的访问地址（有些可以不提供，本地安装模型可以不提供）
    * 3 提供模型的访问密钥
    * */

    @Autowired
    private Assistant assistant ;

    @Test
    public void testLLM() {
        String answer1 = assistant.chat(1,"我是YoyuEN");
        System.out .println(answer1);
        String answer2 = assistant.chat(1,"我是谁");
        System.out .println(answer2);
        String answer3 = assistant.chat(2,"我是谁");
        System.out .println(answer3);

    }
}
