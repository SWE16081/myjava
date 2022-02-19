DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
                          `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '后台用户表',
                          `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
                          `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
                          `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
                          `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户头像',
                          `deleted` int(11) NULL default 0 comment '是否删除 -1:删除 0不删除',
                          `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                          `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

