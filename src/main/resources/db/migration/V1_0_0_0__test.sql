
DROP TABLE IF EXISTS user;
CREATE TABLE user(
  id   BIGINT  NOT NULL,
  name CHARACTER VARYING(20) NOT NULL ,
  phone character VARYING(16) NOT NULL,


  CONSTRAINT user_pkey PRIMARY KEY (id)
);



DROP TABLE IF EXISTS order_info;
CREATE TABLE order_info(
 id       BIGINT  NOT NULL,
 user_id  BIGINT  NOT NULL,
 amount  BIGINT NOT NULL,


 CONSTRAINT order_info_pkey PRIMARY KEY (id)
);