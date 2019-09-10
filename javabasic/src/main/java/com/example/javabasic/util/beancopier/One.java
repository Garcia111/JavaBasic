package com.example.javabasic.util.beancopier;

/**
 * @author：Cheng.
 * @date：Created in 21:38 2019/9/8
 */
public class One {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    @Override
    public String toString() {
        return " One{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                +"  \n }";
    }
}
