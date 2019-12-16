package com.example.javabasic.thread.delay_queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 15:24 2019/12/16
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        Item item1 = new Item("item1", 5, TimeUnit.SECONDS);
        Item item2 = new Item("item2",10, TimeUnit.SECONDS);
        Item item3 = new Item("item3",15, TimeUnit.SECONDS);
        DelayQueue<Item> queue = new DelayQueue<>();
        queue.put(item1);
        queue.put(item3);
        queue.put(item2);
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        for (int i = 0; i < 3; i++) {
            Item take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n",take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }

}


class Item implements Delayed {

    /**触发时间*/
    private long time;

    String name;

    public Item( String name,long time,TimeUnit unit) {
        this.time = System.currentTimeMillis()+(time>0?unit.toMillis(time):0);
        this.name = name;
    }

    /**
     * 返回当前对象的延迟时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return time-System.currentTimeMillis();
    }


    /**
     * 指定延迟的依据条件
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        Item item = (Item) o;
        long diff = this.time-item.time;
        if(diff <= 0){
            return -1;
        }else{
            return 1;
        }
    }


    @Override
    public String toString() {
        return "Item{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}