package com.example.javabasic.interview.interview20191117;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author：Cheng.
 * @date：Created in 14:09 2019/11/18
 */
public class TransactionAopServiceTest {

    @Autowired
    TransactionAopService transactionAopService;

    @Test
    public void addOrder() {
        transactionAopService.addOrder();
    }
}