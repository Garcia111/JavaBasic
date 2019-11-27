package com.example.javabasic.thread.share_resource;

/**
 * @author：Cheng.
 * @date：Created in 16:36 2019/11/27
 */
public class SerialNumberGenerator {

    private static volatile int serialNumber = 0;

    /**
     * 没有进行锁定，有线程同步问题
     * @return
     */
    public static int nextSerialNumber(){
        return serialNumber;
    }

}
