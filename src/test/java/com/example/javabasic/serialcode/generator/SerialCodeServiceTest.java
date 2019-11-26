package com.example.javabasic.serialcode.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author：Cheng.
 * @date：Created in 11:24 2019/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SerialCodeServiceTest {

    @Autowired
    SerialCodeService serialCodeService;

    @Test
    public void test(){
        serialCodeService.orderCode();
    }
}