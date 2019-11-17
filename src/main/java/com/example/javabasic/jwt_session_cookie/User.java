package com.example.javabasic.jwt_session_cookie;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author：Cheng.
 * @date：Created in 16:21 2019/11/14
 */
@Data
@ToString
@EqualsAndHashCode
public class User {

    private Long id;

    private String name;

}
