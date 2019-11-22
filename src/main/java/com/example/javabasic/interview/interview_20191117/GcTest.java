package com.example.javabasic.interview.interview_20191117;

public class GcTest {

    public static void main(String[] args){
        if(true){
            byte[] placeHolder = new byte[64*1024*1024];
            System.out.println(placeHolder.length/1024);
            placeHolder = null;
        }
        System.gc();
    }
}
