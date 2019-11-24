package com.example.javabasic.redis_action.redis_practice_3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;


@Repository

public class PersonDao {


    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    //下面这种方式未调通
//    @Resource(name = "redisTemplate")
//    ValueOperations<Object, Object> valOps;

    public void save(Person person) {
        ValueOperations<Object, Object> valOps = redisTemplate.opsForValue();
        valOps.set(person.getId(), person);
    }

    public Person getPerson(String id) {
        ValueOperations<Object, Object> valOps = redisTemplate.opsForValue();
        return (Person) valOps.get(id);
    }

}