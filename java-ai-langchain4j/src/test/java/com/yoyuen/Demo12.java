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
public class Demo12 {

    /*
    * 通过例子知道访问模型三个步骤
    * 1 提供访问模型的名称
    * 2 提供模型的访问地址（有些可以不提供，本地安装模型可以不提供）
    * 3 提供模型的访问密钥
    * */

    @Autowired
    private Assistant assistant ;
    @Test
    public void testCalculatorTools() {
        String answer = assistant .chat(1, "1+2等于几，475695037565的平方根是多少？") ;
                //答案：3，689706.4865
                System.out .println(answer) ;
    }
}
