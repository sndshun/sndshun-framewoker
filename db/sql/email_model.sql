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

 Date: 03/12/2023 12:52:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for email_model
-- ----------------------------
DROP TABLE IF EXISTS `email_model`;
CREATE TABLE `email_model`  (
  `id` bigint NOT NULL COMMENT 'id',
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主体',
  `from_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发件人',
  `receive_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件人',
  `send_date` datetime NOT NULL COMMENT '发送时间',
  `seen` tinyint(1) NOT NULL COMMENT '是否已读',
  `priority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优先级',
  `reply_sign` tinyint(1) NOT NULL COMMENT '是否需要回执',
  `size` bigint NULL DEFAULT NULL COMMENT '邮箱大小 bit',
  `template_id` bigint NULL DEFAULT 0 COMMENT '模板id 0:不使用模板',
  `template_data` json NULL COMMENT '模板元数据',
  `content` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '正文',
  `message_number` int NULL DEFAULT NULL COMMENT '第几封邮件',
  `attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附件',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '邮件主体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_model
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
