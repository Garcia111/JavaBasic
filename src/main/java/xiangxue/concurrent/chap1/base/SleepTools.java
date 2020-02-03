package xiangxue.concurrent.chap1.base;

import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 17:13 2020/1/19
 */
public class SleepTools {

    public static final void sleepSeconds(int seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){}
    }


    /**
     * 按毫秒数休眠
     * @param seconds 毫秒数
     */
    public static final void sleepMs(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
