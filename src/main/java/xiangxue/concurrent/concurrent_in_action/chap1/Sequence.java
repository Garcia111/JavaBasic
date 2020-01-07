package xiangxue.concurrent.concurrent_in_action.chap1;

import net.jcip.annotations.ThreadSafe;

import javax.annotation.concurrent.GuardedBy;

/**
 * @author：Cheng.
 * @date：Created in 22:24 2020/1/4
 */
@ThreadSafe
public class Sequence {


    @GuardedBy("this")
    private int value;


    public synchronized  int getNext(){
        return value++;
    }














    
}
