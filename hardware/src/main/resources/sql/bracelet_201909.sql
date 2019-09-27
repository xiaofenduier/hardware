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

 Date: 09/27/2019 18:11:48 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `bracelet_201909`
-- ----------------------------
DROP TABLE IF EXISTS `bracelet_201909`;
CREATE TABLE `bracelet_201909` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `bracelet_mac` varchar(20) DEFAULT NULL COMMENT '手环mac',
  `heart_rate` int(3) DEFAULT NULL COMMENT '心率',
  `step` int(3) DEFAULT NULL COMMENT '运动步数',
  `active` varchar(20) DEFAULT NULL COMMENT '活动状态',
  `sleep` varchar(20) DEFAULT NULL COMMENT '睡眠状态',
  `skip_model` int(3) DEFAULT NULL COMMENT '跳绳模式',
  `skip_num` int(3) DEFAULT NULL COMMENT '跳绳数量',
  `skip_time` int(3) DEFAULT NULL COMMENT '跳绳时间',
  `version` varchar(10) DEFAULT NULL,
  `utc` bigint(20) DEFAULT NULL COMMENT '手环utc时间',
  `static_heart_rate` int(3) DEFAULT NULL COMMENT '静止心率',
  `probe_mac` varchar(20) DEFAULT NULL COMMENT '接受的探针mac',
  `uuid` varchar(20) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL COMMENT '接收数据时间',
  `battery` int(2) DEFAULT NULL COMMENT '电量',
  `signal_value` int(3) DEFAULT NULL COMMENT '手环信号值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `insertunique` (`bracelet_mac`,`utc`) USING BTREE,
  KEY `selectByBMac` (`bracelet_mac`) USING BTREE,
  KEY `selectByPMac` (`probe_mac`) USING BTREE,
  KEY `selectByAddTime` (`add_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
