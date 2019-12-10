package com.example.javabasic.serialcode_generate.generator;

import com.xuanner.seq.sequence.Sequence;
import com.xuanner.seq.sequence.impl.DefaultRangeSequence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：Cheng.
 * @date：Created in 15:23 2019/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceTest_Spring {

    @Autowired
    private Sequence userSeq;


    @Before
    public void setUp(){
        userSeq = new DefaultRangeSequence();
    }

    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("++++++++++id:" + userSeq.nextValue());
        }
    }

}
