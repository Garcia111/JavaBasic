package com.example.javabasic;

import com.example.javabasic.mq.rabbitmq.topic_exchange.payment.ApiPaymentSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：Cheng.
 * @date：Created in 15:53 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiPaymentSenderTests {

    @Autowired
    ApiPaymentSender apiPaymentSender;

    @Test
    public void sendOrder(){
        apiPaymentSender.order("来了一个新订单");
    }


    @Test
    public void sendOrderQuery(){
        apiPaymentSender.orderQuery("订单查询");
    }


    @Test
    public void sendOrderDetailQuery(){
        apiPaymentSender.orderDetailQuery("订单详情查询");
    }
}
