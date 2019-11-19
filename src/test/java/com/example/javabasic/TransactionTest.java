package com.example.javabasic;

import com.example.javabasic.interview.interview20191117.exception.OrderInfoException;
import com.example.javabasic.interview.interview20191117.exception.UserInfoException;
import com.example.javabasic.interview.interview20191117.service.TransactionAopService;
import com.example.javabasic.interview.interview20191117.service.TransactionAopService2;
import com.example.javabasic.interview.interview20191117.service.TransactionAopService3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：Cheng.
 * @date：Created in 23:47 2019/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Autowired
    TransactionAopService transactionAopService;

    @Autowired
    TransactionAopService2 transactionAopService2;

    @Autowired
    TransactionAopService3 transactionAopService3;

    @Test
    public void test(){
        transactionAopService.addOrder();
    }


    @Test
    public void test2(){
        transactionAopService2.addOrder();
    }


    @Test
    public void test3() throws UserInfoException, OrderInfoException {
        transactionAopService3.addOrder();
    }
}
