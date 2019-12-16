package com.example.javabasic.thread.pipe;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.hibernate.engine.spi.ExecuteUpdateResultCheckStyle;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 9:17 2019/12/13
 */

class Sender implements Runnable{

    private Random random = new Random(47);

    private PipedWriter pipedWriter = new PipedWriter();

    public PipedWriter getPipedWriter(){
        return pipedWriter;
    }

    @Override
    public void run() {
        try{
            while (true){
                for(char c = 'A'; c<'Z'; c++){
                    pipedWriter.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }

            }
        } catch (InterruptedException e) {
            System.out.println("Sender sleep interrupted");
        } catch (IOException e) {
            System.out.println("Sender write exception");
        }
    }
}


class Receiver implements Runnable{

    private PipedReader pipedReader;


    public Receiver(Sender sender) throws IOException {
        this.pipedReader = new PipedReader(sender.getPipedWriter());
    }
    @Override
    public void run() {
        try{
            while (true){
                System.out.println("read: "+(char)pipedReader.read()+", ");
            }
        } catch (IOException e) {
            System.out.println("Receiver read exception");
        }

    }
}

public class PipeIO {

    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }

}
