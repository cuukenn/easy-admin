# admin/123456
# user/123456
INSERT INTO `sys_user`(`id`, `created_by`, `created_time`, `last_modified_by`, `last_modified_time`, `is_deleted`,
                       `username`, `password`,
                       `locked`, `enabled`, `is_admin`, `account_invalid_time`, `password_invalid_time`)
VALUES (1, -1, now(), -1, now(), 0, 'admin', '{bcrypt}$2a$10$DGBUx7O/t6ZSg7tdFSSnzu2x8vEWbbZ47gLdCCuyMbQI6y2Cv2SEa',
        0, 1, 1, null, null),
       (2, -1, now(), -1, now(), 0, 'user', '{bcrypt}$2a$10$DGBUx7O/t6ZSg7tdFSSnzu2x8vEWbbZ47gLdCCuyMbQI6y2Cv2SEa',
        0, 1, 0, null, null)