/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 17/01/2019 11:13:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for perms
-- ----------------------------
DROP TABLE IF EXISTS `perms`;
CREATE TABLE `perms`  (
  `pid` int(11) DEFAULT NULL,
  `perm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `rid` int(11) DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perms
-- ----------------------------
INSERT INTO `perms` VALUES (1, 'user:add', 1);
INSERT INTO `perms` VALUES (2, 'user:update', 1);
INSERT INTO `perms` VALUES (3, 'user:select', 2);

SET FOREIGN_KEY_CHECKS = 1;
