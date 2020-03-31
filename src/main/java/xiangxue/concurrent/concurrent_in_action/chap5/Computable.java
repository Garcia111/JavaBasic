package xiangxue.concurrent.concurrent_in_action.chap5;

import net.jcip.annotations.GuardedBy;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 尝试使用HashMap和同步来初始化缓存
 * Computable接口的输入类型是A，输出结果类型是V
 *
 * @author：Cheng.
 * @since： 1.0.0
 */
public interface Computable<A,V> {

    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String, BigInteger>{

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}


class Memoizer1<A,V> implements Computable<A,V>{

    @GuardedBy("this")
    private final Map<A,V> cache = new HashMap<A,V>();

    private final Computable<A,V> c;

    public Memoizer1(Computable<A,V> c){
        this.c = c;
    }


    /**
     * 缓存中有值则直接从缓存中取，缓存中无值再计算，注意此方法是同步方法，因为HashMap不是线程安全的，所以为了
     * 保证两个线程不会同时访问HashMap，选择同步整个compute方法。
     *
     * 缺点：一次只有一个线程能执行compute方法，如果另外一个线程正在忙于计算结果，其他调用此方法的线程可能会被阻塞
     * 很长时间。如果有多个线程都在排队等待尚未计算出来的结果，compute方法的效率是很低的。
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public synchronized  V compute(A arg) throws InterruptedException{
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }


}










