package com.example.javabasic.redis_action.redis_practive_2.controller;

import com.example.javabasic.entity.Response;
import com.example.javabasic.redis_action.redis_practive_2.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：Cheng.
 * @date：Created in 16:08 2019/11/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TOrderEntityControllerTest {

    @Autowired private OrderService orderService;

    @Test
    public void test(){
        Response res = orderService.selectOrderById(1);
        System.out.println(res);
    }
}