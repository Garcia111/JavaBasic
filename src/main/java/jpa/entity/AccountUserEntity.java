/*
 * Copyright (C) 2020 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
@Data
@Entity
@Table(name = "account_user")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class AccountUserEntity extends BasicEntity {
    private static final long serialVersionUID = 3076272617960319521L;

    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false)
    private UUID id;

    @Column(name = "account_id", columnDefinition = "uuid", nullable = false)
    private UUID accountId;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "status", nullable = false)
    private Short status;

    @Column(name = "enterprise_id", columnDefinition = "uuid", nullable = false)
    private UUID enterpriseId;

    @Column(name = "department_id", columnDefinition = "uuid", nullable = false)
    private UUID departmentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private Short gender;

    @Column(name = "mdn", nullable = false)
    private String mdn;

    @Column(name = "email")
    private String email;

    @Column(name = "notes")
    private String notes;

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "uuid", nullable = false)
    private UUID createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "uuid", nullable = false)
    private UUID lastModifiedBy;

    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private DateTime createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_time", nullable = false)
    private DateTime lastModifiedTime;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
