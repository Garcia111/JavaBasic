package xiangxue.concurrent.concurrent_in_action.chap5;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 设计原理： 使用ConcurrentHashMap<A,Future<V>> ，当输入一个A，想要获取其对应的计算值时，首先检查一个相应的
 * 计算是否已经开始（是否存在对应的FutureTask），如果不存在就创建一个，将其注册到Map中，并开始计算
 *
 * 如果计算已经开始，那么就会使用future.get来获取计算结果，如果当前仍然在运算过程中，则get会被阻塞，直到计算完成
 *
 * 存在的缺陷：两个线程可能同时计算相同的值，因为compute中的if代码块是非原子的“检查再运行”序列。
 *
 * 缓存污染：缓存一个Future而不是一个值的情况下，如果一个计算被取消或者失败，未来尝试对这个值进行计算都会表现
 * 为取消或者失败，为了避免这个结果，Memoizer如果发现计算被取消，就会把Future从缓存中移除，如果发现有
 * RuntimeException，也会移除Future，这样新请求中的计算才有可能成功。
 *
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Memoizer3<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();

    private final Computable<A,V> c;

    public Memoizer3(Computable<A,V> c) {
        this.c = c;
    }


    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if(f == null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V>  ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg,ft);
            //调用c.compute
            ft.run();
        }

        try{
            return f.get();
        }catch (ExecutionException e){
            //Memoizer如果发现计算被取消，就会把Future从缓存中移除，如果发现有
            //RuntimeException，也会移除Future，这样新请求中的计算才有可能成功。
            Throwable t = e.getCause();
            if(t instanceof RuntimeException){
                cache.remove(arg);
                throw (RuntimeException) t;
            }else if(t instanceof  Error){
                throw (Error) t;
            }else{
                throw new IllegalStateException("Not unchecked",t);
            }

        }
    }

}
