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

 Date: 03/12/2023 22:29:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_menu
-- ----------------------------
DROP TABLE IF EXISTS `blog_menu`;
CREATE TABLE `blog_menu`  (
  `id` bigint NOT NULL COMMENT '主键',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单路径',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单icon',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `is_hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏  0否1是',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户号',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `updated_by` bigint NULL DEFAULT NULL COMMENT '最后更新人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `logic_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除;0：正常 1：删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 226 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_menu
-- ----------------------------
INSERT INTO `blog_menu` VALUES (1, NULL, '首页', '/', '/Home.vue', 'el-icon-myshouye', 1, 0, NULL, '2023-12-03 13:02:52', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (2, 1, '首页', '/blog/home', '/Home.vue', 'el-icon-mydashujukeshihuaico-', 3, 0, NULL, '2023-12-03 16:55:48', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (3, 1, '分类', '/blog/category', '/Category.vue', 'el-icon-mydashujukeshihuaico-', 3, 0, NULL, '2023-12-03 14:11:26', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (4, 1, '标签', '/blog/tags', '/tags.vue', 'el-icon-mydashujukeshihuaico-', 3, 0, NULL, '2023-12-03 14:11:28', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (5, 1, '归档', '/blog/archive', '/Archive.vue', 'el-icon-mydashujukeshihuaico-', 3, 0, NULL, '2023-12-03 14:11:32', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (8, 1, '友链管理', '/blog/links', '/FriendLink.vue', 'el-icon-mydashujukeshihuaico-', 3, 0, NULL, '2023-12-03 14:11:34', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (9, 1, '关于我', '/blog/about', '/About.vue', 'el-icon-myguanyuwo', 4, 0, NULL, '2023-12-03 14:11:37', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (100, NULL, '文章管理', '/article-submenu', 'Layout', 'el-icon-mywenzhang-copy', 2, 0, NULL, '2023-12-03 12:56:47', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (101, NULL, '消息管理', '/message-submenu', 'Layout', 'el-icon-myxiaoxi', 3, 0, NULL, '2023-12-03 12:56:50', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (102, NULL, '系统管理', '/system-submenu', 'Layout', 'el-icon-myshezhi', 5, 0, NULL, '2023-12-03 12:56:53', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (103, NULL, '个人中心', '/setting', '/setting/Setting.vue', 'el-icon-myuser', 7, 0, NULL, '2023-12-03 12:56:55', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (104, 2, '发布文章', '/articles', '/article/Article.vue', 'el-icon-myfabiaowenzhang', 2, 0, NULL, '2023-12-03 12:56:57', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (105, 2, '修改文章', '/articles/*', '/article/Article.vue', 'el-icon-myfabiaowenzhang', 1, 1, NULL, '2023-12-03 12:57:00', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (106, 2, '文章列表', '/article-list', '/article/ArticleList.vue', 'el-icon-mywenzhangliebiao', 3, 0, NULL, '2023-12-03 12:57:02', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (107, 2, '分类管理', '/categories', '/category/Category.vue', 'el-icon-myfenlei', 4, 0, NULL, '2023-12-03 12:57:04', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (108, 2, '标签管理', '/tags', '/tag/Tag.vue', 'el-icon-myicontag', 5, 0, NULL, '2023-12-03 12:57:06', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (109, 3, '评论管理', '/comments', '/comment/Comment.vue', 'el-icon-mypinglunzu', 1, 0, NULL, '2023-12-03 12:57:08', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (110, 202, '用户列表', '/users', '/user/User.vue', 'el-icon-myyonghuliebiao', 1, 0, NULL, '2023-12-03 12:57:11', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (111, 213, '角色管理', '/roles', '/role/Role.vue', 'el-icon-myjiaoseliebiao', 2, 0, NULL, '2023-12-03 12:57:14', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (112, 213, '接口管理', '/resources', '/resource/Resource.vue', 'el-icon-myjiekouguanli', 2, 0, NULL, '2023-12-03 12:57:16', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (113, 213, '菜单管理', '/menus', '/menu/Menu.vue', 'el-icon-mycaidan', 2, 0, NULL, '2023-12-03 12:57:17', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (116, NULL, '日志管理', '/log-submenu', 'Layout', 'el-icon-myguanyuwo', 6, 0, NULL, '2023-12-03 12:57:23', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (117, 19, '操作日志', '/operation/log', '/log/OperationLog.vue', 'el-icon-myguanyuwo', 1, 0, NULL, '2023-12-03 12:57:25', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (201, 202, '在线用户', '/online/users', '/user/Online.vue', 'el-icon-myyonghuliebiao', 7, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (202, NULL, '用户管理', '/users-submenu', 'Layout', 'el-icon-myyonghuliebiao', 4, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (205, NULL, '相册管理', '/album-submenu', 'Layout', 'el-icon-myimage-fill', 5, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (206, 205, '相册列表', '/albums', '/album/Album.vue', 'el-icon-myzhaopian', 1, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (208, 205, '照片管理', '/albums/:albumId', '/album/Photo.vue', 'el-icon-myzhaopian', 1, 1, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (209, 4, '定时任务', '/quartz', '/quartz/Quartz.vue', 'el-icon-myyemianpeizhi', 2, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (210, 205, '照片回收站', '/photos/delete', '/album/Delete.vue', 'el-icon-myhuishouzhan', 3, 1, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (213, NULL, '权限管理', '/permission-submenu', 'Layout', 'el-icon-mydaohanglantubiao_quanxianguanli', 4, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (214, 4, '网站管理', '/website', '/website/Website.vue', 'el-icon-myxitong', 1, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (220, 19, '定时任务日志', '/quartz/log/:quartzId', '/log/QuartzLog.vue', 'el-icon-myguanyuwo', 2, 1, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (221, NULL, '说说管理', '/talk-submenu', 'Layout', 'el-icon-mypinglun', 3, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (222, 221, '说说列表', '/talk-list', '/talk/TalkList.vue', 'el-icon-myiconfontdongtaidianji', 1, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (223, 221, '发布说说', '/talks', '/talk/Talk.vue', 'el-icon-myfabusekuai', 2, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (224, 221, '修改说说', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myfabusekuai', 3, 1, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (225, 19, '异常日志', '/exception/log', '/log/ExceptionLog.vue', 'el-icon-myguanyuwo', 1, 0, NULL, '2023-11-25 04:37:59', NULL, '2023-11-25 04:37:59', NULL, 0, 0);
INSERT INTO `blog_menu` VALUES (9000, 224, '测试菜单', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myfabusekuai', 3, 1, NULL, '2023-11-28 22:16:14', NULL, '2023-11-25 04:37:59', NULL, 1, 0);
INSERT INTO `blog_menu` VALUES (9001, 9000, '测试菜单', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myfabusekuai', 3, 1, NULL, '2023-11-28 22:16:14', NULL, '2023-11-25 04:37:59', NULL, 1, 0);
INSERT INTO `blog_menu` VALUES (9002, 9001, '测试菜单', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myfabusekuai', NULL, 1, NULL, '2023-11-28 22:56:00', NULL, '2023-11-25 04:37:59', NULL, 1, 0);
INSERT INTO `blog_menu` VALUES (9003, 9001, '测试菜单', '/talks/:talkId', '/talk/Talk.vue', 'el-icon-myfabusekuai', -1, 1, NULL, '2023-11-28 23:03:44', NULL, '2023-11-25 04:37:59', NULL, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
