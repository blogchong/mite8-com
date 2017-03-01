/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714
Source Host           : 120.24.45.89:9906
Source Database       : mite_service

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:58:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `jx_gz_dn_praise_score`
-- ----------------------------
DROP TABLE IF EXISTS `jx_gz_dn_praise_score`;
CREATE TABLE `jx_gz_dn_praise_score` (
  `update_time` varchar(255) NOT NULL,
  `p_time` varchar(255) NOT NULL COMMENT '发布时间',
  `from_url` varchar(255) NOT NULL COMMENT '源标题',
  `words_list` varchar(255) NOT NULL COMMENT '通过标题获取的words列表，以","分隔',
  `score_praise` int(255) NOT NULL COMMENT '褒-包含好、乐',
  `score_anger` int(11) NOT NULL COMMENT '愤怒',
  `score_sad` int(11) NOT NULL COMMENT '哀',
  `score_fear` int(11) NOT NULL COMMENT '惧',
  `score_hate` int(11) NOT NULL COMMENT '恶',
  `score_shock` int(11) NOT NULL COMMENT '惊'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jx_gz_dn_praise_score
-- ----------------------------
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-29 11:50:00', 'http://news.haiwainet.cn/n/2016/1129/c3542210-30529668.html', '受贿', '0', '0', '0', '0', '5', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-29 09:58:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161129_910617.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-29 12:10:00', 'http://jx.ifeng.com/a/20161128/5182741_0.shtml', '发展,最高', '6', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-26 17:31:00', 'http://www.rzw.com.cn/xinxi/28676125.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-26 09:16:00', 'http://www.rzw.com.cn/xinxi/28656020.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-25 16:44:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161125_910457.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-25 09:51:00', 'http://www.chinajob.gov.cn/LabourRelations/content/2016-11/25/content_1257268.htm', '拖欠', '0', '0', '0', '0', '5', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-25 09:31:00', 'http://hy.southcn.com/content/2016-11/25/content_160412159.htm', '和平', '1', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-24 11:15:00', 'http://jx.ifeng.com/a/20161124/5174088_0.shtml', '好心,拾金不昧', '12', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-23 10:25:00', 'http://news.jxntv.cn/qiye/roll/20161123/79.html', '忠贞,病夫', '5', '0', '0', '0', '7', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-23 09:17:00', 'http://www.jcrb.com/procuratorate/jckx/201611/t20161123_1684184.html', '精细', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-23 08:55:00', 'http://jx.ifeng.com/a/20161123/5169332_0.shtml', '好人', '0', '0', '0', '0', '1', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-22 12:59:00', 'http://www.rzw.com.cn/xinxi/28424045.html', '知名', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-21 14:10:00', 'http://www.jcrb.com/procuratorate/jckx/201611/t20161121_1682959.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-21 10:18:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161121_909877.htm', '指导', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-19 14:29:00', 'http://www.rzw.com.cn/xinxi/28331330.html', '数一数二', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-18 17:28:00', 'https://jx.people.com.cn/n2/2016/1118/c186330-29332963.html', '学习,贯彻,精神', '6', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-18 16:27:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161118_909800.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-18 16:16:00', 'http://www.jcrb.com/procuratorate/jcpd/201611/t20161121_1682439.html', '开展,观摩,增强', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-18 10:30:00', 'http://mt.sohu.com/20161118/n473514748.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-18 10:28:00', 'http://www.p5w.net/news/cjzh/201611/t20161118_1640917.htm', '扶持', '1', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-18 10:16:00', 'http://www.p5w.net/news/cjzh/201611/t20161118_1640915.htm', '优惠', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-17 17:01:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161117_909673.htm', '安全', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-17 17:01:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161117_909671.htm', '指导', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-17 09:32:00', 'http://www.yndtjj.com/news1_94809.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-16 09:07:00', 'http://jiangxi.jxnews.com.cn/system/2016/11/16/015387323.shtml', '解决', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-15 01:51:00', 'http://www.rzw.com.cn/xinxi/28067902.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-14 11:25:00', 'http://jx.ifeng.com/a/20161114/5145627_0.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-14 09:32:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161114_909220.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-12 12:59:00', 'http://www.rzw.com.cn/xinxi/27953450.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-11 09:16:00', 'http://www.offcn.com/sydw/2016/1110/248667.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-10 21:10:00', 'http://finance.sina.com.cn/roll/2016-11-10/doc-ifxxsmif2707400.shtml', '建设', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-10 15:04:00', 'http://jx.huatu.com/2016/1110/1497465.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-10 08:56:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201611/t20161110_908751.htm', '仪式', '1', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-09 09:01:00', 'http://jx.ifeng.com/a/20161109/5131986_0.shtml', '犯罪', '0', '0', '0', '0', '5', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-07 22:40:00', 'http://hk.eastmoney.com/news/1535,20161107681507614.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-04 17:03:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161104_908350.htm', '批准', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-03 09:57:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201611/t20161103_908136.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-03 09:42:00', 'http://news.gmw.cn/newspaper/2016-11/03/content_117684297.htm', '突破', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-02 09:49:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161102_907990.htm', '开展,发展', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-11-01 15:16:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161101_907897.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-10-31 17:36:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201610/t20161031_907775.htm', '重要', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-10-16 00:07:00', 'http://sz.szhk.com/xinxi/26599507.html', '精密', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-09-14 16:11:00', 'http://www.jx.chinanews.com.cn/news/2016/0914/1296.html', '行凶', '0', '0', '0', '7', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-09-02 09:36:00', 'http://news.cbg.cn/hotnews/2016/0902/4607047.shtml', '发展', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-08-09 09:30:00', 'http://jx.offcn.com/html/2016/08/61886.html', '安全', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-08-03 07:13:00', 'http://jx.people.com.cn/BIG5/n2/2016/0803/c186330-28770757.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-08-02 00:00:00', 'http://mp.weixin.qq.com/s?__biz=MzAxNTI5Mzc3Nw%3D%3D&amp;mid=2651019718&amp;idx=4&amp;sn=72e934e1181061b79ec4b4ff38fcaebd', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-07-30 00:00:00', 'http://mp.weixin.qq.com/s?__biz=MzAxNTI5Mzc3Nw%3D%3D&amp;mid=2651019609&amp;idx=3&amp;sn=42d3a9e1adc0da9c7c930ee1383759d6', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-07-06 15:26:00', 'http://news.yunnan.cn/html/2016-07/06/content_4422487.htm', '精准,幸福', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-05-17 00:44:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201605/t20160517_889204.htm', '开展,灾害', '0', '0', '5', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-05-06 11:59:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201605/t20160506_888318.htm', '促进,安全', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-04-25 10:51:00', 'http://jx.people.com.cn/n2/2016/0425/c338249-28215587.html', '烈士', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-03-19 14:27:00', 'http://www.mof.gov.cn/pub/mof/xinxi/difangbiaoxun/difangzhongbiaogonggao/201603/t20160319_1915820.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-01-25 16:39:00', 'http://news.163.com/16/0125/16/BE6J0VGG00014AED.html', '和平', '1', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2016-01-22 14:40:00', 'http://jx.people.com.cn/n2/2016/0122/c186330-27605420.html', '好评', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-11-10 20:44:00', 'http://jx.people.com.cn/BIG5/n/2015/1110/c186330-27067803.html', '平安,成效', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-09-21 02:00:00', 'http://biz.xinmin.cn/2015/09/21/28618862.html', '达标', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-09-08 17:01:00', 'http://jx.ifeng.com/news/detail_2015_09/08/4325499_0.shtml', '人事', '0', '0', '0', '5', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-08-25 01:49:00', 'http://news.163.com/15/0825/01/B1R1KPGI00014AEF.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-08-20 09:52:00', 'http://news.d1cm.com/2015082072237.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-08-12 01:54:00', 'http://news.xinhuanet.com/local/2015-08/12/c_128117944.htm', '幸福', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-07-31 10:56:00', 'http://jjjc.jxcn.cn/system/2015/07/31/014099542.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-27 19:05:00', 'http://jx.people.com.cn/n/2015/0627/c348399-25383332.html', '开展,纪念', '0', '0', '3', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-26 16:16:00', 'http://jx.people.com.cn/n/2015/0626/c337348-25376174.html', '紧急,赢得,赞美', '12', '0', '0', '3', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-26 15:45:00', 'http://mt.sohu.com/20150626/n415714841.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-25 08:43:00', 'http://tv.sohu.com/20150625/n415598700.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-24 21:14:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201506/t20150624_847590.htm', '健全', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-24 16:47:00', 'http://jx.people.com.cn/n/2015/0624/c186330-25351040.html', '创新,丰富,化育', '15', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-24 08:45:00', 'http://culture.gmw.cn/newspaper/2015-06/24/content_107474076.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 15:53:00', 'http://jx.people.com.cn/n/2015/0623/c186330-25336820.html', '创新,开展', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 15:48:00', 'http://jx.people.com.cn/n/2015/0623/c186330-25336733.html', '安全,保障,安全', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 13:51:00', 'http://www.baobei360.com/Articles/Html/2015-06-23/114622.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 13:31:00', 'http://www.js.xinhuanet.com/2015-06/23/c_1115694833.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 10:35:00', 'http://jx.people.com.cn/n/2015/0623/c338249-25332429.html', '精准,发展', '6', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 10:19:00', 'http://news.youth.cn/sh/201506/t20150623_6779168.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 09:49:00', 'http://epaper.hf365.com/jhcb/html/2015-06/23/content_111773.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 09:15:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150623_847297.htm', '安全', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 09:15:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150623_847299.htm', '创新,安居乐业', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 09:15:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150623_847296.htm', '精准', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-23 08:54:00', 'http://gz.jxcn.cn/system/2015/06/23/013979598.shtml', '岳母', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-22 19:47:00', 'http://news.china.com.cn/live/2015-06/22/content_33218393.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-22 17:33:00', 'http://news.cntv.cn/2015/06/22/VIDE1434965522224531.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-17 15:08:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150617_846720.htm', '演出', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-17 13:44:00', 'http://mt.sohu.com/20150617/n415193453.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-17 08:58:00', 'http://www.jiangxi.gov.cn/xzx/jxyw/sxyw/201506/t20150617_1170373.html', '发展', '3', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-16 16:58:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150616_846632.htm', '教育,开展,立功,教育', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-16 16:58:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150616_846629.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-12 22:17:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201506/t20150612_846361.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-12 14:51:00', 'http://news.youth.cn/jy/201506/t20150612_6746044.htm', '事故,追究', '0', '0', '0', '0', '10', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-12 08:15:00', 'http://www.jiangxi.gov.cn/xzx/jxyw/sxyw/201506/t20150612_1169347.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-11 15:39:00', 'http://jx.people.com.cn/n/2015/0611/c337348-25205628.html', '建设,促进,发展', '13', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-11 15:35:00', 'http://jx.people.com.cn/n/2015/0611/c348474-25205594.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-11 11:34:00', 'http://gz.jxcn.cn/system/2015/06/11/013949756.shtml', '推进,教育,发展', '11', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-11 11:16:00', 'http://gz.jxcn.cn/system/2015/06/11/013949301.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-10 23:13:00', 'http://www.jx.xinhuanet.com/news/dianwang/2015-06/10/c_1115577538.htm', '爱心,困难,希望', '10', '0', '0', '1', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-10 23:13:00', 'http://www.jx.xinhuanet.com/news/dianwang/2015-06/10/c_1115577536.htm', '贫困,关爱', '5', '0', '0', '0', '5', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-10 14:19:00', 'http://jx.people.com.cn/n/2015/0610/c348399-25190990.html', '保障', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-10 10:59:00', 'http://gz.jxcn.cn/system/2015/06/10/013945302.shtml', '推进,建设', '12', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 20:07:00', 'http://news.china.com.cn/2015-06/09/content_35779757.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 19:44:00', 'http://www.ntjoy.com/news/vod/xwsph/nttv2/jwpt/2015/06/2015-06-09416168.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 17:57:00', 'http://news.sjtu.edu.cn/info/1010/619572.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 17:05:00', 'http://news.xinhuanet.com/edu/2015-06/09/c_127896695.htm', '追究', '0', '0', '0', '0', '5', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 11:02:00', 'http://gz.jxcn.cn/system/2015/06/09/013941245.shtml', '创新', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 10:26:00', 'http://sd.youth.cn/2015/0609/1278421.shtml', '负责', '5', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 10:21:00', 'http://www.qlwb.com.cn/2015/0609/398248.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 10:05:00', 'http://gz.jxcn.cn/system/2015/06/09/013940175.shtml', '遭遇,事故', '0', '0', '5', '0', '5', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 10:04:00', 'http://news.hainan.net/photo/shehuiwanxiang/datu/2015/06/09/2405980.shtml', '名学', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 09:21:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150609_845879.htm', '生事,稳步,健康,发展', '9', '0', '0', '0', '3', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 09:15:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150609_845876.htm', '搏击', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 08:54:00', 'http://hebei.news.163.com/15/0609/08/ARLHBJPK027907KO.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 08:44:00', 'http://tv.sohu.com/20150609/n414665487.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 08:32:00', 'http://edu.enorth.com.cn/system/2015/06/08/030291017.shtml', '名学', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 08:13:00', 'http://xianyu.haiwainet.cn/n/2015/0609/c352874-28816050.html', '不便,补救', '0', '0', '7', '0', '3', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-09 07:15:00', 'http://news.163.com/15/0609/07/ARLBM9AI00014Q4P.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 23:15:00', 'http://guangyuanol.cn/news/shehui/2015/0608/421363.html', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 21:59:00', 'http://gaozhong.eol.cn/jx/jiangxi/201506/t20150608_1270485.shtml', '名学', '7', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 21:34:00', 'http://jx.people.com.cn/n/2015/0608/c348251-25166963.html', '深入,开展,暴力', '1', '0', '0', '0', '3', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 19:58:00', 'http://news.mydrivers.com/1/433/433769.htm', '欲哭无泪', '0', '0', '7', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 19:35:00', 'http://www.guancha.cn/broken-news/2015_06_08_322594.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 18:53:00', 'http://www.wgcmw.com/news/content_3327.htm', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 17:46:00', 'http://mt.sohu.com/20150608/n414639812.shtml', '', '0', '0', '0', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 17:45:00', 'http://news.cntv.cn/2015/06/08/ARTI1433756674316584.shtml', '补救', '0', '0', '7', '0', '0', '0');
INSERT INTO jx_gz_dn_praise_score VALUES ('2016-11-29 13:10:38', '2015-06-08 17:39:00', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201506/t20150608_845837.htm', '发展', '3', '0', '0', '0', '0', '0');
