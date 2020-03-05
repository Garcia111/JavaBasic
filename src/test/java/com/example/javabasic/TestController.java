package com.example.javabasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：Cheng.
 * @date：Created in 17:29 2020/3/2
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping
    public String test(){
        return "test success";
    }
}
