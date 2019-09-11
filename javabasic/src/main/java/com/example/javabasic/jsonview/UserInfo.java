package com.example.javabasic.jsonview;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author：Cheng.
 * @date：Created in 17:06 2019/9/11
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserInfo {

    @JsonView(User.UserSimpleView.class)
    private User user;


}
