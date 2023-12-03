/*
 Navicat Premium Data Transfer

 Source Server         : 华为云
 Source Server Type    : MySQL
 Source Server Version : 80023 (8.0.23)
 Source Host           : 124.70.45.144:3306
 Source Schema         : framework

 Target Server Type    : MySQL
 Target Server Version : 80023 (8.0.23)
 File Encoding         : 65001

 Date: 03/12/2023 22:29:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_archive
-- ----------------------------
DROP TABLE IF EXISTS `blog_archive`;
CREATE TABLE `blog_archive`  (
  `id` bigint NOT NULL COMMENT '归档ID',
  `archive_date` datetime NOT NULL COMMENT '归档日期',
  `article_count` int NOT NULL DEFAULT 0 COMMENT '文章数量',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '归档表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_archive
-- ----------------------------
INSERT INTO `blog_archive` VALUES (1, '2022-01-01 00:00:00', 10, NULL, 0, 0, NULL, '2023-12-03 18:20:29', NULL, '2023-12-03 18:20:29');
INSERT INTO `blog_archive` VALUES (2, '2022-02-01 00:00:00', 15, NULL, 0, 0, NULL, '2023-12-03 18:20:29', NULL, '2023-12-03 18:20:29');
INSERT INTO `blog_archive` VALUES (3, '2022-03-01 00:00:00', 8, NULL, 0, 0, NULL, '2023-12-03 18:20:29', NULL, '2023-12-03 18:20:29');
INSERT INTO `blog_archive` VALUES (4, '2022-04-01 00:00:00', 12, NULL, 0, 0, NULL, '2023-12-03 18:20:29', NULL, '2023-12-03 18:20:29');
INSERT INTO `blog_archive` VALUES (5, '2022-05-01 00:00:00', 20, NULL, 0, 0, NULL, '2023-12-03 18:20:29', NULL, '2023-12-03 18:20:29');
INSERT INTO `blog_archive` VALUES (6, '2022-06-01 00:00:00', 18, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (7, '2022-07-01 00:00:00', 22, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (8, '2022-08-01 00:00:00', 25, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (9, '2022-09-01 00:00:00', 30, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (10, '2022-10-01 00:00:00', 28, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (11, '2022-11-01 00:00:00', 35, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (12, '2022-12-01 00:00:00', 40, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (13, '2023-01-01 00:00:00', 38, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (14, '2023-02-01 00:00:00', 42, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (15, '2023-03-01 00:00:00', 50, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (16, '2023-04-01 00:00:00', 55, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (17, '2023-05-01 00:00:00', 60, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (18, '2023-06-01 00:00:00', 65, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (19, '2023-07-01 00:00:00', 70, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (20, '2023-08-01 00:00:00', 75, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (21, '2023-09-01 00:00:00', 80, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (22, '2023-10-01 00:00:00', 85, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (23, '2023-11-01 00:00:00', 90, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (24, '2023-12-01 00:00:00', 95, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');
INSERT INTO `blog_archive` VALUES (25, '2024-01-01 00:00:00', 100, NULL, 0, 0, NULL, '2023-12-03 18:20:37', NULL, '2023-12-03 18:20:37');

SET FOREIGN_KEY_CHECKS = 1;
