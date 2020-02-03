package xiangxue.concurrent.chap1.base.syn;

import xiangxue.concurrent.chap1.base.SleepTools;

import java.util.concurrent.ThreadPoolExecutor;

/** 类说明：当锁的实例不一样，线程是不能被锁住的
 * @author：Cheng.
 * @date：Created in 16:38 2020/1/19
 */
public class DiffInstance {

    private static class InstanceSyn implements Runnable{

        private DiffInstance diffInstance;

        public InstanceSyn(DiffInstance diffInstance){
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..."+ diffInstance);
            diffInstance.instance();
        }
    }


    private static class Instance2Syn implements Runnable{

        private DiffInstance diffInstance;

        public Instance2Syn(DiffInstance diffInstance){
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running..."+ diffInstance);
            diffInstance.instance();
        }
    }

    private synchronized void instance(){
        SleepTools.sleepSeconds(3);
        System.out.println("synInstance is going..."+this.toString());
        SleepTools.sleepSeconds(3);
        System.out.println("synInstance ended "+this.toString());
    }
    
    
    private synchronized void instance2(){
        SleepTools.sleepSeconds(3);
        System.out.println("synInstance2 is going..."+this.toString());
        SleepTools.sleepSeconds(3);
        System.out.println("synInstance2 ended "+this.toString());
    }

    public static void main(String[] args) {

        DiffInstance instance1 = new DiffInstance();
        Thread t3 = new Thread(new Instance2Syn(instance1));
        DiffInstance instance2 = new DiffInstance();
        Thread t4 = new Thread(new InstanceSyn(instance2));
        t3.start();
        t4.start();
        SleepTools.sleepSeconds(1);
    }
    
    

}









