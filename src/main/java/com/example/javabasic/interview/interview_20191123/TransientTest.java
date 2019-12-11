package com.example.javabasic.interview.interview_20191123;

import java.io.*;

/**
 * @author：Cheng.
 * @date：Created in 16:30 2019/12/10
 */
public class TransientTest implements Serializable {

    private String name1;

    private transient String name2;


    public TransientTest(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }


    @Override
    public String toString() {
        return "TransientTest{" +
                "name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }


    //这里的readObject() writeObject()方法的执行时由ObjectInputStream与ObjectOutputStream
    //中的readObject  writeObject方法通过反射调用实现的。
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        s.defaultWriteObject();
        //序列化时不会按照默认算法将这个成员变量写入磁盘，而是写了个writeObject方法，
        // 序列化时会按照这个方法将其持久化
        //所以，这是将其序列化到哪里去了？
        s.writeObject(name2);
    }
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        name2=String.valueOf(s.readObject());
    }



    public static void main(String[] args){
        String name1="常规属性",name2="transient修饰的属性";
        TransientTest test = new TransientTest(name1, name2);
        System.out.println("序列化前："+test.toString());

        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        String filePath = "D:/TransientTest.obj";
        try{
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(test);


            inStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTest readObject = (TransientTest) inStream.readObject();
            System.out.println("序列化后："+readObject.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
