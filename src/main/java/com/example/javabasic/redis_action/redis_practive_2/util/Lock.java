package com.example.javabasic.redis_action.redis_practive_2.util;

public interface Lock {
    void lock(String key);
    boolean tryLock(String key);
    void unlock(String key) throws Exception;
}
