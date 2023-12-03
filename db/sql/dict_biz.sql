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

 Date: 03/12/2023 12:52:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_biz
-- ----------------------------
DROP TABLE IF EXISTS `dict_biz`;
CREATE TABLE `dict_biz`  (
  `id` bigint NOT NULL COMMENT '主键',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父主键',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典码',
  `dict_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典名称',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典备注',
  `is_sealed` tinyint(1) NULL DEFAULT 0 COMMENT '是否已封存',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '业务字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_biz
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
