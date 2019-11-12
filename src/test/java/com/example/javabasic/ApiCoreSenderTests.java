package com.example.javabasic;

import com.example.javabasic.mq.rabbitmq.topic_exchange.core.ApiCoreSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：Cheng.
 * @date：Created in 15:41 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiCoreSenderTests {

    @Autowired
    private ApiCoreSender sender;

    @Test
    public void testUser(){
        sender.user("用户管理！");
    }


    @Test
    public void testUserQuery(){
        sender.userQuery("用户查询");
    }

}
