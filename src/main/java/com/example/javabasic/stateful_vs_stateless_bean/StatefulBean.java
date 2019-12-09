package com.example.javabasic.stateful_vs_stateless_bean;

import com.example.javabasic.jsonview.User;

/**
 * 有状态bean,有state,user等属性，并且user有存偖功能，是可变的。
 * @author：Cheng.
 * @date：Created in 16:57 2019/12/9
 */
public class StatefulBean {

    public int state;
    // 由于多线程环境下，user是引用对象，是非线程安全的
    public User user;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
