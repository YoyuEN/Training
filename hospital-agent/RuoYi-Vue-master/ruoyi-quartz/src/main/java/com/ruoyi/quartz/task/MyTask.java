package com.ruoyi.quartz.task;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/13
 * @Time: 9:05
 * @Description:
 */
@Component("myTask")
public class MyTask {
    public void printTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + simpleDateFormat.format(new Date()));
    }
}
