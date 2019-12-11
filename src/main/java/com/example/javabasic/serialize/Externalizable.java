package com.example.javabasic.serialize;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author：Cheng.
 * @date：Created in 9:28 2019/12/11
 */
public interface Externalizable extends java.io.Serializable {

    //Externalizable是继承自Serializable接口的，添加的readExternal（）和writeExternal（）可以在序列化和反序列化中
    //自动调用？？？
    //这样可以在序列化和反序列前后做一些我们自己的操作，比如记录日志或者一些安全性控制的问题
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;


    public void writeExternal(ObjectOutput out)throws IOException;
}
