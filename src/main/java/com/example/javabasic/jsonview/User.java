package com.example.javabasic.jsonview;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author：Cheng.
 * @date：Created in 17:00 2019/9/10
 */
@Setter
@Accessors(chain = true)
public class User {

    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};

    private String userName;

    private String password;

    @JsonView(UserSimpleView.class)
    public String getUserName(){
        return userName;
    }

    @JsonView(UserDetailView.class)
    public String getPassword(){
        return password;
    }

}
