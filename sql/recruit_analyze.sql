/*
 Navicat Premium Data Transfer

 Source Server         : 101.132.99.210
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 101.132.99.210:3306
 Source Schema         : recruit_analyze

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 20/04/2019 16:21:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_size_id` int(11) NOT NULL,
  `company_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `size_index`(`company_size_id`) USING BTREE,
  INDEX `type_index`(`company_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50200 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company_size
-- ----------------------------
DROP TABLE IF EXISTS `company_size`;
CREATE TABLE `company_size`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `size_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company_type
-- ----------------------------
DROP TABLE IF EXISTS `company_type`;
CREATE TABLE `company_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for edu_level
-- ----------------------------
DROP TABLE IF EXISTS `edu_level`;
CREATE TABLE `edu_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `edu_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` bigint(20) NOT NULL,
  `job_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位号码',
  `job_name_id` int(11) NOT NULL COMMENT '职位名',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位薪资',
  `empl_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位类型（如：全职或实习）',
  `recruit_count` int(11) NULL DEFAULT NULL COMMENT '职位数',
  `working_exp_id` int(11) NOT NULL COMMENT '工作经验',
  `edu_level_id` int(11) NOT NULL COMMENT '学历需求',
  `company_id` bigint(20) NOT NULL COMMENT '公司',
  `city_id` int(11) NOT NULL COMMENT '所在城市',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_date`(`create_date`) USING BTREE,
  INDEX `end_date`(`end_date`) USING BTREE,
  INDEX `city_id`(`city_id`, `end_date`) USING BTREE,
  INDEX `job_name_id`(`job_name_id`, `create_date`, `city_id`) USING BTREE,
  INDEX `job_name_id_2`(`job_name_id`, `end_date`, `working_exp_id`) USING BTREE,
  INDEX `job_name_id_3`(`job_name_id`, `end_date`, `edu_level_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_0
-- ----------------------------
DROP TABLE IF EXISTS `job_0`;
CREATE TABLE `job_0`  (
  `id` bigint(20) NOT NULL,
  `job_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位号码',
  `job_name_id` int(11) NOT NULL COMMENT '职位名',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位薪资',
  `empl_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位类型（如：全职或实习）',
  `recruit_count` int(11) NULL DEFAULT NULL COMMENT '职位数',
  `working_exp_id` int(11) NOT NULL COMMENT '工作经验',
  `edu_level_id` int(11) NOT NULL COMMENT '学历需求',
  `company_id` bigint(20) NOT NULL COMMENT '公司',
  `city_id` int(11) NOT NULL COMMENT '所在城市',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `city_id`(`end_date`, `city_id`) USING BTREE,
  INDEX `job_name_id`(`job_name_id`, `city_id`, `create_date`) USING BTREE,
  INDEX `job_name_id_2`(`job_name_id`, `working_exp_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_3`(`job_name_id`, `edu_level_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_4`(`job_name_id`, `end_date`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_1
-- ----------------------------
DROP TABLE IF EXISTS `job_1`;
CREATE TABLE `job_1`  (
  `id` bigint(20) NOT NULL,
  `job_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位号码',
  `job_name_id` int(11) NOT NULL COMMENT '职位名',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位薪资',
  `empl_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位类型（如：全职或实习）',
  `recruit_count` int(11) NULL DEFAULT NULL COMMENT '职位数',
  `working_exp_id` int(11) NOT NULL COMMENT '工作经验',
  `edu_level_id` int(11) NOT NULL COMMENT '学历需求',
  `company_id` bigint(20) NOT NULL COMMENT '公司',
  `city_id` int(11) NOT NULL COMMENT '所在城市',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `city_id`(`end_date`, `city_id`) USING BTREE,
  INDEX `job_name_id`(`job_name_id`, `city_id`, `create_date`) USING BTREE,
  INDEX `job_name_id_2`(`job_name_id`, `working_exp_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_3`(`job_name_id`, `edu_level_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_4`(`job_name_id`, `end_date`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_2
-- ----------------------------
DROP TABLE IF EXISTS `job_2`;
CREATE TABLE `job_2`  (
  `id` bigint(20) NOT NULL,
  `job_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位号码',
  `job_name_id` int(11) NOT NULL COMMENT '职位名',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位薪资',
  `empl_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位类型（如：全职或实习）',
  `recruit_count` int(11) NULL DEFAULT NULL COMMENT '职位数',
  `working_exp_id` int(11) NOT NULL COMMENT '工作经验',
  `edu_level_id` int(11) NOT NULL COMMENT '学历需求',
  `company_id` bigint(20) NOT NULL COMMENT '公司',
  `city_id` int(11) NOT NULL COMMENT '所在城市',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `city_id`(`end_date`, `city_id`) USING BTREE,
  INDEX `job_name_id`(`job_name_id`, `city_id`, `create_date`) USING BTREE,
  INDEX `job_name_id_2`(`job_name_id`, `working_exp_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_3`(`job_name_id`, `edu_level_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_4`(`job_name_id`, `end_date`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_merge
-- ----------------------------
DROP TABLE IF EXISTS `job_merge`;
CREATE TABLE `job_merge`  (
  `id` bigint(20) NOT NULL,
  `job_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位号码',
  `job_name_id` int(11) NOT NULL COMMENT '职位名',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位薪资',
  `empl_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位类型（如：全职或实习）',
  `recruit_count` int(11) NULL DEFAULT NULL COMMENT '职位数',
  `working_exp_id` int(11) NOT NULL COMMENT '工作经验',
  `edu_level_id` int(11) NOT NULL COMMENT '学历需求',
  `company_id` bigint(20) NOT NULL COMMENT '公司',
  `city_id` int(11) NOT NULL COMMENT '所在城市',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `city_id`(`end_date`, `city_id`) USING BTREE,
  INDEX `job_name_id`(`job_name_id`, `city_id`, `create_date`) USING BTREE,
  INDEX `job_name_id_2`(`job_name_id`, `working_exp_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_3`(`job_name_id`, `edu_level_id`, `end_date`) USING BTREE,
  INDEX `job_name_id_4`(`job_name_id`, `end_date`) USING BTREE
) ENGINE = MRG_MYISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic UNION = (`job_0`,`job_1`,`job_2`);

-- ----------------------------
-- Table structure for job_name
-- ----------------------------
DROP TABLE IF EXISTS `job_name`;
CREATE TABLE `job_name`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_welfare
-- ----------------------------
DROP TABLE IF EXISTS `job_welfare`;
CREATE TABLE `job_welfare`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` bigint(20) NOT NULL,
  `welfare_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `job_index`(`job_id`) USING BTREE,
  INDEX `welfare_index`(`welfare_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4383178 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for welfare
-- ----------------------------
DROP TABLE IF EXISTS `welfare`;
CREATE TABLE `welfare`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `welfare_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 986 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for working_exp
-- ----------------------------
DROP TABLE IF EXISTS `working_exp`;
CREATE TABLE `working_exp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `working_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
