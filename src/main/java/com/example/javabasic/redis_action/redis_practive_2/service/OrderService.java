package com.example.javabasic.redis_action.redis_practive_2.service;

import com.example.javabasic.entity.TOrderEntity;
import com.example.javabasic.entity.Response;

public interface OrderService {
    Integer insertOrder(TOrderEntity TOrderEntity);

    Response selectOrderById(Integer id);

//    Response synchronizedSelectOrderById(Integer id);
}
