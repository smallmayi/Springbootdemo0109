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

 Date: 17/01/2019 11:14:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `t_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `rid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (10000, '张三', 'd6ae580c9bbbe9802d8e785a09a9f5b0', 1);
INSERT INTO `teacher` VALUES (10001, '李四', 'd38b38e7be6e85343caec41a4bb643ba', 1);
INSERT INTO `teacher` VALUES (10007, '王五', 'ca62680412f2ca1cc18b4415c91f61f0', 2);

SET FOREIGN_KEY_CHECKS = 1;
