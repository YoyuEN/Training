package com.yoyuen;

import com.yoyuen.domin.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @Author: YoyuEN
 * @Date: 2025/6/9
 * @Time: 10:41
 * @Description:
 */
@SpringBootTest
public class Demo10 {

    @Autowired
    private MongoTemplate  mongoTemplate;

    /*
    * 插入数据操作
    * */
    @Test
    public void testInsert(){
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setMessage("你好");
        mongoTemplate.insert(chatMessages);
    }

    /*
    * 查询一条记录
    * */
    @Test
    public void testFind(){
        ChatMessages chatMessages = mongoTemplate.findById("684795a6fe92856167d33323", ChatMessages.class);
        System.out.println(chatMessages);
    }

    /*
    * 修改文档
    * */
    @Test
    public void testUpdate(){
        Criteria criteria = Criteria.where("_id").is("684795a6fe92856167d33323");
        Query query = new Query(criteria);

        Update update = new Update() ;
        update.set("message", "新的聊天记录列表");
        //修改或新增
        mongoTemplate.upsert(query, update, ChatMessages.class) ;
    }
    @Test
    public void testRemove(){
        Criteria  criteria = Criteria.where("_id").is("684795a6fe92856167d33323");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }
}
