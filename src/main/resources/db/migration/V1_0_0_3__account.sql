
CREATE TABLE account
(
    id                 BIGINT                      NOT NULL COMMENT '用户序列号',

    name               CHARACTER VARYING(16)       NOT NULL COMMENT '用户姓名',
    gender             SMALLINT                    NOT NULL DEFAULT 1 COMMENT '用户性别，1：男；2：女，默认为 1',
    mdn                CHARACTER VARYING(16)       NOT NULL COMMENT '用户手机号',
    email              CHARACTER VARYING(64)       DEFAULT NULL COMMENT '用户邮箱',
    notes              CHARACTER VARYING(512)      DEFAULT NULL COMMENT '备注信息',

    status             SMALLINT                    NOT NULL DEFAULT 1 COMMENT '用户状态，1：有效状态；2：删除状态',

    created_by         BIGINT                      NOT NULL COMMENT '创建人 ID',
    created_time       TIMESTAMP                   NOT NULL DEFAULT now() COMMENT '创建时间',
    last_modified_by   BIGINT                      NOT NULL COMMENT '修改人 ID',
    last_modified_time TIMESTAMP                   NOT NULL DEFAULT now() COMMENT '修改时间',
    version            BIGINT                      NOT NULL DEFAULT 0 COMMENT '乐观计数器',

    CONSTRAINT account_user_pkey PRIMARY KEY (id)
)COMMENT =  '用户信息表';


