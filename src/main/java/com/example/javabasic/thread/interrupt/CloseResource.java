package com.example.javabasic.thread.interrupt;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput =
                new Socket("localhost",8080).getInputStream();
//
//        exec.execute(new IOBlocked(socketInput));
//        exec.execute(new IOBlocked(System.in));

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shuting down all threads");

        exec.shutdown();;
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing "+socketInput.getClass().getName());
        //一旦底层资源被关闭，任务将会解除阻塞，所以在线程shutdown之后，主线程sleep一会儿再关闭资源
        //韦德是看到阻塞的效果
        socketInput.close();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing "+System.in.getClass().getName());
        System.in.close();

    }

}
