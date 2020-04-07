package com.example.javabasic.interview.interview20191117.service;

import com.example.javabasic.entity.OrderEntity;
import com.example.javabasic.entity.UserEntity;
import com.example.javabasic.interview.interview20191117.exception.OrderInfoException;
import com.example.javabasic.interview.interview20191117.exception.UserInfoException;
import com.example.javabasic.repository.OrderRepository;
import com.example.javabasic.repository.UserRepository;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：Cheng.
 * @date：Created in 10:01 2019/11/19
 */
@Service
public class TransactionAopService3 {


    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;


    @Transactional(rollbackFor = OrderInfoException.class, noRollbackFor = RuntimeException.class)
    public void addOrder() throws OrderInfoException, UserInfoException {
        orderRepository.save(new OrderEntity().setUserId(1L).setAmount(3000L));
        System.out.println("addOrder");
        TransactionAopService3 service = (TransactionAopService3) AopContext.currentProxy();
        service.addUser();
//        throw new OrderInfoException();
    }

    @Transactional(rollbackFor = UserInfoException.class, noRollbackFor = RuntimeException.class)
    public void addUser() throws UserInfoException {
        userRepository.save(new UserEntity().setName("zhangsan3").setPhone("12345678901"));
        throw new UserInfoException();
    }

    //使用同一个类中调用方法，抛出异常都没有进行回退
    //额外添加代理，仅仅抛出UserInfoException()------addUser addOrder 都进行了回退
    //额外添加代理，仅仅抛出OrderInfoException()-----addUser addOrder 都进行了回退

}
