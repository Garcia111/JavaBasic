package com.example.javabasic.redis_action.redis_practive_2.service.impl;

import com.example.javabasic.entity.NullValueResultDO;
import com.example.javabasic.entity.TOrderEntity;
import com.example.javabasic.entity.Response;
import com.example.javabasic.redis_action.redis_practive_2.dao.TOrderEntityRepository;
import com.example.javabasic.redis_action.redis_practive_2.filter.RedisBloomFilter;
import com.example.javabasic.redis_action.redis_practive_2.service.OrderService;
import com.example.javabasic.redis_action.redis_practive_2.util.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

//@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    TOrderEntityRepository TOrderEntityRepository;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    ValueOperations valueOperations;

//    @Autowired
//    CacheTemplate cacheTemplate;

    @Autowired
    RedisBloomFilter bloomFilter;


    @Autowired
    private RedisLock redisLock;



    @Override
    public Integer insertOrder(TOrderEntity TOrderEntity) {
        Integer integer = TOrderEntityRepository.save(TOrderEntity).getId();
        return integer;
    }




//    @Override
//    public Response selectOrderById(Integer id) {
//        return cacheTemplate.redisFindCache(String.valueOf(id), 10, TimeUnit.MINUTES, new CacheLoadble<TOrderEntity>() {
//            @Override
//            public TOrderEntity load() {
//                return orderMapper.selectOrderById(id);
//            }
//        },false);
//    }






//    @Override
//    public Response selectOrderById(Integer id) {
//        //查询缓存
//        Object redisObj = valueOperations.get(String.valueOf(id));
//        //命中缓存
//        if(redisObj != null) {
//            //正常返回数据
//            return new Response().setCode(200).setData(redisObj).setMsg("OK");
//        }
//        try {
//                redisLock.lock(String.valueOf(id));
////                查询缓存
//                redisObj = valueOperations.get(String.valueOf(id));
////                命中缓存
//                if(redisObj != null) {
//                    //正常返回数据
//                    return new Response().setCode(200).setData(redisObj).setMsg("OK");
//                }
//                TOrderEntity order = orderMapper.selectOrderById(id); //查数据库
//                if (order != null) {
//                    valueOperations.set(String.valueOf(id), order);  //加入缓存
//                    return new Response().setCode(200).setData(order).setMsg("OK");
//                }
//        }finally {
//            redisLock.unlock(String.valueOf(id));
//        }
//        return new Response().setCode(500).setData(new NullValueResultDO()).setMsg("查询无果");
//    }


    /**
     * 常规的查询缓存的思路：首先查询缓存，缓存中存在则直接从缓存中返回响应对象，
     * 缓存中没有，则去查询数据库，如果查到，将数据库中的值放入缓存中，然后将响应返回；
     * 如果没有查到，则返回查询无果
     * @param id
     * @return
     */
    @Override
    public Response selectOrderById(Integer id) {
        //查询缓存
        TOrderEntity redisObj = (TOrderEntity)redisTemplate.opsForValue().get(id);
        //命中缓存
        if(redisObj != null) {
            //正常返回数据
            return new Response().setCode(200).setData(redisObj).setMsg("OK");
        }

        Optional<TOrderEntity> op = TOrderEntityRepository.findById(id); //查数据库
        if (op.isPresent()) {
                redisTemplate.opsForValue().set(id, op.get(),5,TimeUnit.SECONDS);  //加入缓存
                return new Response().setCode(200).setData(op.get()).setMsg("OK");
            }
        return new Response().setCode(500).setMsg("查询无果");
    }


    /**
     * 使用布隆过滤器解决缓存穿透
     * @param id
     * @return
     */
    public  Response selectOrderById2(Integer id) {
       if(!bloomFilter.isExist(String.valueOf(id))){
           return new Response().setMsg("查询无果").setData(new NullValueResultDO()).setCode(600);
       }
        //查询缓存
        Object redisObj = valueOperations.get(String.valueOf(id));

        //命中缓存
        if(redisObj != null) {
            //正常返回数据
            return new Response().setCode(200).setData(redisObj).setMsg("OK");
        }
        try{
            //使用分布式锁解决缓存击穿
            redisLock.lock(String.valueOf(id));
            //命中缓存
            if(redisObj != null) {
                //正常返回数据
                return new Response().setCode(200).setData(redisObj).setMsg("OK");
            }
            Optional<TOrderEntity> op = TOrderEntityRepository.findById(id);
            if (op.isPresent()) {
                redisTemplate.opsForValue().set(id, op.get(),5,TimeUnit.SECONDS);  //加入缓存
                return new Response().setCode(200).setData(op.get()).setMsg("OK");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisLock.unlock(String.valueOf(id));
        }
        return new Response().setCode(500).setMsg("查询无果");
    }

    /**
     * 缓存空对象解决缓存穿透
     * @param id
     * @return
     */
    public Response selectOrderById1(Integer id) {
        //查询缓存
        Object redisObj = redisTemplate.opsForValue().get(id);


        //命中缓存
        if(redisObj != null) {
            if(redisObj instanceof NullValueResultDO){
                return new Response().setCode(500).setMsg("查询无果");
            }else{
                //正常返回数据
                return new Response().setCode(200).setData(redisObj).setMsg("OK");
            }
        }

        Optional<TOrderEntity> op = TOrderEntityRepository.findById(id); //查数据库
        if (op.isPresent()) {
            redisTemplate.opsForValue().set(id, op.get(),5,TimeUnit.SECONDS);  //加入缓存
            return new Response().setCode(200).setData(op.get()).setMsg("OK");
        }else{
            redisTemplate.opsForValue().set(id,new NullValueResultDO(),5,TimeUnit.SECONDS);
        }
        return new Response().setCode(500).setMsg("查询无果");
    }

//    public Response synchronizedSelectOrderById(Integer id) {
//        return cacheTemplate.findCache(String.valueOf(id), 10, TimeUnit.MINUTES, new CacheLoadble<TOrderEntity>() {
//            @Override
//            public TOrderEntity load() {
//                return TOrderEntityRepository.findById(id).get();
//            }
//        });
//    }

}
