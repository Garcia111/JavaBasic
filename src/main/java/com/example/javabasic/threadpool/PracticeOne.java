package com.example.javabasic.threadpool;

public class PracticeOne{

    public static void main(String[] args){
        for(int i=1;i<10;i++){
            new Thread(new RunableTest()).start();
        }
    }
}


class RunableTest implements Runnable{

    public String startstr = "start!";

    public String stopstr = "stop!";

    static  int num = 0;

    public int id;

    public RunableTest(){
        num++;
        id=num;
        startstr = "#"+id+" "+startstr;
        stopstr = "#"+id+" "+stopstr;
        System.out.println(startstr);
    }

    @Override
    public void run() {
        for(int i=1;i<3;i++){
            System.out.println("#"+id+"loop"+i);
            //yield控制时间片切换
            Thread.yield();
        }
        System.out.println(stopstr);
    }
}