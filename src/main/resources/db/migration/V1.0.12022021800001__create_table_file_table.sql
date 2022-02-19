
-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
                         `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文件表',
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
                         `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
                         `file_type` int(11) NOT NULL DEFAULT 2 COMMENT '类型:1文件夹 2文件',
                         `type` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '文件类型:1.图片 2.视频 3.音频 4.文档 ',
                         `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
                         `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级id最高级为0',
                         `user_id` int(11) NOT NULL COMMENT '用户id',
                         `space_id` int(11) NULL DEFAULT NULL COMMENT '空间id',
                         `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除 -1:删除 0不删除',
                         `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                         `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
