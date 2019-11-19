package com.example.javabasic.interview.interview20191117.service;

import com.example.javabasic.interview.interview20191117.entity.OrderEntity;
import com.example.javabasic.interview.interview20191117.entity.UserEntity;
import com.example.javabasic.interview.interview20191117.repository.OrderInfoRepository;
import com.example.javabasic.interview.interview20191117.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：Cheng.
 * @date：Created in 23:43 2019/11/17
 */
@Service
public class TransactionAopService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderInfoRepository orderInfoRepository;

    public void addOrder(){
        orderInfoRepository.save(new OrderEntity().setUserId(1L).setAmount(100L));
        System.out.println("addOrder");
        addUser();
    }

    @Transactional
    public void addUser(){
        userRepository.save(new UserEntity().setName("zhangsan").setPhone("12345678901"));
        throw new RuntimeException();
    }


    //1.addOrder不添加@Transactional, addUser添加@Transactional-----发生异常，都没有进行回滚


}
