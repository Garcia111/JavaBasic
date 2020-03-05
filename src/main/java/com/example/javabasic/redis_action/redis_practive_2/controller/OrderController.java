package com.example.javabasic.redis_action.redis_practive_2.controller;


import com.example.javabasic.entity.TOrderEntity;
import com.example.javabasic.entity.Response;
import com.example.javabasic.redis_action.redis_practive_2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/insert")
    public Integer insertOrder(TOrderEntity TOrderEntity){
        return orderService.insertOrder(TOrderEntity);
    }


    @GetMapping("/queryById")
    public Response selectOrderById(Integer id){
        return orderService.selectOrderById(id);
    }
}
