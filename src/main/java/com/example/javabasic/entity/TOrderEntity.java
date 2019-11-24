package com.example.javabasic.entity;

import com.alibaba.fastjson.JSON;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "t_order")
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@EntityListeners({AuditingEntityListener.class})
public class TOrderEntity implements Serializable {


    @Id
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;


    public static byte[] tOrderEntitySerializer(TOrderEntity tOrderEntity) {
        return JSON.toJSONString(tOrderEntity).getBytes();
    }

}
