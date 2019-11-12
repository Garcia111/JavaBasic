package com.example.javabasic.spring.study_20191112;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：Cheng.
 * @date：Created in 19:18 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitializingBeanTest {

    @Autowired
    TestInitializingBean testInitializingBean;


    @Test
    public void test(){

    }


}
