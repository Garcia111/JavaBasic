package com.example.javabasic.interview.interview20191117.repository;

import com.example.javabasic.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author：Cheng.
 * @date：Created in 10:51 2019/11/18
 */
@Repository
public interface OrderInfoRepository extends JpaSpecificationExecutor<OrderEntity>,
        JpaRepository<OrderEntity,Long> {
}
