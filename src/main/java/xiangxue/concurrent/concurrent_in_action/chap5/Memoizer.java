package xiangxue.concurrent.concurrent_in_action.chap5;

import java.util.concurrent.*;

import static xiangxue.concurrent.concurrent_in_action.chap5.Preloader.launderThrowable;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Memoizer<A,V> implements Computable<A,V> {

    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();

    private final Computable<A,V> c;

    public Memoizer(Computable<A,V> c){
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if(f == null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws InterruptedException {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                //当缓存中没有值时才会向缓存中添加，返回添加之前的值
                f = cache.putIfAbsent(arg,ft);
                if(f == null){
                    //如果确实没有计算过，则开始计算
                    f = ft;
                    ft.run();
                }
            }
            try{
                return f.get();
            }catch (CancellationException e){
                //取消计算,则从缓存中移除
                cache.remove(arg,f);
            }catch (ExecutionException e){
                throw launderThrowable(e.getCause());
            }
        }
    }
}
