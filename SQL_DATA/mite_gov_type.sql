/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:59:09
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `mite_gov_type`
-- ----------------------------
DROP TABLE IF EXISTS `mite_gov_type`;
CREATE TABLE `mite_gov_type` (
  `a_id` int(11) NOT NULL COMMENT '类型id，111=江西·赣州·定南 ',
  `t_title` varchar(255) NOT NULL DEFAULT '' COMMENT '类型名称',
  `t_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '类型描述',
  `t_url` varchar(255) NOT NULL DEFAULT '' COMMENT '类型对应的链接',
  `t_images` varchar(255) NOT NULL DEFAULT '' COMMENT '对应的缩略图链接，155*90 png',
  `t_rank` int(11) NOT NULL COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mite_gov_type
-- ----------------------------
INSERT INTO mite_gov_type VALUES ('111', '财政统计分析', '对所有政务开放的财政数据进行结构化，并且进行各种维度的横向纵向的可视化对比分析，帮助政府财政规划人员以及相关决策层进行快速了解财政状态，以及进行财政预算规划。', '/gov/jx_gz_dn/finance', '/myres/type/jx_gz_dn_finance.png', '1');
INSERT INTO mite_gov_type VALUES ('111', '问政舆情', '对问政涉及部门、问政情感进行分析、问政类别的分析，对问政人的一些分析等等，从百姓问政的角度，以问政结果数据化的方式，改进政府执行政务的效率以及准确率。', '/gov/jx_gz_dn/politics', '/myres/type/jx_gz_dn_politics.png', '2');
INSERT INTO mite_gov_type VALUES ('111', '人才&招聘舆情', '一个城市需要发展，那么，必然离不开对人才与市场需求的洞察，进一步分析城市内部人才与招聘市场需求的匹配程度，对城市人才结构、供需关系进行对比分析。', '/gov/jx_gz_dn/hire', '/myres/type/jx_gz_dn_hire.png', '5');
INSERT INTO mite_gov_type VALUES ('111', '外界口碑舆情', '监控外界口碑舆情的目的在于，观测外界对本城市的口碑情况，是更多良性的信息被外界所报道还是更多事故，贬责性的新闻被外界所传播。', '/gov/jx_gz_dn/praise', '/myres/type/jx_gz_dn_praise.png', '4');
INSERT INTO mite_gov_type VALUES ('111', '房产舆情', '衣食住行，住这一维度永远是百姓关注的重点，所以，对房产相关的信息进行足够的分析挖掘以及舆情监控，将对政务执行会有巨大的帮助。', '/gov/jx_gz_dn/house', '/myres/type/jx_gz_dn_house.png', '6');
INSERT INTO mite_gov_type VALUES ('111', '教育舆情', '建立每一年的学生数量、高考批次成绩等的横向纵向对比，这将大大有助于教育部门对于教育情况的了解以及对比分析，以及建立起对教育人才的流动舆情监控，对于后期城市发展人才回收有着重要作用。', '/gov/jx_gz_dn/edu', '/myres/type/jx_gz_dn_edu.png', '3');
INSERT INTO mite_gov_type VALUES ('10001', '大数据职位需求报告', '数据来自于各大主流招聘网站8/9月份的4600份大数据JD。对大数据职位需求进行洞察，掌握大数据职位需求岗位分布情况、薪酬分布情况、城市需求情况以及福利特征等各个维度数据。', '/insight/big_data', '/myres/type/insight_big_data.png', '2');
INSERT INTO mite_gov_type VALUES ('10001', '雾霾影响数据分析报告', '截止2016年12月20日X时，数据虫巢以搜索“雾霾口罩”为爬取入口，爬取了以防霾口罩为主共63页，1617个相关物品累计804812个评论，从地域分布、月份变化趋势、时段变化趋势，以及北京地区影响变化等方面进行雾霾影响数据分析。', '/insight/jd_comments_wm', '/myres/type/insight_jd_wm.png', '1');
