INSERT INTO sys_user(`id`, `username`, `nickname`, `gender`, `avatar`, `location`, `email`, `status`,
                     `deleted`, `created_by`, `last_modified_by`, `created_time`, `last_modified_time`)
VALUES (1, 'admin', '管理员1', 0, '1.png', '北京市', '123456@abc.com', true, 0, -1, -1, now(), now()),
       (2, 'user', '普通用户1', 2, '1.png', '四川省', '123456@abc.com', true, 0, -1, -1, now(), now());

INSERT INTO sys_role(`id`, `name`, `permission`, `description`, `status`,
                     `deleted`, `created_by`, `last_modified_by`, `created_time`, `last_modified_time`)
VALUES (1, '管理员', 'ADMIN', '管理员账户，具备所有权限', true, 0, -1, -1, now(), now()),
       (2, '普通用户', 'USER', '普通账户，仅具备普通权限', true, 0, -1, -1, now(), now());

INSERT INTO sys_user_role(`user_id`, `role_id`)
VALUES (1, 1),
       (2, 2);

INSERT INTO sys_menu(`id`, `parent_id`, `name`, `icon`, `number`, `router_path`, `component_path`, `permission`,
                     `type`, `status`,
                     `deleted`, `created_by`, `last_modified_by`, `created_time`, `last_modified_time`)
VALUES (1, -1, '系统管理', 'setting', 0, 'system', null, null, 0, true, 0, -1, -1, now(), now()),
       (2, 1, '用户管理', 'user', 0, 'user', 'system/user', null, 1, true, 0, -1, -1, now(), now()),
       (3, 2, '用户查询', null, 0, null, null, 'system:user:query', 2, true, 0, -1, -1, now(), now()),
       (4, 2, '用户修改', null, 1, null, null, 'system:user:update', 2, true, 0, -1, -1, now(), now()),
       (5, 2, '用户删除', null, 2, null, null, 'system:user:delete', 2, true, 0, -1, -1, now(), now()),
       (6, 1, '角色管理', 'avatar', 1, 'role', 'system/role', null, 1, true, 0, -1, -1, now(), now()),
       (7, 6, '角色查询', null, 1, null, null, 'system:role:query', 2, true, 0, -1, -1, now(), now()),
       (8, 6, '角色修改', null, 1, null, null, 'system:role:update', 2, true, 0, -1, -1, now(), now()),
       (9, 6, '角色删除', null, 2, null, null, 'system:role:delete', 2, true, 0, -1, -1, now(), now()),
       (10, 1, '菜单管理', 'menu', 2, 'menu', 'system/menu', null, 1, true, 0, -1, -1, now(), now()),
       (11, 10, '菜单查询', null, 0, null, null, 'system:menu:query', 2, true, 0, -1, -1, now(), now()),
       (12, 10, '菜单修改', null, 1, null, null, 'system:menu:update', 2, true, 0, -1, -1, now(), now()),
       (13, 10, '菜单删除', null, 2, null, null, 'system:menu:delete', 2, true, 0, -1, -1, now(), now()),
       (14, -1, '基础设施', 'officeBuilding', 0, 'infra', null, null, 0, true, 0, -1, -1, now(), now()),
       (15, 14, '接口列表', 'document', 0, 'api', 'infra/api', null, 1, true, 0, -1, -1, now(), now()),
       (16, 1, '日志管理', 'document', 4, 'log', null, null, 0, true, 0, -1, -1, now(), now()),
       (17, 16, '日志级别', 'IceCreamSquare', 0, 'level', 'system/log/level', null, 1, true, 0, -1, -1, now(), now()),
       (18, 16, 'API操作记录', 'IceCreamSquare', 0, 'operation', 'system/log/operation', null, 1, true, 0, -1, -1, now(), now());

INSERT INTO sys_role_menu(`role_id`, `menu_id`)
VALUES (2, 1),
       (2, 14);