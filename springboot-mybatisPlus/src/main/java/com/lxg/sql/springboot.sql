/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 16/08/2022 10:17:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `scores` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1559054201080807426', '[97,108,103]', '{\"姓名\":\"张三\",\"年龄\":\"21\"}', '[{\"小学\":\"深圳小学\"},{\"初中\":\"深圳初中\"},{\"高中\":\"深圳高中\"}]');
INSERT INTO `student` VALUES ('1559054351975088129', '[99,72,88]', '{\"姓名\": \"李四\", \"年龄\": \"23\"}', '[{\"小学\": \"广州小学\"}, {\"初中\": \"广州初中\"}, {\"高中\": \"广州高中\"}]');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '张三', 22);
INSERT INTO `t_user` VALUES (2, 'zhangsan', NULL);
INSERT INTO `t_user` VALUES (3, 'liSi', 12);
INSERT INTO `t_user` VALUES (4, 'liSi', 12);
INSERT INTO `t_user` VALUES (5, 'liSi', 12);
INSERT INTO `t_user` VALUES (6, 'liSi', 12);
INSERT INTO `t_user` VALUES (7, 'liSi', 12);
INSERT INTO `t_user` VALUES (8, 'liSi', 12);
INSERT INTO `t_user` VALUES (14, 'liSi', 12);
INSERT INTO `t_user` VALUES (15, 'liSi', 12);
INSERT INTO `t_user` VALUES (16, 'liSi', 12);
INSERT INTO `t_user` VALUES (17, 'liSi', 12);
INSERT INTO `t_user` VALUES (18, 'liSi', 12);

SET FOREIGN_KEY_CHECKS = 1;
