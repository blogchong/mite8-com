/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714
Source Host           : 120.24.45.89:9906
Source Database       : mite_service

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 17:02:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `insight_jd_comments_r_month`
-- ----------------------------
DROP TABLE IF EXISTS `insight_jd_comments_r_month`;
CREATE TABLE `insight_jd_comments_r_month` (
  `name` varchar(255) NOT NULL COMMENT 'Key',
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of insight_jd_comments_r_month
-- ----------------------------
INSERT INTO insight_jd_comments_r_month VALUES ('1', '36465');
INSERT INTO insight_jd_comments_r_month VALUES ('2', '12108');
INSERT INTO insight_jd_comments_r_month VALUES ('3', '14312');
INSERT INTO insight_jd_comments_r_month VALUES ('4', '9232');
INSERT INTO insight_jd_comments_r_month VALUES ('5', '7276');
INSERT INTO insight_jd_comments_r_month VALUES ('6', '8790');
INSERT INTO insight_jd_comments_r_month VALUES ('7', '7610');
INSERT INTO insight_jd_comments_r_month VALUES ('8', '12294');
INSERT INTO insight_jd_comments_r_month VALUES ('9', '23218');
INSERT INTO insight_jd_comments_r_month VALUES ('10', '64000');
INSERT INTO insight_jd_comments_r_month VALUES ('11', '70948');
INSERT INTO insight_jd_comments_r_month VALUES ('12', '73337');
