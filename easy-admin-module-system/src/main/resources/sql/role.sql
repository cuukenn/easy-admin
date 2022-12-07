INSERT INTO sys_role(`id`, `name`, `permission`, `description`, `status`,
                     `deleted`, `created_by`, `last_modified_by`, `created_time`, `last_modified_time`)
VALUES (1, '管理员', 'ADMIN', '管理员账户，具备所有权限', true, 0, -1, -1, now(), now()),
       (2, '普通用户', 'USER', '普通账户，仅具备普通权限', true, 0, -1, -1, now(), now());