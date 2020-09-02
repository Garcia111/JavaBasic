为什么线程方法中stop() suspend() resume() destroy()不建议使用？
    suspend()----中断线程，但是线程不会释放锁就会进入睡眠，有可能会导致死锁；
    stop()-------强制干掉线程，过于野蛮，导致线程所占用的资源不会正常的释放。


   转而使用Thread中提供的interrupt()等方法
    interrupt() & interrupted() & isInterrupted()
    1.interrupt()
        对线程进行中断，只是改写一下线程的中断标志位，不代表线程立即终止工作，线程完全可以不理会此中断请求。
        简而言之，只是打了一个招呼。如果想要在设置中断之后，立即使线程停止，应该使用isInterrupted()或者interrupted()
        手动中止线程。
    2.isInterrupted()---当前线程是否处于中断状态
    3.interrupted()-----当前线程是否处于中断状态，检查完之后将线程复位

















