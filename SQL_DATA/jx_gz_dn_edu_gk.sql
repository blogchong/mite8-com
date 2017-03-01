/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714
Source Host           : 120.24.45.89:9906
Source Database       : mite_service

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:56:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `jx_gz_dn_edu_gk`
-- ----------------------------
DROP TABLE IF EXISTS `jx_gz_dn_edu_gk`;
CREATE TABLE `jx_gz_dn_edu_gk` (
  `province` varchar(255) NOT NULL COMMENT '对应省份',
  `number` int(11) NOT NULL COMMENT '人数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jx_gz_dn_edu_gk
-- ----------------------------
INSERT INTO jx_gz_dn_edu_gk VALUES ('江西', '231');
INSERT INTO jx_gz_dn_edu_gk VALUES ('北京', '34');
INSERT INTO jx_gz_dn_edu_gk VALUES ('湖南', '56');
INSERT INTO jx_gz_dn_edu_gk VALUES ('广东', '132');
INSERT INTO jx_gz_dn_edu_gk VALUES ('上海', '34');
INSERT INTO jx_gz_dn_edu_gk VALUES ('山东', '23');
INSERT INTO jx_gz_dn_edu_gk VALUES ('陕西', '11');
INSERT INTO jx_gz_dn_edu_gk VALUES ('海南', '3');
INSERT INTO jx_gz_dn_edu_gk VALUES ('云南', '4');
INSERT INTO jx_gz_dn_edu_gk VALUES ('黑龙江', '6');
INSERT INTO jx_gz_dn_edu_gk VALUES ('浙江', '14');
INSERT INTO jx_gz_dn_edu_gk VALUES ('香港', '1');
INSERT INTO jx_gz_dn_edu_gk VALUES ('青海', '2');
INSERT INTO jx_gz_dn_edu_gk VALUES ('福建', '9');
