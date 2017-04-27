/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:59:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `mite_gov_addr`
-- ----------------------------
DROP TABLE IF EXISTS `mite_gov_addr`;
CREATE TABLE `mite_gov_addr` (
  `a_id` int(11) NOT NULL COMMENT '地理位置编码表 111-江西·赣州·定南',
  `a_name` varchar(255) NOT NULL COMMENT '名称',
  `a_url` varchar(255) NOT NULL COMMENT '对应的列表链接'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mite_gov_addr
-- ----------------------------
INSERT INTO mite_gov_addr VALUES ('111', '江西·赣州·定南', '/gov/addr_type?a_id=111');
INSERT INTO mite_gov_addr VALUES ('10001', '数据报告列表', '/insight_type?a_id=10001');
