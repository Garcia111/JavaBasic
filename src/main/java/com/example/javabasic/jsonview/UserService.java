package com.example.javabasic.jsonview;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 17:09 2019/9/10
 */
@Service
public class UserService {

    public List<User> getUsers(){
        return Lists.newArrayList(new User().setUserName("zhangsan").setPassword("123456"),
                new User().setUserName("liSi").setPassword("123456"));
    }
}
