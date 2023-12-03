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

 Date: 03/12/2023 22:29:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `blog_friend_link`;
CREATE TABLE `blog_friend_link`  (
  `id` bigint NOT NULL COMMENT '友链ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '友链名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '友链URL',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '友链描述',
  `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '友链Logo URL',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用; 0: 禁用, 1: 启用',
  `is_pending_approval` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否待审核; 0: 不是, 1: 是;',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '友链表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_friend_link
-- ----------------------------
INSERT INTO `blog_friend_link` VALUES (1, 'TechHub', 'https://techhub.com', '一个技术交流的社区', 'https://techhub.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (2, 'CodeGeeks', 'https://codegeeks.com', '分享编程知识和经验', 'https://codegeeks.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (3, 'WebDevConnect', 'https://webdevconnect.com', '连接Web开发者的平台', 'https://webdevconnect.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (4, 'DataScienceHub', 'https://datasciencehub.com', '数据科学领域的交流社区', 'https://datasciencehub.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (5, 'AIExplorers', 'https://aiexplorers.com', '探索人工智能的未知领域', 'https://aiexplorers.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (6, 'OpenSourceWorld', 'https://opensourceworld.com', '开源世界的精彩', 'https://opensourceworld.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (7, 'CodeMasters', 'https://codemasters.com', '编程大师的聚集地', 'https://codemasters.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (8, 'CloudTechies', 'https://cloudtechies.com', '云计算技术的分享平台', 'https://cloudtechies.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (9, 'SecurityHub', 'https://securityhub.com', '网络安全专业社区', 'https://securityhub.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (10, 'IoTInnovators', 'https://iotinnovators.com', '物联网创新者的聚集地', 'https://iotinnovators.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (11, 'VRWorld', 'https://vrworld.com', '虚拟现实技术的全球网络', 'https://vrworld.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (12, 'TechDebates', 'https://techdebates.com', '科技领域的激烈辩论', 'https://techdebates.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (13, 'StartupInsights', 'https://startupinsights.com', '创业者的见解和经验分享', 'https://startupinsights.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (14, 'CodeCrafters', 'https://codecrafters.com', '编程工匠的交流平台', 'https://codecrafters.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (15, 'TechNewsDigest', 'https://technewsdigest.com', '科技新闻的精华摘要', 'https://technewsdigest.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (16, 'DevOpsConnect', 'https://devopsconnect.com', 'DevOps实践者的互动社区', 'https://devopsconnect.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (17, 'CodeArtisans', 'https://codeartisans.com', '代码艺术家的创作园地', 'https://codeartisans.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (18, 'TechInnovate', 'https://techinnovate.com', '科技创新的风向标', 'https://techinnovate.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (19, 'CodeNinjas', 'https://codeninjas.com', '编程忍者的秘密基地', 'https://codeninjas.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');
INSERT INTO `blog_friend_link` VALUES (20, 'GeekInsights', 'https://geekinsights.com', '极客见解和科技观察', 'https://geekinsights.com/logo.png', 1, 0, NULL, 0, 0, NULL, '2023-12-03 18:18:32', NULL, '2023-12-03 18:27:48');

SET FOREIGN_KEY_CHECKS = 1;
