package com.example.javabasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableConfigurationProperties
@EnableJpaRepositories(value = {"com.example.javabasic.repository",
        "com.example.javabasic.redis_action.redis_practive_2.dao"})
@SpringBootApplication
public class JavabasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavabasicApplication.class, args);
    }

}
