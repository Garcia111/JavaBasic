package com.example.javabasic.util.beancopier;

/**
 * @author：Cheng.
 * @date：Created in 21:39 2019/9/8
 */
public class Two {
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return " Two{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + "  \n }";
    }
}