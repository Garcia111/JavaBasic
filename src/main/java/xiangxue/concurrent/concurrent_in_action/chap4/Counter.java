package xiangxue.concurrent.concurrent_in_action.chap4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Java监视器模式的典型案例（此类对比Holder类）
 * @author：Cheng.
 * @date：Created in 18:48 2020/2/1
 */
@ThreadSafe
public final class Counter {

    @GuardedBy("this")
    //封装了一个状态变量value
    private long value = 0;


    //所有对变量value的访问都要通过Counter方法
    public synchronized long getValue(){
        return value;
    }


    public synchronized long increment(){
        if(value == Long.MAX_VALUE){
            throw new IllegalArgumentException("counter overflow");
        }
        return ++value;
    }









}
