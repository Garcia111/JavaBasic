package com.example.javabasic.spring.study_20191112;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：Cheng.
 * @date：Created in 19:37 2019/11/12
 */
@Configuration
public class Config {


    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    public TestInitializingBean getTestInitializingBean(){
        return new TestInitializingBean();
    }
}
