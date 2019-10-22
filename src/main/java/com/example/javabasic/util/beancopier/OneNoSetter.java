package com.example.javabasic.util.beancopier;

/**
 * @author：Cheng.
 * @date：Created in 21:42 2019/9/8
 */
public class OneNoSetter {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int id;
    private String name;

    @Override
    public String toString() {
        return " oneNoSetter{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + " \n }";
    }
}
