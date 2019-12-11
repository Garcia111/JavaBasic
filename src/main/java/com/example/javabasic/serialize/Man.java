package com.example.javabasic.serialize;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author：Cheng.
 * @date：Created in 9:22 2019/12/11
 */
public class Man implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public Man(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
