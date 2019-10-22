package com.example.javabasic.util.beancopier;

/**
 * @author：Cheng.
 * @date：Created in 21:40 2019/9/8
 */
public class OneDif{
    
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return " oneDif{  "
                + " \n id : " + this.id
                + " \n name : " + this.name
                + " \n }";
    }
}
