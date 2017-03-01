/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714
Source Host           : 120.24.45.89:9906
Source Database       : mite_service

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:56:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `jx_gz_dn_ask_politics_house`
-- ----------------------------
DROP TABLE IF EXISTS `jx_gz_dn_ask_politics_house`;
CREATE TABLE `jx_gz_dn_ask_politics_house` (
  `update_time` varchar(255) NOT NULL COMMENT '更新时间',
  `id` varchar(255) NOT NULL COMMENT '问政编号',
  `house_words` varchar(255) NOT NULL COMMENT 'house特征列表，以","分隔'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jx_gz_dn_ask_politics_house
-- ----------------------------
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201611073455', '万象广场');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201611074690', '万象广场');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201611060606', '利丰山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201611060606', '甲桂林山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201611037460', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201610286898', '学府园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201610244353', '公园华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201610201110', '学府园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201610201802', '九龙商业街');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201610206526', '公园华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201609012293', '利丰山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201608297087', '利丰山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201608263238', '丽景花园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201609063756', '天天佳园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201608313675', '天天佳园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201609064293', '天天佳园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201608313339', '天天佳园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201608313402', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201608300408', '瑞祥家园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201608038234', '桃花源');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201607282601', '丽水华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201607223244', '登峰雅苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201607116860', '瑞祥家园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606300169', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606297695', '瑞祥家园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606277183', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606262049', '凤凰国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606267869', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606156578', '瑞祥家园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606152534', '瑞祥家园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606088320', '瑞安华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606081039', '瑞安华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201606081079', '瑞安华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605190770', '中央华府');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605190770', '御水上都');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605190770', '凤凰国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605190770', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605190770', '东江国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605141615', '瑞安华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605132651', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605133125', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605066022', '瑞安华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605051545', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605052400', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605040724', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605040724', '东江国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX20160504011', '瑞安华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201605033462', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604297808', '凤凰国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604270383', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604251337', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604228621', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604228157', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604162248', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604169008', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604090353', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604081733', '登峰雅苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201604053039', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201603071340', '九龙商业街');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201602263941', '九龙商业街');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201602203892', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201602185910', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201601217057', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201601069127', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201512142643', '凤凰国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201512112525', '九龙商业街');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201512043359', '丽景花园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201512016344', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201511081469', '清华苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201511022491', '清华苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201509194884', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201509177065', '瑞安华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201509121227', '巴黎印象');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201509020467', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508274635', '丽景花园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508252255', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508252255', '公园华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508252255', '清华苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508252255', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508175415', '公园华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508175415', '丽景花园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508107713', '丽景花园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201508050350', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201507172846', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201507115152', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201507118423', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201507103565', '学府园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201507104100', '学府园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201507088748', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201506044907', '利丰山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201506025395', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201506025395', '万象广场');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201506016324', '登峰雅苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201505107388', '利丰山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201504268301', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201504149176', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201504053670', '丽景花园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201503277531', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201503110754', '万象广场');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201503086306', '龙神山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201503082967', '龙神山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201503047533', '东江国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201502251271', '城中首府');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201502251271', '巴黎印象');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201501231615', '丽水华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201501237383', '丽水华庭');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201412089995', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201412038602', '金穗商贸广场');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '中央华府');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '城中首府');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '湖滨花园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '怡和花苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '立建大厦');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '九龙商业街');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '绿海鑫城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '金龙商住城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '明都大厦');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '清华苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '万和一品');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '学府园');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '香格里拉');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '万象广场');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '巴黎印象');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '登峰雅苑');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '利丰山庄');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '凤凰国际');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201411297964', '金穗商贸广场');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201410294150', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201410293805', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201410093321', '台北城');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201410099890', '九龙商业街');
INSERT INTO jx_gz_dn_ask_politics_house VALUES ('2016-11-29 15:51:05', 'XX201410096056', '九龙商业街');
