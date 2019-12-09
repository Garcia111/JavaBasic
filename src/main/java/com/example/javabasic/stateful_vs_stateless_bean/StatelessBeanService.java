package com.example.javabasic.stateful_vs_stateless_bean;

import com.example.javabasic.jsonview.User;
import com.example.javabasic.redis_action.redis_practice_1.dao.RedisDao;

import java.util.List;

/**
 * 无状态bean,不能存偖数据。因为没有任何属性，所以是不可变的。只有一系统的方法操作。
 * @author：Cheng.
 * @date：Created in 17:00 2019/12/9
 */
public class StatelessBeanService {

    // 虽然有redisDao属性，但redisDao是没有状态信息的，是Stateless Bean.
    RedisDao redisDao;

    public RedisDao getRedisDao() {
        return redisDao;
    }

    public void setRedisDao(RedisDao redisDao) {
        this.redisDao = redisDao;
    }

    public List<User> findUser(String Id) {
        return null;
    }
}
