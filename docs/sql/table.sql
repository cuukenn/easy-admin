DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`                    bigint       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `created_by`            bigint       NOT NULL DEFAULT -1 COMMENT '创建者',
    `created_time`          datetime     NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `last_modified_by`      bigint       NOT NULL DEFAULT -1 COMMENT '修改者',
    `last_modified_time`    datetime     NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `is_deleted`            tinyint(1)   NOT NULL DEFAULT 0 COMMENT '数据是否被逻辑删除',
    `username`              varchar(20)  NOT NULL COMMENT '用户名',
    `password`              varchar(200) NOT NULL COMMENT '用户名',
    `locked`                tinyint(1)   NOT NULL DEFAULT 0 COMMENT '账户是否被锁',
    `enabled`               tinyint(1)   NOT NULL DEFAULT 0 COMMENT '账户是否启用',
    `is_admin`              tinyint(1)   NOT NULL DEFAULT 0 COMMENT '账户是否为管理员',
    `account_invalid_time`  datetime     NULL     DEFAULT NULL COMMENT '账户过期时间',
    `password_invalid_time` datetime     NULL     DEFAULT NULL COMMENT '密码过期时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uix_username` (`is_deleted`, `username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                 bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `created_by`         bigint      NOT NULL DEFAULT -1 COMMENT '创建者',
    `created_time`       datetime    NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `last_modified_by`   bigint      NOT NULL DEFAULT -1 COMMENT '修改者',
    `last_modified_time` datetime    NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `is_deleted`         tinyint(1)  NOT NULL DEFAULT 0 COMMENT '数据是否被逻辑删除',
    `role`               varchar(20) NOT NULL COMMENT '角色名',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uix_role` (`is_deleted`, `role`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`                 bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `created_by`         bigint      NOT NULL DEFAULT -1 COMMENT '创建者',
    `created_time`       datetime    NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `last_modified_by`   bigint      NOT NULL DEFAULT -1 COMMENT '修改者',
    `last_modified_time` datetime    NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `is_deleted`         tinyint(1)  NOT NULL DEFAULT 0 COMMENT '数据是否被逻辑删除',
    `user_id`            varchar(20) NOT NULL COMMENT '用户ID',
    `role_id`            varchar(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uix_ru` (`is_deleted`, `user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户-角色表'
  ROW_FORMAT = Dynamic;