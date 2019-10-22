package com.example.javabasic.enumration.multichanneldistribution;

/**
 * @author：Cheng.
 * @date：Created in 10:32 2019/9/17
 */
public interface Item {

    Outcome compete(Item it);
    //包
    Outcome eval(Paper p);
    //剪
    Outcome eval(Scissors s);
    //锤
    Outcome eval(Rock r);
}
