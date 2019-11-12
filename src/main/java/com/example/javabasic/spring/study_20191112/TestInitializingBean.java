package com.example.javabasic.spring.study_20191112;


import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author：Cheng.
 * @date：Created in 18:32 2019/11/12
 */

public class TestInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ceshi InitializingBean ");
    }

    @PostConstruct
    public void testPostConstruct(){
        System.out.println("ceshi post construct method");
    }


    public void initMethod(){
        System.out.println("ceshi init method");
    }


    public void destroyMethod(){
        System.out.println("ceshi destroy method");
    }
}
