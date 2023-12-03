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

 Date: 03/12/2023 22:29:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`  (
  `id` bigint NOT NULL COMMENT '分类ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类描述',
  `slug` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是分类的别名，可用于生成友好的 URL',
  `post_count` int NULL DEFAULT NULL COMMENT '文章;分类下有几篇文章',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父分类ID，表示层级关系',
  `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用; 0: 禁用, 1: 启用',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES (1, '技术分享', '分享最新的技术动态和编程经验。', 'tech-share', 1, NULL, 1, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:43');
INSERT INTO `blog_category` VALUES (2, '前端开发', '关于前端开发的技巧、框架和实践经验。', 'front-end', NULL, 1, 2, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 18:16:01');
INSERT INTO `blog_category` VALUES (3, '后端开发', '深入探讨后端开发领域的最新趋势和技术。', 'back-end', NULL, 1, 3, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 18:16:01');
INSERT INTO `blog_category` VALUES (4, '移动开发', '移动应用开发的技术分享和最佳实践。', 'mobile-dev', 2, 1, 4, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:43');
INSERT INTO `blog_category` VALUES (5, '人工智能', '探讨人工智能、机器学习和深度学习的前沿技术。', 'ai', NULL, NULL, 5, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 18:16:01');
INSERT INTO `blog_category` VALUES (6, '区块链', '深入了解区块链技术和加密货币的最新动态。', 'blockchain', 2, NULL, 6, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:44');
INSERT INTO `blog_category` VALUES (7, '开源项目', '分享和推荐有趣的开源项目和工具。', 'open-source', NULL, 19, 7, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:25:07');
INSERT INTO `blog_category` VALUES (8, '编程语言', '探讨各种编程语言的特性和最佳实践。', 'programming-languages', NULL, NULL, 8, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 18:16:01');
INSERT INTO `blog_category` VALUES (9, '数据科学', '关于数据分析、挖掘和可视化的实践和经验分享。', 'data-science', NULL, NULL, 9, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 18:16:01');
INSERT INTO `blog_category` VALUES (10, '云计算', '云服务和云计算平台的使用和优化技巧。', 'cloud-computing', 3, 19, 10, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:25:10');
INSERT INTO `blog_category` VALUES (11, '网络安全', '网络安全领域的最新威胁和防御策略。', 'cybersecurity', 4, NULL, 11, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:44');
INSERT INTO `blog_category` VALUES (12, '物联网', '物联网设备和技术的发展和应用案例。', 'iot', NULL, 2, 12, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:25:00');
INSERT INTO `blog_category` VALUES (13, '虚拟现实', '虚拟现实和增强现实技术的应用和创新。', 'vr-ar', NULL, NULL, 13, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 18:16:01');
INSERT INTO `blog_category` VALUES (14, '编程工具', '各种编程工具和开发环境的使用技巧。', 'development-tools', 5, NULL, 14, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:45');
INSERT INTO `blog_category` VALUES (15, '软件工程', '软件工程领域的最佳实践和项目管理经验。', 'software-engineering', 6, 19, 15, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:25:12');
INSERT INTO `blog_category` VALUES (16, '人机交互', '用户体验和人机界面设计的前沿研究和实践。', 'hci', 7, NULL, 16, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:56');
INSERT INTO `blog_category` VALUES (17, '技术趋势', '探讨未来技术趋势和行业发展动向。', 'tech-trends', 5, 5, 17, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:25:03');
INSERT INTO `blog_category` VALUES (18, '编码规范', '编码规范和最佳实践的分享和讨论。', 'coding-standards', 3, NULL, 18, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:51');
INSERT INTO `blog_category` VALUES (19, '科技新闻', '科技界的热点新闻和重要事件分析。', 'tech-news', NULL, NULL, 19, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 18:16:01');
INSERT INTO `blog_category` VALUES (20, '创业经验', '创业者的经验和教训，以及创业生态的最新动态。', 'startup-experience', 2, NULL, 20, 1, NULL, 0, 0, NULL, '2023-12-03 18:16:01', NULL, '2023-12-03 22:08:49');

SET FOREIGN_KEY_CHECKS = 1;
