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

 Date: 03/12/2023 22:29:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_post
-- ----------------------------
DROP TABLE IF EXISTS `blog_post`;
CREATE TABLE `blog_post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `likes` int NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comments` int NOT NULL DEFAULT 0 COMMENT '评论数',
  `cover_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图片URL',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '摘要',
  `view_count` int NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `is_published` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已发布; 0: 未发布, 1: 已发布，2：草稿',
  `category_id` bigint NULL DEFAULT NULL COMMENT '分类ID',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签，逗号分隔',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问密码',
  `type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '文章类型;1原创 2转载 3翻译',
  `source_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原文链接',
  `allow_comments` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许评论; 0: 不允许, 1: 允许',
  `allow_likes` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许点赞; 0: 不允许, 1: 允许',
  `is_featured` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否特色文章; 0: 普通文章, 1: 特色文章',
  `published_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `author_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者姓名',
  `author_bio` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '作者简介',
  `seo_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SEO标题',
  `seo_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'SEO描述',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_post
-- ----------------------------
INSERT INTO `blog_post` VALUES (1, '深入理解RESTful API设计', '在本文中，我们将深入探讨RESTful API的设计原则和最佳实践。我们将介绍资源的定义、HTTP方法的使用以及状态码的选择。通过详细的案例分析，你将更好地理解如何构建可伸缩、可维护的API。', 120, 50, 'https://64.media.tumblr.com/dcd1f6296e0b0b936b10e7bae6f38ed8/tumblr_pyl4lfBphC1ta0hnbo1_1280.jpg', '学习如何设计高效的RESTful API。', 8000, 1, 1, 'API, RESTful, 设计, 开发', NULL, 1, 'https://original-source.com/restful-api-design', 1, 1, 1, '2023-03-10 10:00:00', '技术小白', '在技术领域探索的小白，热衷于分享知识', '深入理解RESTful API设计 - 技术博客', '掌握RESTful API设计的关键原则和技巧。', 1, '2023-12-02 21:57:27', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (2, '使用Docker容器部署微服务应用', '本文将介绍如何使用Docker容器轻松部署和管理微服务应用。我们将演示Docker的基本概念、容器化应用的构建过程以及如何使用Docker Compose进行多容器应用的编排。', 80, 30, 'https://64.media.tumblr.com/d959941734498250abbe2581763ac311/tumblr_pyl4ixzXTQ1ta0hnbo1_1280.jpg', '学习如何利用Docker部署微服务应用。', 6000, 1, 2, 'Docker, 微服务, 部署', NULL, 1, 'https://original-source.com/docker-microservices', 1, 1, 1, '2023-04-05 15:30:00', '容器爱好者', '喜欢使用容器技术构建和部署应用', '使用Docker容器部署微服务应用 - 技术博客', '通过Docker轻松管理和部署微服务应用。', 1, '2023-12-02 21:57:34', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (3, '深入理解RESTful API设计', '在本文中，我们将深入探讨RESTful API的设计原则和最佳实践。我们将介绍资源的定义、HTTP方法的使用以及状态码的选择。通过详细的案例分析，你将更好地理解如何构建可伸缩、可维护的API。', 120, 50, 'https://64.media.tumblr.com/b52b3ccf4c77e34b645be00e8d5a0bed/tumblr_pyl4h7xVue1ta0hnbo1_1280.jpg', '学习如何设计高效的RESTful API。', 8000, 1, 1, 'API, RESTful, 设计, 开发', NULL, 1, 'https://original-source.com/restful-api-design', 1, 1, 1, '2023-03-10 10:00:00', '技术小白', '在技术领域探索的小白，热衷于分享知识', '深入理解RESTful API设计 - 技术博客', '掌握RESTful API设计的关键原则和技巧。', 1, '2023-12-02 21:57:46', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (4, '使用Docker容器部署微服务应用', '本文将介绍如何使用Docker容器轻松部署和管理微服务应用。我们将演示Docker的基本概念、容器化应用的构建过程以及如何使用Docker Compose进行多容器应用的编排。', 80, 30, 'https://64.media.tumblr.com/7820c07c72e3614a53da0356478f0ca0/tumblr_pyl4dmQxMj1ta0hnbo1_1280.jpg', '学习如何利用Docker部署微服务应用。', 6000, 1, 2, 'Docker, 微服务, 部署', NULL, 1, 'https://original-source.com/docker-microservices', 1, 1, 1, '2023-04-05 15:30:00', '容器爱好者', '喜欢使用容器技术构建和部署应用', '使用Docker容器部署微服务应用 - 技术博客', '通过Docker轻松管理和部署微服务应用。', 1, '2023-12-02 21:57:47', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (5, '微服务架构的优势与挑战', '本文将深入探讨微服务架构的优势与挑战。我们将分析微服务的核心概念、优势，以及在实际应用中可能遇到的挑战和解决方案。', 90, 25, 'https://64.media.tumblr.com/ca75b8a01ea2aa78f47ea73f80c739aa/tumblr_pyl486o2tB1ta0hnbo1_1280.jpg', '了解微服务架构的利弊与应对之策。', 7000, 1, 2, '微服务, 架构, 分布式系统', NULL, 1, 'https://original-source.com/microservices-architecture', 1, 1, 0, '2023-07-02 11:15:00', '架构师小王', '有着丰富的微服务架构设计经验', '微服务架构的优势与挑战 - 技术博客', '探讨微服务架构的优势、挑战及应对策略。', 1, '2023-12-02 21:57:52', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (6, 'CI/CD实践指南', '本文将介绍CI/CD（持续集成/持续交付）的实践指南。我们将深入讨论CI/CD的基本概念、工作流程以及如何在项目中实施CI/CD以提高交付效率。', 110, 30, 'https://64.media.tumblr.com/df4872db84ce16e6201bc742323e3836/tumblr_pyl45o48jy1ta0hnbo1_1280.jpg', '学习CI/CD实践的步骤和技巧。', 8500, 1, 1, 'CI/CD, 持续集成, 持续交付', NULL, 1, 'https://original-source.com/cicd-best-practices', 1, 1, 1, '2023-08-12 13:30:00', 'DevOps精英', '致力于实施DevOps实践，提升交付效率', 'CI/CD实践指南 - 技术博客', '深入了解CI/CD的实践指南和最佳实践。', 1, '2023-12-02 21:57:59', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (7, 'GraphQL入门指南', '本文将带您入门GraphQL，介绍GraphQL的基本概念、优势以及如何在应用程序中使用GraphQL进行数据查询。通过本文，您将了解GraphQL的核心原理。', 75, 20, 'https://64.media.tumblr.com/0a3e54cc5b1f0cb76ad6c2f4bd43da25/tumblr_pyl43zWiFF1ta0hnbo1_1280.jpg', '学习如何使用GraphQL进行灵活的数据查询。', 5000, 1, 3, 'GraphQL, 数据查询, API', NULL, 1, 'https://original-source.com/graphql-getting-started', 1, 1, 0, '2023-09-21 09:45:00', 'GraphQL爱好者', '深度研究GraphQL技术，关注前端数据层的优化', 'GraphQL入门指南 - 技术博客', '了解GraphQL的基本概念和应用实践。', 1, '2023-12-02 21:58:08', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (8, '容器编排工具比较：Kubernetes vs. Docker Swarm', '本文将比较两大流行的容器编排工具：Kubernetes和Docker Swarm。我们将分析它们的特点、适用场景以及如何选择合适的容器编排工具。', 130, 35, 'https://64.media.tumblr.com/3a31737d98ac12002140bcdceca2960b/tumblr_pyl4154GJ11ta0hnbo1_1280.jpg', '选择最适合你的容器编排工具。', 9000, 1, 2, 'Kubernetes, Docker Swarm, 容器编排', NULL, 1, 'https://original-source.com/container-orchestration-comparison', 1, 1, 1, '2023-10-08 14:20:00', '容器编排专家', '专注于容器编排技术研究和实践', '容器编排工具比较：Kubernetes vs. Docker Swarm - 技术博客', '深度比较两大容器编排工具的特性和应用场景。', 1, '2023-12-02 21:58:13', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (9, 'Serverless架构的实践与应用', '本文将介绍Serverless架构的实践与应用。我们将深入讨论Serverless的核心概念、优势，以及如何在项目中应用Serverless以提高开发效率。', 95, 28, 'https://64.media.tumblr.com/9089185a69da6f2c13ea4e5a7f917b47/tumblr_pyl1s60Xcb1ta0hnbo1_1280.jpg', '学习如何应用Serverless架构提高开发效率。', 6500, 1, 2, 'Serverless, 无服务器架构, 开发', NULL, 1, 'https://original-source.com/serverless-architecture-practices', 1, 1, 1, '2023-11-25 16:10:00', 'Serverless爱好者', '追求Serverless架构在实际应用中的最佳实践', 'Serverless架构的实践与应用 - 技术博客', '了解Serverless架构的实际应用和最佳实践。', 1, '2023-12-02 21:58:15', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (10, '大数据处理框架比较：Hadoop vs. Spark', '本文将比较两大流行的大数据处理框架：Hadoop和Spark。我们将分析它们的特点、性能差异以及在不同场景下的适用性。', 115, 32, 'https://64.media.tumblr.com/b706b511db9f2ecd4d9d00ffddd13484/tumblr_pyl1oq0w3Y1ta0hnbo1_1280.jpg', '选择最适合你的大数据处理框架。', 8500, 1, 3, 'Hadoop, Spark, 大数据处理', NULL, 1, 'https://original-source.com/big-data-processing-comparison', 1, 1, 1, '2023-12-14 11:45:00', '大数据工程师', '专注于大数据处理技术研究和实践', '大数据处理框架比较：Hadoop vs. Spark - 技术博客', '深度比较两大大数据处理框架的特性和性能。', 1, '2023-12-02 21:58:19', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (11, '持续学习的重要性与方法', '本文将探讨在快速变化的技术领域中，持续学习的重要性以及有效的学习方法。我们将分享一些学习新技术的实用建议，帮助读者保持技术敏锐性。', 80, 22, 'https://64.media.tumblr.com/b0c6948652f8efa3eb6bd9675bd8ea5a/tumblr_pqaqv3Pkfx1ta0hnbo1_1280.jpg', '保持学习状态，适应技术领域的快速变化。', 6000, 1, 1, '学习, 持续学习, 技术敏锐性', NULL, 1, 'https://original-source.com/continuous-learning-importance', 1, 1, 1, '2024-01-03 10:30:00', '永不停歇', '持续追求技术进步，从不停歇', '持续学习的重要性与方法 - 技术博客', '探讨在快速变化的技术领域中持续学习的重要性。', 1, '2023-12-02 21:58:27', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (12, '网络安全最佳实践指南', '本文将介绍网络安全的最佳实践指南，包括防火墙配置、加密通信、漏洞管理等方面。通过本文，您将了解如何保护网络安全，降低潜在的网络威胁。', 120, 28, 'https://64.media.tumblr.com/0af6bbfb3e508b6c50255ca4f8810eb3/tumblr_oy9leglrll1ta0hnbo1_1280.jpg', '学习保护网络安全的实用技巧。', 7500, 1, 2, '网络安全, 安全最佳实践, 防火墙', NULL, 1, 'https://original-source.com/network-security-best-practices', 1, 1, 1, '2024-02-18 15:20:00', '安全专家', '在网络安全领域有多年的从业经验', '网络安全最佳实践指南 - 技术博客', '了解网络安全的最佳实践和实用技巧。', 1, '2023-12-03 00:09:03', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (13, '前端性能优化实践', '本文将分享前端性能优化的实践经验，包括减少加载时间、资源压缩、懒加载等方面。通过这些优化措施，您将提升前端应用的性能和用户体验。', 90, 25, 'https://64.media.tumblr.com/3d57486bdf7441aa2ed436edce119c96/tumblr_pqaqrcycc91ta0hnbo1_1280.jpg', '提升前端应用性能，改善用户体验。', 6000, 1, 3, '前端性能优化, 性能提升, 用户体验', NULL, 1, 'https://original-source.com/frontend-performance-optimization', 1, 1, 1, '2024-03-08 09:40:00', '前端优化探索者', '专注于提升前端应用性能的探索者', '前端性能优化实践 - 技术博客', '分享前端性能优化的实践经验和技巧。', 1, '2023-12-02 21:58:37', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (14, '容器化应用的安全性考虑', '本文将讨论容器化应用的安全性考虑因素，包括容器镜像的安全性、容器运行时的安全性、网络安全等方面。通过本文，您将了解如何确保容器化应用的安全性。', 110, 30, 'https://64.media.tumblr.com/4c5a34bd6b1da7ac7f48766ab3297e2e/tumblr_pqaqoc66A11ta0hnbo1_1280.jpg', '确保容器化应用的安全性。', 8000, 1, 2, '容器化应用, 安全性考虑, 容器安全', NULL, 1, 'https://original-source.com/container-security-best-practices', 1, 1, 1, '2024-04-20 14:50:00', '安全架构师', '在容器安全领域有深厚的技术功底', '容器化应用的安全性考虑 - 技术博客', '探讨确保容器化应用安全性的实际方法和技巧。', 1, '2023-12-02 21:58:42', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (15, '人工智能在软件开发中的应用', '本文将探讨人工智能在软件开发中的应用场景，包括自动化测试、代码生成、缺陷检测等方面。通过本文，您将了解人工智能对软件开发的影响。', 100, 28, 'https://64.media.tumblr.com/10fbf342dadb7120eea947fe8545211e/tumblr_p8vnr7OPpw1ta0hnbo1_1280.jpg', '了解人工智能在软件开发中的前沿应用。', 7000, 1, 1, '人工智能, 软件开发, 自动化', NULL, 1, 'https://original-source.com/ai-in-software-development', 1, 1, 0, '2024-05-05 09:15:00', 'AI开发者', '致力于将人工智能应用于软件开发领域', '人工智能在软件开发中的应用 - 技术博客', '了解人工智能在软件开发中的创新应用场景。', 1, '2023-12-02 21:58:46', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (16, '跨平台移动应用开发比较：Flutter vs. React Native', '本文将比较两大跨平台移动应用开发框架：Flutter和React Native。我们将分析它们的性能、开发体验以及在实际项目中的应用情况。', 130, 35, 'https://64.media.tumblr.com/568a40ba88d7c91e2c24acdaa0c20115/tumblr_oy9mczTFm71ta0hnbo1_1280.jpg', '选择最适合你的跨平台移动应用开发框架。', 9500, 1, 3, 'Flutter, React Native, 移动应用开发', NULL, 1, 'https://original-source.com/cross-platform-mobile-development-comparison', 1, 1, 1, '2024-06-18 12:30:00', '移动开发专家', '在跨平台移动应用开发领域有多年的从业经验', '跨平台移动应用开发比较：Flutter vs. React Native - 技术博客', '深度比较两大跨平台移动应用开发框架的性能和开发体验。', 1, '2023-12-02 21:58:52', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (17, 'DevSecOps实践指南', '本文将介绍DevSecOps（安全开发运维）的实践指南。我们将深入讨论如何在整个开发周期中融入安全性，以确保应用程序的安全性。', 120, 32, 'https://64.media.tumblr.com/628bd2a702028acab5354e214c3381bc/tumblr_oy9maeUNby1ta0hnbo1_1280.jpg', '实践DevSecOps，保障应用程序的安全性。', 8500, 1, 2, 'DevSecOps, 安全开发运维, 安全性', NULL, 1, 'https://original-source.com/devsecops-best-practices', 1, 1, 1, '2024-07-03 14:20:00', '安全开发专家', '在安全开发运维领域有多年的实践经验', 'DevSecOps实践指南 - 技术博客', '深度探讨如何在开发周期中实施DevSecOps以确保安全性。', 1, '2023-12-02 21:58:57', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (18, '云原生应用架构设计', '本文将探讨云原生应用架构的设计原则和最佳实践。我们将分析微服务、容器化、自动化部署等云原生概念，帮助读者构建可伸缩、弹性的应用系统。', 140, 38, 'https://64.media.tumblr.com/e28d679f464f12863bfa1b003598cd1a/tumblr_oy9m6jGUag1ta0hnbo1_1280.jpg', '构建云原生应用，提升应用系统的可伸缩性。', 9000, 1, 1, '云原生, 应用架构, 微服务', NULL, 1, 'https://original-source.com/cloud-native-architecture-design', 1, 1, 1, '2024-08-21 11:45:00', '云架构师', '专注于云原生应用架构设计和实践', '云原生应用架构设计 - 技术博客', '学习云原生应用架构设计的原理和最佳实践。', 1, '2023-12-02 21:59:02', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (19, '数据湖架构与实践', '本文将介绍数据湖架构的设计原理和实践方法。我们将深入讨论数据湖的概念、架构设计以及如何在实际项目中建立和维护数据湖。', 100, 30, 'https://64.media.tumblr.com/ee437563bbfa76ab8f2dfd7dbd0905e9/tumblr_oy9m3prQ771ta0hnbo1_1280.jpg', '学习数据湖架构的设计和实践经验。', 7500, 1, 2, '数据湖, 数据架构, 大数据', NULL, 1, 'https://original-source.com/data-lake-architecture-practices', 1, 1, 1, '2024-09-10 15:10:00', '大数据架构师', '在大数据领域有着丰富的数据架构设计经验', '数据湖架构与实践 - 技术博客', '深度了解数据湖架构的设计和实践方法。', 1, '2023-12-02 21:59:06', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (20, '可视化数据分析工具比较', '本文将比较几种流行的可视化数据分析工具，包括Tableau、Power BI和Looker。我们将分析它们的功能、易用性以及在数据分析领域的应用。', 120, 35, 'https://64.media.tumblr.com/1a696493a8efb773b70f4f910c4ef8f7/tumblr_oy9m1yK2AU1ta0hnbo1_1280.jpg', '选择最适合你的可视化数据分析工具。', 8500, 1, 3, '可视化分析, 数据工具, Tableau', NULL, 1, 'https://original-source.com/data-visualization-tool-comparison', 1, 1, 1, '2024-10-02 14:40:00', '数据分析师', '专注于数据可视化分析工具的比较和应用', '可视化数据分析工具比较 - 技术博客', '深度比较几种可视化数据分析工具的特性和应用场景。', 1, '2023-12-02 21:59:07', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (21, '持续部署最佳实践', '本文将介绍持续部署的最佳实践，包括自动化部署、流水线构建、环境管理等方面。通过本文，您将了解如何在项目中实施持续部署以提高交付效率。', 110, 30, 'https://64.media.tumblr.com/db525f6261b5acac5fa966f27bba2d00/tumblr_oy9lwt7vBd1ta0hnbo1_1280.jpg', '实践持续部署，提高交付效率。', 8000, 1, 1, '持续部署, 自动化部署, 交付效率', NULL, 1, 'https://original-source.com/continuous-deployment-best-practices', 1, 1, 1, '2024-11-15 10:20:00', 'DevOps实践者', '专注于实施DevOps实践，优化交付流程', '持续部署最佳实践 - 技术博客', '深度探讨如何实施持续部署以提高交付效率。', 1, '2023-12-02 21:59:14', 0, 0, '2023-12-02 21:34:44', 1, 1);
INSERT INTO `blog_post` VALUES (22, '容器与虚拟机技术比较', '本文将深入比较容器技术和虚拟机技术。我们将分析两者的优势、劣势，以及在不同场景下的适用性。通过这篇文章，你将更好地了解容器与虚拟机的区别和选择。', 90, 25, 'https://64.media.tumblr.com/1c7fcaeee8341856914c4d2b3f6a42cb/tumblr_oy9luh8JmM1ta0hnbo1_1280.jpg', '了解容器技术与虚拟机技术的特点和应用场景。', 7000, 1, 1, '容器, 虚拟机, 技术比较', NULL, 1, 'https://original-source.com/container-vs-vm', 1, 1, 1, '2023-04-10 14:30:00', '技术探索者', '热衷于探索各种新兴技术', '容器与虚拟机技术比较 - 技术博客', '深入比较容器技术和虚拟机技术的特性和优劣。', 1, '2023-12-02 21:59:18', 0, 0, '2023-12-02 21:43:30', 1, 1);
INSERT INTO `blog_post` VALUES (23, '服务网格架构的实践与应用', '本文将介绍服务网格架构的实践与应用。我们将深入探讨服务网格的基本概念、优势，以及如何在项目中应用服务网格以提高微服务应用的可观察性和可维护性。', 100, 30, 'https://64.media.tumblr.com/8e08d76ff9da9d38400282b75cca798a/tumblr_oy9lrtcE2j1ta0hnbo1_1280.jpg', '学习如何应用服务网格架构提升微服务应用的管理效率。', 8000, 1, 2, '服务网格, 微服务, 架构设计', NULL, 1, 'https://original-source.com/service-mesh-practices', 1, 1, 1, '2023-05-18 11:15:00', '微服务架构师', '专注于微服务架构设计与实践', '服务网格架构的实践与应用 - 技术博客', '深入介绍服务网格架构的实际应用和优势。', 1, '2023-12-02 21:59:20', 0, 0, '2023-12-02 21:43:30', 1, 1);
INSERT INTO `blog_post` VALUES (24, '云原生应用开发最佳实践', '本文将分享云原生应用开发的最佳实践。我们将涵盖容器化、微服务、自动化部署等方面，帮助开发者更好地构建和维护云原生应用。', 110, 35, 'https://64.media.tumblr.com/df9483b3a69d8d43cfd5c70710422280/tumblr_oy9li5BcxR1ta0hnbo1_1280.jpg', '学习构建高效的云原生应用的实用技巧。', 8500, 1, 1, '云原生, 应用开发, 最佳实践', NULL, 1, 'https://original-source.com/cloud-native-app-dev-best-practices', 1, 1, 1, '2023-06-25 13:40:00', '云计算爱好者', '关注云计算技术的忠实爱好者', '云原生应用开发最佳实践 - 技术博客', '分享云原生应用开发的最佳实践和经验。', 1, '2023-12-02 21:59:24', 0, 0, '2023-12-02 21:43:30', 1, 1);
INSERT INTO `blog_post` VALUES (25, '持续交付的关键技术与流程', '本文将深入探讨持续交付的关键技术和流程。我们将介绍CI/CD、自动化测试、蓝绿部署等关键概念，以及如何在项目中实施持续交付以提高交付效率。', 120, 40, 'https://64.media.tumblr.com/6efd58b9ba5a8510d307459798e84122/tumblr_oy9lg8NFET1ta0hnbo1_1280.jpg', '学习构建高效持续交付流程的实用技巧。', 9000, 1, 2, '持续交付, CI/CD, 流程优化', NULL, 1, 'https://original-source.com/key-technologies-processes-continuous-delivery', 1, 1, 1, '2023-08-05 15:20:00', '交付专家', '专注于持续交付流程的优化与实践', '持续交付的关键技术与流程 - 技术博客', '深入介绍持续交付的关键技术和流程优化。', 1, '2023-12-02 21:59:29', 0, 0, '2023-12-02 21:43:30', 1, 1);
INSERT INTO `blog_post` VALUES (26, '微前端架构的实践与案例', '本文将分享微前端架构的实践经验和应用案例。我们将深入讨论微前端的核心概念、优势，以及如何在实际项目中应用微前端以提高前端应用的可维护性和灵活性。', 85, 28, 'https://64.media.tumblr.com/0af6bbfb3e508b6c50255ca4f8810eb3/tumblr_oy9leglrll1ta0hnbo1_1280.jpg', '学习如何构建可维护和灵活的微前端架构。', 6500, 1, 3, '微前端, 架构设计, 前端开发', NULL, 1, 'https://original-source.com/micro-frontends-practices', 1, 1, 1, '2023-09-15 10:45:00', '前端架构师', '专注于前端架构设计与实践', '微前端架构的实践与案例 - 技术博客', '深入介绍微前端架构的实际应用和优势。', 1, '2023-12-02 21:59:33', 0, 0, '2023-12-02 21:43:30', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
