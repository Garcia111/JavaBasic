package com.example.javabasic.redis_action.redis_practive_2;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.javabasic.entity.Response;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author：Cheng.
 * @date：Created in 9:39 2019/11/24
 */
public class Test {

    private static CountDownLatch countDownLatch = new CountDownLatch(99);

    @org.junit.Test
    public void test() throws InterruptedException{
        TicketsRunBle ticketsRunBle = new TicketsRunBle();

        for(int i=0;i<99;i++){
            Thread thread1 = new Thread(ticketsRunBle,"线程："+i);
            thread1.start();
            countDownLatch.countDown();
        }
        Thread.currentThread().join();
    }


    class TicketsRunBle implements Runnable{

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RestTemplate restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> fastJsonHttpMessageConverters = new ArrayList<>();
            fastJsonHttpMessageConverters.add(new FastJsonHttpMessageConverter());
            restTemplate.setMessageConverters(fastJsonHttpMessageConverters);
            Response forObject = restTemplate.getForObject("http://localhost:8082/selectid?id=2",Response.class);
            System.out.println(forObject);
        }
    }
}



