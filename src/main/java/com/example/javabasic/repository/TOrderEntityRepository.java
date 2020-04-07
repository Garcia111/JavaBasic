package com.example.javabasic.repository;

import com.example.javabasic.entity.OrderEntity;
import com.example.javabasic.entity.TOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface TOrderEntityRepository extends JpaRepository<TOrderEntity,Integer>,
        JpaSpecificationExecutor<OrderEntity>  {


    Optional<TOrderEntity> findById(Integer id);


}
