/*
 * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created on 20/04/2017.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Cheng", "www.hello.com", "Cheng@qq.com");
        return new ApiInfoBuilder()
                .title("my-practice APIs")
                .description("my-practice-api")
                .termsOfServiceUrl("http://www.hello.com/")
                .contact(contact)
                .version("1.0")
                .build();
    }

    /**
     * Create rest api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.javabasic.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
