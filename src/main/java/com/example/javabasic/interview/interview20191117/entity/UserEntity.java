package com.example.javabasic.interview.interview20191117.entity;

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
@MappedSuperclass
@Accessors(chain = true)
@EntityListeners({AuditingEntityListener.class})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1)
    private Long id;


    @Column(name = "name",nullable = false)
    private String name;


    @Column(name = "phone",nullable = false)
    private String phone;
}
