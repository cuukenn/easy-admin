INSERT INTO sys_user(`id`, `username`, `nickname`, `gender`, `avatar`, `location`, `email`, `status`,
                     `deleted`, `created_by`, `last_modified_by`, `created_time`, `last_modified_time`)
VALUES (1, 'admin', '管理员1', 0, '1.png', '北京市', '123456@abc.com', true, 0, -1, -1, now(), now()),
       (2, 'user', '普通用户1', 2, '1.png', '四川省', '123456@abc.com', true, 0, -1, -1, now(), now());