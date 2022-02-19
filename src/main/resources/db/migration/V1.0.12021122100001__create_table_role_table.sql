DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
 `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色表',
 `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
 `type` int(11) NULL DEFAULT 1 COMMENT '角色类型 适用多端 1:后端 2:小程序后端',
 `deleted` int(11) NULL DEFAULT 0 COMMENT '是否删除 -1:删除 0不删除',
 `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
 `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`(
 `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限表',
 `permission_name` varchar(255) CHARACTER SET utf8 collate utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
 `type` int(11) NOT NULL default 1 COMMENT '权限类型 1:管理员后端 2 小程序后端',
 `path`varchar(255) CHARACTER SET utf8 collate utf8_general_ci NULL DEFAULT NULL COMMENT'路由路径',
 `icon`varchar(255) CHARACTER SET utf8 collate utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
 `is_open` int(11) NOT NULL default 0 comment '是否开启 1:开启 2关闭',
 `sort_number` int(11) NOT NULL default 0 comment '排序',
 `parent_id` int(11) NOT NULL default 0 comment '父级id',
 `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
 `updated_at` timestamp(0) NOT NULL DEFAULT  CURRENT_TIMESTAMP(0),
 PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`(
`id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色权限表',
`role_id` int(11) NOT NULL default 0 comment '角色id',
`permission_id` int(11) NOT NULL default 0 comment '权限id',
`deleted` int(11) NULL default 0 comment '是否删除 -1:删除 0不删除',
`created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
`updated_at` timestamp(0) NOT NULL DEFAULT  CURRENT_TIMESTAMP(0),
PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`(
 `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户角色表',
 `role_id` int(11) NOT NULL default 0 comment '角色id',
 `user_id` int(11) NOT NULL default 0 comment '用户id',
 `deleted` int(11) NULL default 0 comment '是否删除 -1:删除 0不删除',
 `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
 `updated_at` timestamp(0) NOT NULL DEFAULT  CURRENT_TIMESTAMP(0),
 PRIMARY KEY (`id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET utf8 COLLATE =utf8_general_ci ROW_FORMAT=DYNAMIC ;


