package com.example.javabasic.interview.interview20191117.service;

import com.example.javabasic.entity.OrderEntity;
import com.example.javabasic.entity.UserEntity;
import com.example.javabasic.interview.interview20191117.repository.OrderInfoRepository;
import com.example.javabasic.interview.interview20191117.repository.UserRepository;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：Cheng.
 * @date：Created in 9:48 2019/11/19
 */
@Service
public class TransactionAopService2 {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderInfoRepository orderInfoRepository;

    public void addOrder(){
        OrderEntity orderEntity = orderInfoRepository.save(new OrderEntity().setUserId(1L).setAmount(200L));
        System.out.println("addOrder");
        TransactionAopService2 service = (TransactionAopService2) AopContext.currentProxy();
        try{
            service.addUser();
        }catch (Exception e){
            orderEntity.setAmount(2000L);
            orderInfoRepository.save(orderEntity);
            e.printStackTrace();

        }

    }

    @Transactional
    public void addUser(){
        userRepository.save(new UserEntity().setName("zhangsan2").setPhone("12345678901"));
//        throw new RuntimeException();
    }

    //此种情况，addUser进行了回退，addOrder没有进行回退
}
