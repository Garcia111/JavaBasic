package com.example.javabasic.redis_action.redis_practive_2.controller;


import com.example.javabasic.entity.TOrderEntity;
import com.example.javabasic.entity.Response;
import com.example.javabasic.redis_action.redis_practive_2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/test")
    public Integer insertOrder(TOrderEntity TOrderEntity){
        return orderService.insertOrder(TOrderEntity);
    }


    @GetMapping("/selectid")
    public Response selectOrderById(Integer id){
        return orderService.selectOrderById(id);
    }
}
