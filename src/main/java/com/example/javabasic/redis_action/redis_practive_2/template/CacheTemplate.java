//package com.example.javabasic.redis_action.redis_practive_2.template;
//
//
//import com.example.javabasic.entity.NullValueResultDO;
//import com.example.javabasic.entity.Response;
//import com.example.javabasic.redis_action.redis_practive_2.filter.RedisBloomFilter;
//import com.example.javabasic.redis_action.redis_practive_2.util.RedisLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class CacheTemplate<T> {
//
////    @Autowired
////    private ValueOperations<String,Object> valueOperations;
//
//    @Autowired
//    RedisBloomFilter bloomFilter;
//
//
//    @Autowired
//    private RedisLock redisLock;
//
////    @Autowired
////    private Redisson redisson;
//
////    @Autowired
////    OrderRepository orderRepository;
//
//
//
//    public Response findCache(String key, long expire, TimeUnit unit, CacheLoadble<T> cacheLoadble){
//        //查询缓存
//        Object redisObj = valueOperations.get(String.valueOf(key));
//        if(redisObj!=null){
//            return new Response().setCode(200).setData(redisObj).setMsg("OK");
//        }
//        synchronized (this){
//            redisObj = valueOperations.get(String.valueOf(key));
//            if(redisObj!=null){
//                return new Response().setCode(200).setData(redisObj).setMsg("OK");
//            }
//            T load = cacheLoadble.load();
//            valueOperations.set(String.valueOf(key), load,expire, unit);  //加入缓存
//            return new Response().setCode(200).setData(load).setMsg("OK");
//        }
//    }
//
//
//    public Response redisFindCache(String key, long expire, TimeUnit unit, CacheLoadble<T> cacheLoadble, boolean b){
//        //判断是否走过滤器
//        if(b){
//            //先走过滤器
//            boolean bloomExist = bloomFilter.isExist(String.valueOf(key));
//            if(!bloomExist){
//                return new Response().setCode(600).setData(null).setMsg("查询无果");
//            }
//        }
//        //查询缓存
//        Object redisObj = valueOperations.get(String.valueOf(key));
//        //命中缓存
//        if(redisObj != null) {
//            //正常返回数据
//            return new Response().setCode(200).setData(redisObj).setMsg("OK");
//        }
////        RLock lock0 = redisson.getLock("{taibai0}:" + key);
////        RLock lock1 = redisson.getLock("{taibai1}:" + key);
////        RLock lock2 = redisson.getLock("{taibai2}:" + key);
////        RedissonMultiLock lock = new RedissonMultiLock(lock0,lock1, lock2);
//        try {
//        redisLock.lock(key);
////        lock.lock();
//        //查询缓存
//        redisObj = valueOperations.get(String.valueOf(key));
//        //命中缓存
//        if(redisObj != null) {
//            //正常返回数据
//            return new Response().setCode(200).setData(redisObj).setMsg("OK");
//        }
//        T load = cacheLoadble.load();//查询数据库
//        if (load != null) {
//            valueOperations.set(key, load,expire, unit);  //加入缓存
//            return new Response().setCode(200).setData(load).setMsg("OK");
//        }
//            return new Response().setCode(500).setData(new NullValueResultDO()).setMsg("查询无果");
//        }finally {
//            redisLock.unlock(key);
////            lock.unlock();
//        }
//    }
//
//}
