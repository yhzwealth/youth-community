/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 05/10/2021 19:23:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论数据的ID',
  `dynamic_id` bigint DEFAULT NULL COMMENT '被评论的动态的ID',
  `user_id` bigint DEFAULT NULL COMMENT '评论人ID',
  `pid` bigint DEFAULT NULL COMMENT '父级评论的ID',
  `content` varchar(200) DEFAULT NULL COMMENT '评论内容',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (3, 1, 1, NULL, 'Winter就是YBG的！', '2021-09-15 17:07:42', '2021-09-15 17:07:42', 0);
COMMIT;

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '动态ID',
  `user_id` bigint DEFAULT NULL COMMENT '发送人用户名',
  `content` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发送内容',
  `pic` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配图',
  `top` tinyint DEFAULT '0' COMMENT '是否置顶',
  `type` tinyint DEFAULT NULL COMMENT '何种动态类型',
  `comment` tinyint DEFAULT NULL COMMENT '是否可以被评论',
  `privacy` tinyint DEFAULT NULL COMMENT '可见类型',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of dynamic
-- ----------------------------
BEGIN;
INSERT INTO `dynamic` VALUES (1, 1, 'IUIUIU!!!!', NULL, 0, 1, 1, 1, '2021-09-15 16:49:55', '2021-10-04 22:12:40', 0);
INSERT INTO `dynamic` VALUES (2, 1, 'WinterWinter!!!!', NULL, 1, 2, 1, 2, '2021-10-04 22:12:55', '2021-10-04 22:25:42', 0);
INSERT INTO `dynamic` VALUES (3, 1, 'SanaSana!!!!', NULL, 0, 3, 1, 0, '2021-10-04 22:13:57', '2021-10-04 22:14:06', 0);
INSERT INTO `dynamic` VALUES (4, 1, 'toptop', NULL, 0, 4, 1, 1, '2021-10-04 22:14:26', '2021-10-04 22:25:42', 0);
COMMIT;

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关注列表ID',
  `user_id` bigint DEFAULT NULL COMMENT '被关注人用户名',
  `fan_id` bigint DEFAULT NULL COMMENT '粉丝用户名',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------
BEGIN;
INSERT INTO `follow` VALUES (1, 1, 2, NULL, '2021-10-04 22:18:54', '2021-10-04 22:18:54', 0);
COMMIT;

-- ----------------------------
-- Table structure for love
-- ----------------------------
DROP TABLE IF EXISTS `love`;
CREATE TABLE `love` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `dynamic_id` bigint DEFAULT NULL COMMENT '被点赞的动态ID',
  `user_id` bigint DEFAULT NULL COMMENT '点赞用户的用户名',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of love
-- ----------------------------
BEGIN;
INSERT INTO `love` VALUES (1, 2, 2, '2021-10-04 22:15:04', '2021-10-04 22:20:27', 1);
INSERT INTO `love` VALUES (2, 1, 2, '2021-10-04 22:20:09', '2021-10-04 22:20:09', 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码(MD5加密)',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像URL',
  `background` varchar(200) DEFAULT NULL COMMENT '背景URL',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `birth` date DEFAULT NULL COMMENT '生日',
  `QQ` varchar(15) DEFAULT NULL COMMENT 'QQ号',
  `WeChat` varchar(20) DEFAULT NULL COMMENT '微信号',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `blog` varchar(200) DEFAULT NULL COMMENT '个人主页',
  `introduction` varchar(200) DEFAULT NULL COMMENT '个人简介',
  `privacy` tinyint(1) DEFAULT '1' COMMENT '是否公开个人信息',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'yhzwealth', '123456', '13575681832', 'iamYBG', 'http://122.9.10.73:8080/photos/1631699431719default-avatar.jpg', 'http://122.9.10.73:8080/photos/1631699454052default-background.png', '男', '2001-03-20', '592019923', 'yhzwealth', 'yhzxb@outlook.com', '', '你滴Y皇 无限猖狂', 0, '2021-09-15 16:49:28', '2021-09-15 18:55:12', 0);
INSERT INTO `user` VALUES (2, 'testtest', '123456', NULL, 'test01', 'http://122.9.10.73:8080/photos/1631699431719default-avatar.jpg', 'http://122.9.10.73:8080/photos/1631699454052default-background.png', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2021-09-15 18:19:52', '2021-09-15 18:19:52', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
