package com.example.javabasic.redis_action.chap1;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * @author：Cheng.
 * @date：Created in 17:47 2019/11/28
 */
public class ArticleVote {

    @Autowired
    private Jedis jedis;


    public void articlePost(){}

    public void articleVote(){

    }
}
