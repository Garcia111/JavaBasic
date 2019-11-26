package com.example.javabasic.thread.exception_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingDefaultHandler {

    public static void main(String[] args){
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
        //设置ExceptionThread类的默认异常处理器为MyUncaughtExceptionHandler
    }
}
