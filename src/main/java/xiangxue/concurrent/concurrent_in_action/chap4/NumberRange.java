package xiangxue.concurrent.concurrent_in_action.chap4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * NumberRange非线程安全，因为其变量需要维持一个额外的约束限制，lower<upper
 * 虽然底层的AtomicInteger是线程安全的，但是组合的类却不是，因为lower与upper不是彼此独立的，NumberRange不能简单地
 * 将线程安全性委托给线程安全的状态变量上。
 * @author：Cheng.
 * @date：Created in 17:02 2020/1/15
 */
public class NumberRange {

    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i){
        //非线程安全，非原子性操作，需要先比较，再赋值
        if(i>upper.get()){
            throw new IllegalArgumentException("can not set lower to "+i+" > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i){
        //非线程安全，非原子性操作，需要先比较，再赋值
        if(i<lower.get()){
            throw new IllegalArgumentException("can not set upper to "+i+"< lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i){
        return (i>= lower.get() && i<=upper.get());
    }
}
