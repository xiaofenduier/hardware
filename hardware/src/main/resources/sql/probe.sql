/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost
 Source Database       : hardware

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : utf-8

 Date: 09/27/2019 18:11:55 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `probe`
-- ----------------------------
DROP TABLE IF EXISTS `probe`;
CREATE TABLE `probe` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `probe_mac` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `is_normal` int(1) DEFAULT NULL,
  `regular_throughput` int(10) DEFAULT NULL,
  `on_line` int(1) DEFAULT NULL,
  `version` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
