package com.example.javabasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableConfigurationProperties
@EnableJpaRepositories(value = {"com.example.javabasic.interview.interview20191117.repository"})
@SpringBootApplication
public class JavabasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavabasicApplication.class, args);
    }

}
