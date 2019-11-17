package com.example.javabasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableAspectJAutoProxy(exposeProxy = true)
@EnableConfigurationProperties
@SpringBootApplication
public class JavabasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavabasicApplication.class, args);
    }

}
