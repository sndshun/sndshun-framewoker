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

 Date: 03/12/2023 22:30:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名称',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签描述',
  `slug` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签别名',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用; 0: 禁用, 1: 启用',
  `category_id` bigint NULL DEFAULT NULL COMMENT '所属分类;所属分类id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属分类;所属分类名',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (1, 'Java', '关于Java编程语言的相关内容。', 'java', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (2, 'JavaScript', 'JavaScript编程语言的技术分享和实践经验。', 'javascript', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (3, 'Python', 'Python编程语言的特性和应用案例分享。', 'python', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (4, 'Web开发', '关于Web开发的前沿技术和最佳实践。', 'web-development', 1, 2, '前端', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:49');
INSERT INTO `blog_tag` VALUES (5, '数据科学', '数据科学领域的工具和方法分享。', 'data-science', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (6, '人工智能', '人工智能和机器学习领域的相关标签。', 'ai', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (7, '区块链', '区块链技术和加密货币的标签。', 'blockchain', 1, 1, '前端', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:13:02');
INSERT INTO `blog_tag` VALUES (8, 'DevOps', 'DevOps实践和工具的相关标签。', 'devops', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (9, '云计算', '云服务和云计算平台的标签。', 'cloud-computing', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (10, '前端框架', '各种前端框架的技术和使用心得。', 'frontend-frameworks', 1, 1, '前端', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:57');
INSERT INTO `blog_tag` VALUES (11, '后端框架', '后端开发框架的相关标签。', 'backend-frameworks', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (12, '数据库', '数据库技术和管理的相关标签。', 'database', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (13, '编程实践', '编程实践和最佳编码实践的标签。', 'coding-practices', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (14, '前沿技术', '前沿技术和科技趋势的标签。', 'emerging-technologies', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (15, '开源项目', '开源项目和社区的标签。', 'open-source', 1, 2, '前端', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:13:04');
INSERT INTO `blog_tag` VALUES (16, '网络安全', '网络安全和信息安全的相关标签。', 'cybersecurity', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (17, '物联网', '物联网技术和应用的标签。', 'iot', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (18, '虚拟现实', '虚拟现实和增强现实技术的标签。', 'vr-ar', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (19, '编程语言比较', '各种编程语言比较和评测的标签。', 'language-comparison', 1, 1, '科技', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:12:29');
INSERT INTO `blog_tag` VALUES (20, '技术漫谈', '技术相关的各类话题和趣闻。', 'tech-talk', 1, 1, '前端', NULL, 0, 0, NULL, '2023-12-03 18:17:20', NULL, '2023-12-03 21:13:01');

SET FOREIGN_KEY_CHECKS = 1;
