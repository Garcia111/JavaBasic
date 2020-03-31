package xiangxue.concurrent.concurrent_in_action.chap5;



import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用ConcurrentHashMap 代替HashMap，减少同步代码块，相对 Memoizer1 提高了效率
 *
 * 缺点：当两个线程同时调用compute时，存在一个漏洞，就是同一个值会计算两遍：
 *  如果缓存中不存在某个计算结果，一个线程启动了一个开销很大的计算来计算这个结果，而其他线程并不知道这个计算正在
 *  进行中，所以可能又会重复这个计算
 *
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Memoizer2<A,V> implements Computable<A,V>{

    private final Map<A,V> cache = new ConcurrentHashMap<A,V>();

    private final Computable<A,V> c;

    public Memoizer2(Computable<A,V> c){
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null){
            V v = c.compute(arg);
            cache.put(arg,v);
        }
        return result;
    }


}
