/*
 * Copyright (C) 2018 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created on 2018/5/31.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
class BasicEntity implements Serializable {

    private static final long serialVersionUID = 3312129978855359313L;
}
