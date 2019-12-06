package com.example.javabasic.thread.wait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 17:19 2019/12/6
 */
class ConditionObject{

    private boolean condition;

    public boolean isCondition() {
        return condition;
    }

    public synchronized  void setCondition(boolean condition) {
        this.condition = condition;
        notifyAll();
    }

    public synchronized  void waitForTrue() throws InterruptedException {
        while(condition==false){
            wait();
        }
    }

    public synchronized  void waitForFalse() throws InterruptedException {
        while(condition==true){
            wait();
        }
    }
}



class TaskOne implements Runnable{

    ConditionObject conditionObject;

    public TaskOne(ConditionObject conditionObject) {
        this.conditionObject = conditionObject;
    }

    @Override
    public void run() {
        try{
            System.out.println("task one running");
            conditionObject.setCondition(true);
            conditionObject.waitForFalse();
            System.out.println("condition now is false");
        }catch (InterruptedException e){
            System.out.println("task one interrupted");
        }

    }
}


class TaskTwo implements Runnable{
    TaskOne taskOne;

    public TaskTwo(TaskOne taskOne) {
        this.taskOne = taskOne;
    }

    @Override
    public void run() {
        try{
            while (taskOne.conditionObject.isCondition()){
                TimeUnit.SECONDS.sleep(2);
                taskOne.conditionObject.setCondition(false);
            }
        }catch (InterruptedException e){
            System.out.println("task two interrupted");
        }


    }
}

public class PracticeOne {


    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        TaskOne taskOne = new TaskOne(new ConditionObject());
        exec.execute(taskOne);
        exec.execute(new TaskTwo(taskOne));
    }
}
