package com.example.javabasic.interview.interview_20191117;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：Cheng.
 * @date：Created in 23:43 2019/11/17
 */
@Service
public class TransactionAopServiceImpl {


    public void addOrder(){
        System.out.println("addOrder");
        addUser();
    }

    @Transactional
    public void addUser(){
        System.out.println("addUser");
        throw new RuntimeException();
    }




}
