/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 17:02:48
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `insight_jd_comments_r_area`
-- ----------------------------
DROP TABLE IF EXISTS `insight_jd_comments_r_area`;
CREATE TABLE `insight_jd_comments_r_area` (
  `name` varchar(255) NOT NULL COMMENT 'Key',
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of insight_jd_comments_r_area
-- ----------------------------
INSERT INTO insight_jd_comments_r_area VALUES ('北京', '53680');
INSERT INTO insight_jd_comments_r_area VALUES ('四川', '10452');
INSERT INTO insight_jd_comments_r_area VALUES ('广东', '10355');
INSERT INTO insight_jd_comments_r_area VALUES ('河北', '9644');
INSERT INTO insight_jd_comments_r_area VALUES ('上海', '8463');
INSERT INTO insight_jd_comments_r_area VALUES ('山东', '8248');
INSERT INTO insight_jd_comments_r_area VALUES ('江苏', '6489');
INSERT INTO insight_jd_comments_r_area VALUES ('天津', '6170');
INSERT INTO insight_jd_comments_r_area VALUES ('辽宁', '5826');
INSERT INTO insight_jd_comments_r_area VALUES ('陕西', '5485');
INSERT INTO insight_jd_comments_r_area VALUES ('河南', '4590');
INSERT INTO insight_jd_comments_r_area VALUES ('浙江', '3976');
INSERT INTO insight_jd_comments_r_area VALUES ('湖北', '3456');
INSERT INTO insight_jd_comments_r_area VALUES ('山西', '3036');
INSERT INTO insight_jd_comments_r_area VALUES ('黑龙江', '2661');
INSERT INTO insight_jd_comments_r_area VALUES ('安徽', '2626');
INSERT INTO insight_jd_comments_r_area VALUES ('福建', '2313');
INSERT INTO insight_jd_comments_r_area VALUES ('湖南', '2112');
INSERT INTO insight_jd_comments_r_area VALUES ('吉林', '1636');
INSERT INTO insight_jd_comments_r_area VALUES ('重庆', '1308');
INSERT INTO insight_jd_comments_r_area VALUES ('内蒙古', '1289');
INSERT INTO insight_jd_comments_r_area VALUES ('江西', '1235');
INSERT INTO insight_jd_comments_r_area VALUES ('云南', '940');
INSERT INTO insight_jd_comments_r_area VALUES ('广西', '902');
INSERT INTO insight_jd_comments_r_area VALUES ('甘肃', '832');
INSERT INTO insight_jd_comments_r_area VALUES ('贵州', '794');
INSERT INTO insight_jd_comments_r_area VALUES ('新疆', '788');
INSERT INTO insight_jd_comments_r_area VALUES ('青海', '582');
INSERT INTO insight_jd_comments_r_area VALUES ('海南', '566');
INSERT INTO insight_jd_comments_r_area VALUES ('西藏', '448');
INSERT INTO insight_jd_comments_r_area VALUES ('宁夏', '434');
INSERT INTO insight_jd_comments_r_area VALUES ('台湾', '62');
INSERT INTO insight_jd_comments_r_area VALUES ('香港', '38');
INSERT INTO insight_jd_comments_r_area VALUES ('钓鱼岛', '28');
INSERT INTO insight_jd_comments_r_area VALUES ('澳门', '6');
