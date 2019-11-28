package com.example.javabasic.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author：Cheng.
 * @date：Created in 9:46 2019/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PipelineTest {

    private static int count = 10000;

    @Autowired
    ShardedJedis shardedJedis;


    public void userPipeline(){
        ShardedJedisPipeline pipeline = shardedJedis.pipelined();
        long begin = System.currentTimeMillis();
        for(int i = 0;i<count;i++){
            pipeline.set("key_"+i,"value_"+i);
        }
        pipeline.sync();
        shardedJedis.close();
        System.out.println("usePipeline total time:"+(System.currentTimeMillis()-begin));
    }


    public void useNormal(){
        long begin = System.currentTimeMillis();
        for(int i = 0; i<count; i++){
            shardedJedis.set("key_"+i,"value_"+i);
        }
        shardedJedis.close();
        System.out.println("useNormal total time:"+(System.currentTimeMillis()-begin));
    }


    @Test
    public void test(){
        userPipeline();
        useNormal();
    }
}
