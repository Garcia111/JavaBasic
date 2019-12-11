package com.example.javabasic.serialize;

import java.io.Serializable;

/**
 * @author：Cheng.
 * @date：Created in 9:23 2019/12/11
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private Man man;
    private String username;
    private transient int age;

    public Person() {
        System.out.println("person constru");
    }

    public Person(Man man, String username, int age) {
        this.man = man;
        this.username = username;
        this.age = age;
    }

    public Man getMan() {
        return man;
    }
    public void setMan(Man man) {
        this.man = man;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
