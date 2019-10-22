package com.example.javabasic;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author：Cheng.
 * @date：Created in 17:13 2019/9/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonViewTest {

    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testGetUsersSimple() throws Exception {
        String mvcResult = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()) // 打印信息
                .andExpect(status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString();
        System.out.println("mvcResult: " + mvcResult);
        //mvcResult: [{"username":"张三","password":"123"},{"username":"李四","password":"123"}]
        //@JsonView(UserSimpleView.class) ==> mvcResult: [{"username":"张三"},{"username":"李四"}]
    }


    @Test
    public void testGetUserDetail() throws Exception {
        String mvcResult = mockMvc.perform(get("/userDetail")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()) // 打印信息
                .andExpect(status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString();
        System.out.println("mvcResult: " + mvcResult);
        //mvcResult: [{"username":"张三","password":"123"},{"username":"李四","password":"123"}]
        //@JsonView(UserSimpleView.class) ==> mvcResult: [{"username":"张三"},{"username":"李四"}]
    }


    @Test
    public void testGetUserInfo() throws Exception{
        String mvcResult = mockMvc.perform(get("/userInfo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()) // 打印信息
                .andExpect(status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString();
        System.out.println("mvcResult: " + mvcResult);
    }


    @Test
    public void testGetPersonInfo() throws Exception{
        String mvcResult = mockMvc.perform(get("/personInfo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()) // 打印信息
                .andExpect(status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString();
        System.out.println("mvcResult: " + mvcResult);
    }


}
