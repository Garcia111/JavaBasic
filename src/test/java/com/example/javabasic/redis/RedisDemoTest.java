package com.example.javabasic.redis;

import com.example.javabasic.redis_action.redis_practice_1.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：Cheng.
 * @date：Created in 14:21 2019/11/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoTest {


    @Autowired
    private RedisDao  redisDao;

    @Test
    public void testRedis(){
        redisDao.setKey("name","tangyuee");
        redisDao.setKey("age","11");
        System.out.println(redisDao.getValue("name"));
        System.out.println(redisDao.getValue("age"));
    }
}













