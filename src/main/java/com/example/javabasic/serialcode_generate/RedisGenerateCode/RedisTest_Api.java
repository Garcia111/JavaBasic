package com.example.javabasic.serialcode_generate.RedisGenerateCode;

import com.xuanner.seq.range.impl.redis.RedisSeqRangeMgr;
import com.xuanner.seq.sequence.impl.DefaultRangeSequence;
import org.junit.Before;
import org.junit.Test;

/**
 * @author：Cheng.
 * @date：Created in 16:45 2019/11/26
 */
public class RedisTest_Api {

    private com.xuanner.seq.sequence.Sequence userSeq;

    @Before
    public void setup() {

        //利用Redis获取区间管理器
        RedisSeqRangeMgr redisSeqRangeMgr = new RedisSeqRangeMgr();
        redisSeqRangeMgr.setIp("127.0.0.1");//IP[必选]
        redisSeqRangeMgr.setPort(6379);//PORT[必选]
//        redisSeqRangeMgr.setAuth("root");//密码[可选]看你的redis服务端配置是否需要密码
        redisSeqRangeMgr.setStep(10);//每次取数步长[可选] 默认：1000
        redisSeqRangeMgr.init();
        //构建序列号生成器
        DefaultRangeSequence defaultRangeSequence = new DefaultRangeSequence();
        defaultRangeSequence.setName("user");
        defaultRangeSequence.setSeqRangeMgr(redisSeqRangeMgr);
        userSeq = defaultRangeSequence;
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println("++++++++++id:" + userSeq.nextValue());
        }
        System.out.println("interval time:" + (System.currentTimeMillis() - start));
    }
}
