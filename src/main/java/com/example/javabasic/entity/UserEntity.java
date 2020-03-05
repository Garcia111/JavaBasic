package com.example.javabasic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author：Cheng.
 * @date：Created in 9:28 2019/11/18
 */
@Data
@ToString
@EqualsAndHashCode
@Table(name = "user")
@Entity
@Accessors(chain = true)
@EntityListeners({AuditingEntityListener.class})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "name",nullable = false)
    private String name;


    @Column(name = "phone",nullable = false)
    private String phone;
}
