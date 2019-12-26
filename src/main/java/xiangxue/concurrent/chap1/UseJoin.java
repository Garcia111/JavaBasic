package xiangxue.concurrent.chap1;

/**
 * @author：Cheng.
 * @date：Created in 17:09 2019/12/26
 */
public class UseJoin {

    static class God implements Runnable{

        private Thread thread;

        public God(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                System.out.println("God is running");
                thread.join();
                System.out.println("God running finished");
            } catch (InterruptedException e) {
                System.out.println("join is interrupted");
            }
        }
    }

    static class GodBoyFriend implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("God boy firend is running");
                Thread.sleep(30);
                System.out.println("God boy firend running finished");
            } catch (InterruptedException e) {
                System.out.println("GodBoyFriend sleep is interrupted");
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("main is running");
        GodBoyFriend gbf = new GodBoyFriend();
        Thread gbfThread = new Thread(gbf);

        God god = new God(gbfThread);
        Thread godThread = new Thread(god);

        godThread.start();
        gbfThread.start();

        try {
            godThread.join();
        } catch (InterruptedException e) {
            System.out.println(" GodThread join interrupt! ");
        }

        System.out.println("main run finished");

    }


}
