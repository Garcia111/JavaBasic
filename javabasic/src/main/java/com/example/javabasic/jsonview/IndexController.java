package com.example.javabasic.jsonview;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 17:06 2019/9/10
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @JsonView(User.UserSimpleView.class)
    public @ResponseBody List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/userDetail")
    @JsonView(User.UserDetailView.class)
    public @ResponseBody List<User> getUserDetail(){
        return userService.getUsers();
    }



    @GetMapping("/userInfo")
    @JsonView(User.UserSimpleView.class)
    public @ResponseBody
    UserInfo getUserInfo(){
        return new UserInfo().setUser(userService.getUsers().get(0));
    }


    @GetMapping("/personInfo")
    @JsonView(PersonInfo.PersonSimpleView.class)
    public @ResponseBody
    PersonInfo getPersonInfo(){
        return new PersonInfo().setStudent(new Student().setId(1).setName("lisi").setAge(16));
    }
}
