package com.example.javabasic.thread.join;

/**
 * @author：Cheng.
 * @date：Created in 15:47 2019/12/4
 */
public class JoinTest2 {

    public static void main(String [] args) throws InterruptedException {
        ThreadJoinTest2 t1 = new ThreadJoinTest2("小明");
        ThreadJoinTest2 t2 = new ThreadJoinTest2("小东");
        t1.start();
        /**join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
         * main线程和t1线程之间执行顺序由串行执行变为普通的并行执行
         */
        t1.join(10);
        t2.start();
    }

}
class ThreadJoinTest2 extends Thread{
    public ThreadJoinTest2(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println(this.getName() + ":" + i);
        }
    }

}
