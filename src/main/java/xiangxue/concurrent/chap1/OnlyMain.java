package xiangxue.concurrent.chap1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * @author：Cheng.
 * @date：Created in 13:55 2019/12/26
 */
public class OnlyMain {
    public static void main(String[] args) {
        //JAVA虚拟机线程管理系统的接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取线程monitor和synchronize信息，只是获取线程堆栈信息
        //todo 话说回来，线程monitor是什么东西？
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        Arrays.stream(threadInfos).forEach(threadInfo-> System.out.println("[threadId :" + threadInfo.getThreadId()
                                                +"  threadName:"+threadInfo.getThreadName()
                                                +"  threadState:"+threadInfo.getThreadState()+"]"));

    }
}
