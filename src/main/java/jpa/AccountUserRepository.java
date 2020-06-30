/*
 * Copyright (C) 2020 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package jpa;

import jpa.entity.AccountUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
@Repository
public interface AccountUserRepository
        extends JpaSpecificationExecutor<AccountUserEntity>,
        JpaRepository<AccountUserEntity, UUID> {}
