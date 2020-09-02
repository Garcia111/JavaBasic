package interview.interview_20191114;


import java.util.Vector;

public class ArrayListVectorTest {


    public static void main(String[] args) {
        Vector vector = new Vector();

        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<20;i++){
                    vector.add(i);
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        });


        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=50;i<70;i++){
                    vector.add(i);
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        });
        thread1.start();

        thread2.start();
    }



}
