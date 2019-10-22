package com.example.javabasic.util.beancopier;

/**
 * @author：Cheng.
 * @date：Created in 21:41 2019/9/8
 */
public class OneLessSetter {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return " OneLessSetter{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + " \n }";
    }
}
