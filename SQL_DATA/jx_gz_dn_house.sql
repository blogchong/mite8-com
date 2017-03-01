/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714
Source Host           : 120.24.45.89:9906
Source Database       : mite_service

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:57:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `jx_gz_dn_house`
-- ----------------------------
DROP TABLE IF EXISTS `jx_gz_dn_house`;
CREATE TABLE `jx_gz_dn_house` (
  `house_name` varchar(255) NOT NULL COMMENT '房产名字',
  `price` int(11) NOT NULL COMMENT '价格'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jx_gz_dn_house
-- ----------------------------
INSERT INTO jx_gz_dn_house VALUES ('凤凰国际', '4000');
INSERT INTO jx_gz_dn_house VALUES ('九龙商业街\r\n', '3398');
INSERT INTO jx_gz_dn_house VALUES ('香格里拉', '4000');
INSERT INTO jx_gz_dn_house VALUES ('御水上都', '3350');
INSERT INTO jx_gz_dn_house VALUES ('万和一品', '4200');
INSERT INTO jx_gz_dn_house VALUES ('万象广场', '3988');
INSERT INTO jx_gz_dn_house VALUES ('宏润国际', '3700');
INSERT INTO jx_gz_dn_house VALUES ('中央华府', '3700');
INSERT INTO jx_gz_dn_house VALUES ('明都大厦', '3800');
INSERT INTO jx_gz_dn_house VALUES ('桃花源', '3300');
INSERT INTO jx_gz_dn_house VALUES ('清华苑', '3900');
INSERT INTO jx_gz_dn_house VALUES ('利丰山庄', '3500');
INSERT INTO jx_gz_dn_house VALUES ('巴黎印象', '3700');
INSERT INTO jx_gz_dn_house VALUES ('金穗商贸广场', '3300');
