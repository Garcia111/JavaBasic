package xiangxue.concurrent.concurrent_in_action.chap5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量来约束容器
 * @author：Cheng.
 * @since： 1.0.0
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore sem;


    public BoundedHashSet(int bound){
        //使用实例限制原理创建线程安全类包装器
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        //首先获得信号量许可
        sem.acquire();

        boolean wasAddedd = false;

        try{
            wasAddedd = set.add(o);
            return wasAddedd;
        }
        finally {
            if(!wasAddedd){
                //如果没有添加成功，则释放信号量许可
                sem.release();
            }
        }
    }

    public boolean remove(Object o){
        boolean wasRemoved = set.remove(o);
        if(wasRemoved){
            sem.release();
        }
        return wasRemoved;
    }

}
