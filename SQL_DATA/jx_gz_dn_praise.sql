/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714
Source Host           : 120.24.45.89:9906
Source Database       : mite_service

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:58:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `jx_gz_dn_praise`
-- ----------------------------
DROP TABLE IF EXISTS `jx_gz_dn_praise`;
CREATE TABLE `jx_gz_dn_praise` (
  `update_time` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '新闻标题',
  `p_time` varchar(255) NOT NULL COMMENT '发布时间',
  `news_from` varchar(255) NOT NULL COMMENT '新闻来源网站',
  `from_url` varchar(255) NOT NULL COMMENT '原始链接'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jx_gz_dn_praise
-- ----------------------------
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '赣州市公路局定南分局原局长江胜英受贿获刑3年', '2016-11-29 11:50:00', '海外网', 'http://news.haiwainet.cn/n/2016/1129/c3542210-30529668.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县档案史志局用档案化解山林土地纠纷', '2016-11-29 09:58:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161129_910617.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南县有奖征集发展定位目标 最高奖金2000元', '2016-11-29 12:10:00', '凤凰网江西站', 'http://jx.ifeng.com/a/20161128/5182741_0.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南A级防火箱厂房是多少钱一平方?', '2016-11-26 17:31:00', '日照网', 'http://www.rzw.com.cn/xinxi/28676125.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南供应锅炉系统循环水泵CT变频器维修安装维修', '2016-11-26 09:16:00', '日照网', 'http://www.rzw.com.cn/xinxi/28656020.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '赣州市地税局组织召开2017年工作思路调研定南片会', '2016-11-25 16:44:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161125_910457.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南:拖欠农民工工资 几位老板摊上大事', '2016-11-25 09:51:00', '中国就业网', 'http://www.chinajob.gov.cn/LabourRelations/content/2016-11/25/content_1257268.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南与河源和平携手共建粤赣边际区域产业合作示范区', '2016-11-25 09:31:00', '南方网', 'http://hy.southcn.com/content/2016-11/25/content_160412159.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南:老汉丢失万元救命钱 好心保安拾金不昧', '2016-11-24 11:15:00', '凤凰网江西站', 'http://jx.ifeng.com/a/20161124/5174088_0.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南县历市镇一女子诚信20年还清债务 忠贞33年照顾病夫', '2016-11-23 10:25:00', '江西网络广播电视台', 'http://news.jxntv.cn/qiye/roll/20161123/79.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南县检察院采取精细化初查办案成案率达100%', '2016-11-23 09:17:00', '正义网', 'http://www.jcrb.com/procuratorate/jckx/201611/t20161123_1684184.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县\"中国好人\"黄飞玉半辈子做了这两件事', '2016-11-23 08:55:00', '凤凰网江西站', 'http://jx.ifeng.com/a/20161123/5169332_0.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南知名的住人集装箱品牌代理商有几家', '2016-11-22 12:59:00', '日照网', 'http://www.rzw.com.cn/xinxi/28424045.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南县检察院召开\"关心下一代\"工作现场会', '2016-11-21 14:10:00', '正义网', 'http://www.jcrb.com/procuratorate/jckx/201611/t20161121_1682959.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县档案史志局派业务人员现场指导公安局档案整理工作', '2016-11-21 10:18:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161121_909877.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南监控安装,定南监控批发,数一数二的安防监控公司', '2016-11-19 14:29:00', '日照网', 'http://www.rzw.com.cn/xinxi/28331330.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县检察院学习贯彻十八届六中全会精神', '2016-11-18 17:28:00', '人民网江西频道', 'https://jx.people.com.cn/n2/2016/1118/c186330-29332963.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县召开第三轮矿产资源总体规划大纲论证会', '2016-11-18 16:27:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161118_909800.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西省定南县检察院开展庭审观摩活动增强庭审\"内功\"', '2016-11-18 16:16:00', '正义网', 'http://www.jcrb.com/procuratorate/jcpd/201611/t20161121_1682439.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县政府相关部门负责人莅临企服城参观考察', '2016-11-18 10:30:00', '搜狐', 'http://mt.sohu.com/20161118/n473514748.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县鼓励扶持企业上市挂牌暂行办法', '2016-11-18 10:28:00', '全景网', 'http://www.p5w.net/news/cjzh/201611/t20161118_1640917.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县招商引资若干优惠政策措施摘要', '2016-11-18 10:16:00', '全景网', 'http://www.p5w.net/news/cjzh/201611/t20161118_1640915.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县打好校园食品安全监管\"组合拳\"', '2016-11-17 17:01:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161117_909673.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县副县长赖晓强到县市场和质量监督管理局调研指导工作', '2016-11-17 17:01:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161117_909671.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '\"定南大讲堂\"环境保护专题讲座开讲', '2016-11-17 09:32:00', '云南低碳经济网', 'http://www.yndtjj.com/news1_94809.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县法院探索建立覆盖全县多元化纠纷解决机制', '2016-11-16 09:07:00', '中国江西网', 'http://jiangxi.jxnews.com.cn/system/2016/11/16/015387323.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南规模大的热水器制造厂家', '2016-11-15 01:51:00', '日照网', 'http://www.rzw.com.cn/xinxi/28067902.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县依法拆除金桥广场附近一处违章建筑 总共15间店面', '2016-11-14 11:25:00', '凤凰网江西站', 'http://jx.ifeng.com/a/20161114/5145627_0.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县举办党史专题报告会', '2016-11-14 09:32:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161114_909220.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南实用的美的净水器品牌', '2016-11-12 12:59:00', '日照网', 'http://www.rzw.com.cn/xinxi/27953450.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '2016江西赣州市招聘县以下事业单位人员体检公告(定南县考点)', '2016-11-11 09:16:00', '中公教育', 'http://www.offcn.com/sydw/2016/1110/248667.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南:电能替代助力绿色生态建设', '2016-11-10 21:10:00', '新浪', 'http://finance.sina.com.cn/roll/2016-11-10/doc-ifxxsmif2707400.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '赣州定南2016年招聘县以下事业单位工作人员体检公告', '2016-11-10 15:04:00', '华图教育', 'http://jx.huatu.com/2016/1110/1497465.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南云计算中心项目启动仪式举行', '2016-11-10 08:56:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201611/t20161110_908751.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南警方严打拒不支付劳动报酬犯罪', '2016-11-09 09:01:00', '凤凰网江西站', 'http://jx.ifeng.com/a/20161109/5131986_0.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '中国卫生控股300万元获授定南县中医院经营权 继续停牌', '2016-11-07 22:40:00', '东方网', 'http://hk.eastmoney.com/news/1535,20161107681507614.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县档案史志局精简一项行政审批项目获县政府批准', '2016-11-04 17:03:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161104_908350.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '县职业中专被命名为\"定南瑞狮\"传承基地', '2016-11-03 09:57:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201611/t20161103_908136.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县\"新三板\"挂牌企业实现零突破', '2016-11-03 09:42:00', '光明网', 'http://news.gmw.cn/newspaper/2016-11/03/content_117684297.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县档案史志局与县苏区办联合开展赣南苏区振兴发展档案行政执法检查自查工...', '2016-11-02 09:49:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161102_907990.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县档案史志局接收县政协《定南县文史资料》第五辑进馆', '2016-11-01 15:16:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201611/t20161101_907897.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南:全力打造赣州南部工业经济重要增长极', '2016-10-31 17:36:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201610/t20161031_907775.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南好用的精密过滤器厂家直供', '2016-10-16 00:07:00', '深港在线', 'http://sz.szhk.com/xinxi/26599507.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南\"摩的\"司机被砍伤致死 行凶者疑为14岁少年', '2016-09-14 16:11:00', '中新网江西站', 'http://www.jx.chinanews.com.cn/news/2016/0914/1296.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南打造全域旅游促经济发展', '2016-09-02 09:36:00', '重庆网络广播电视台', 'http://news.cbg.cn/hotnews/2016/0902/4607047.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '2016江西定南县农业和粮食局招聘1名农产品质量安全检测辅助人员公告', '2016-08-09 09:30:00', '中公教育', 'http://jx.offcn.com/html/2016/08/61886.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '让农村既绿又富--定南县生态扶贫掠影', '2016-08-03 07:13:00', '人民网江西频道', 'http://jx.people.com.cn/BIG5/n2/2016/0803/c186330-28770757.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '17岁少女恋上40岁定南大叔,结局警示每一位父母', '2016-08-02 00:00:00', '微信', 'http://mp.weixin.qq.com/s?__biz=MzAxNTI5Mzc3Nw%3D%3D&amp;mid=2651019718&amp;idx=4&amp;sn=72e934e1181061b79ec4b4ff38fcaebd');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南乡村惊现\"拦路虎\"?!\"虎\"主人被警方拘留10天!', '2016-07-30 00:00:00', '微信', 'http://mp.weixin.qq.com/s?__biz=MzAxNTI5Mzc3Nw%3D%3D&amp;mid=2651019609&amp;idx=3&amp;sn=42d3a9e1adc0da9c7c930ee1383759d6');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '【网络媒体走转改】定南精准扶贫:油茶扶出三代幸福', '2016-07-06 15:26:00', '云南网', 'http://news.yunnan.cn/html/2016-07/06/content_4422487.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县开展校园地质灾害防灾避险应急演练', '2016-05-17 00:44:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201605/t20160517_889204.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县矿管局多措并举 促进废弃稀土矿山治理工程施工安全', '2016-05-06 11:59:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201605/t20160506_888318.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南耄耋老人66载守护烈士墓 上央视为烈士寻亲', '2016-04-25 10:51:00', '人民网江西频道', 'http://jx.people.com.cn/n2/2016/0425/c338249-28215587.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县环境保护局定南县生活垃圾填埋场渗滤液处理站设备购置及安装中标公告', '2016-03-19 14:27:00', '中华人民共和国交通运输部', 'http://www.mof.gov.cn/pub/mof/xinxi/difangbiaoxun/difangzhongbiaogonggao/201603/t20160319_1915820.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '河源市和平县定南水治理工程施工招标公告', '2016-01-25 16:39:00', '网易', 'http://news.163.com/16/0125/16/BE6J0VGG00014AED.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县供电公司:兑现服务承诺获好评', '2016-01-22 14:40:00', '人民网江西频道', 'http://jx.people.com.cn/n2/2016/0122/c186330-27605420.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县检察院:检民共建\"平安校园\"成效好', '2015-11-10 20:44:00', '人民网江西频道', 'http://jx.people.com.cn/BIG5/n/2015/1110/c186330-27067803.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南三年拒环保不达标项目160个', '2015-09-21 02:00:00', '新民网', 'http://biz.xinmin.cn/2015/09/21/28618862.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '赣州定南县公布一批人事任免 谢慈生任工信局局长', '2015-09-08 17:01:00', '凤凰网江西站', 'http://jx.ifeng.com/news/detail_2015_09/08/4325499_0.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南设贫困户大病医疗补充保险', '2015-08-25 01:49:00', '网易', 'http://news.163.com/15/0825/01/B1R1KPGI00014AEF.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县委副书记、县长吴建平一行到中联重科考察', '2015-08-20 09:52:00', '第一工程机械网', 'http://news.d1cm.com/2015082072237.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '幸福新定南', '2015-08-12 01:54:00', '新华网', 'http://news.xinhuanet.com/local/2015-08/12/c_128117944.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南:常态化督查纠\"四风\"', '2015-07-31 10:56:00', '中国江西网', 'http://jjjc.jxcn.cn/system/2015/07/31/014099542.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县检察院:开展\"6.26国际禁毒日\"纪念活动', '2015-06-27 19:05:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0627/c348399-25383332.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南供电:深夜紧急抢修 赢得居民赞美', '2015-06-26 16:16:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0626/c337348-25376174.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '2015定南县中小学教师资格审查及面试公告', '2015-06-26 15:45:00', '搜狐', 'http://mt.sohu.com/20150626/n415714841.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西:定南高考错发试卷 相关责任人被问责', '2015-06-25 08:43:00', '搜狐', 'http://tv.sohu.com/20150625/n415598700.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县副县长冯健全调度富田废弃矿山治理项目', '2015-06-24 21:14:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201506/t20150624_847590.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县检察院:创新文化载体丰富文化育检形式效果好', '2015-06-24 16:47:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0624/c186330-25351040.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南供电公司助力村民脱贫致富', '2015-06-24 08:45:00', '光明网', 'http://culture.gmw.cn/newspaper/2015-06/24/content_107474076.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县检察院创新开展2015年\"检察开放日\"活动', '2015-06-23 15:53:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0623/c186330-25336820.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南供电:加强安全意识,保障生产安全', '2015-06-23 15:48:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0623/c186330-25336733.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '羊乐乐在定南 贝贝计划9周年店庆 水果沙龙+宝宝生日会让您和宝宝玩high到爆!', '2015-06-23 13:51:00', '中国婴童网', 'http://www.baobei360.com/Articles/Html/2015-06-23/114622.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考发错试卷:教体局局长等17人被问责', '2015-06-23 13:31:00', '新华网', 'http://www.js.xinhuanet.com/2015-06/23/c_1115694833.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南精准招商为县域经济发展添动力', '2015-06-23 10:35:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0623/c338249-25332429.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考错发试卷12责任人被处分', '2015-06-23 10:19:00', '中国青年网', 'http://news.youth.cn/sh/201506/t20150623_6779168.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考发错试卷', '2015-06-23 09:49:00', '合肥晚报', 'http://epaper.hf365.com/jhcb/html/2015-06/23/content_111773.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '市产业扶贫和农产品质量安全专项督查组到定南督查', '2015-06-23 09:15:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150623_847297.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南:创新社会治安综合治理 让百姓安居乐业', '2015-06-23 09:15:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150623_847299.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南召开2015年精准扶贫工作调度会议', '2015-06-23 09:15:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150623_847296.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南龙塘镇龙塘村刘木星照顾岳母四十载', '2015-06-23 08:54:00', '中国赣州网', 'http://gz.jxcn.cn/system/2015/06/23/013979598.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考发错试卷事:定南教体局局长等17人被问责', '2015-06-22 19:47:00', '中国网', 'http://news.china.com.cn/live/2015-06/22/content_33218393.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '[新闻直播间]江西定南:高考发错试卷 多名相关责任人被撤职或警告处分', '2015-06-22 17:33:00', '央视网', 'http://news.cntv.cn/2015/06/22/VIDE1434965522224531.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '南京军区\"文化服务老区行\"到定南慰问演出', '2015-06-17 15:08:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150617_846720.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '2015年定南二中录取分数线', '2015-06-17 13:44:00', '搜狐网', 'http://mt.sohu.com/20150617/n415193453.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南强化招商引资促工业经济发展', '2015-06-17 08:58:00', '江西省人民政府', 'http://www.jiangxi.gov.cn/xzx/jxyw/sxyw/201506/t20150617_1170373.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南教育系统开展\"立德、立言、立功\"主题教育活动', '2015-06-16 16:58:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150616_846632.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '市果业局到定南督查柑橘黄龙病防控工作', '2015-06-16 16:58:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150616_846629.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '省厅地环处调研员刘燕在定南调研废弃稀土矿山治理和地灾治理项目', '2015-06-12 22:17:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201506/t20150612_846361.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南\"高考发错卷\"事件:已启动事故原因调查和责任追究', '2015-06-12 14:51:00', '中国青年网', 'http://news.youth.cn/jy/201506/t20150612_6746044.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南以生态经济促绿色崛起', '2015-06-12 08:15:00', '江西省人民政府', 'http://www.jiangxi.gov.cn/xzx/jxyw/sxyw/201506/t20150612_1169347.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南供电:狠抓班组建设工作,促进公司发展', '2015-06-11 15:39:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0611/c337348-25205628.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '农发行定南县支行三项举措筑牢雨中\"防火墙\"', '2015-06-11 15:35:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0611/c348474-25205594.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南推进教育事业发展纪实', '2015-06-11 11:34:00', '中国赣州网', 'http://gz.jxcn.cn/system/2015/06/11/013949756.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '信丰县山村发现定南走失耕牛', '2015-06-11 11:16:00', '中国赣州网', 'http://gz.jxcn.cn/system/2015/06/11/013949301.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南供电:爱心捐款 为困难员工点燃生活希望', '2015-06-10 23:13:00', '新华网江西频道', 'http://www.jx.xinhuanet.com/news/dianwang/2015-06/10/c_1115577538.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南供电:志愿者慰问贫困儿童活动传递关爱', '2015-06-10 23:13:00', '新华网江西频道', 'http://www.jx.xinhuanet.com/news/dianwang/2015-06/10/c_1115577536.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县检察院五措并举保障律师会见权', '2015-06-10 14:19:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0610/c348399-25190990.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县推进志愿服务建设纪实', '2015-06-10 10:59:00', '中国赣州网', 'http://gz.jxcn.cn/system/2015/06/10/013945302.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南首次回应发错试卷 将调查原因并追责', '2015-06-09 20:07:00', '中国网', 'http://news.china.com.cn/2015-06/09/content_35779757.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考发错语文卷 考试延时一个多小时', '2015-06-09 19:44:00', '江海明珠网', 'http://www.ntjoy.com/news/vod/xwsph/nttv2/jwpt/2015/06/2015-06-09416168.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西省定南县县领导一行到访交大化学化工学院[图]', '2015-06-09 17:57:00', '上海交通大学', 'http://news.sjtu.edu.cn/info/1010/619572.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南首次回应\"发错试卷\":启动责任追究机制', '2015-06-09 17:05:00', '新华网', 'http://news.xinhuanet.com/edu/2015-06/09/c_127896695.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县创新社会治安综合治理工作纪实', '2015-06-09 11:02:00', '中国赣州网', 'http://gz.jxcn.cn/system/2015/06/09/013941245.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考发错试卷120名高考生受影响 谁来负责?!', '2015-06-09 10:26:00', '中国青年网', 'http://sd.youth.cn/2015/0609/1278421.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南中学考点高考语文发错试卷 官方成立联合调查组', '2015-06-09 10:21:00', '齐鲁晚报网', 'http://www.qlwb.com.cn/2015/0609/398248.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南一八旬老太相续遭遇两次事故', '2015-06-09 10:05:00', '中国赣州网', 'http://gz.jxcn.cn/system/2015/06/09/013940175.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南县高考发错试卷 120名学生受到影响', '2015-06-09 10:04:00', '海南在线', 'http://news.hainan.net/photo/shehuiwanxiang/datu/2015/06/09/2405980.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南卫生事业稳步健康发展', '2015-06-09 09:21:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150609_845879.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南举办首届画眉鸟搏击争霸赛', '2015-06-09 09:15:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/qxdt/201506/t20150609_845876.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南发错试卷 高考关键时刻现\"熊老师\"?', '2015-06-09 08:54:00', '网易河北', 'http://hebei.news.163.com/15/0609/08/ARLHBJPK027907KO.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '考了一半忽然重考 江西定南发错试卷', '2015-06-09 08:44:00', '搜狐', 'http://tv.sohu.com/20150609/n414665487.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考语文发错试卷 120名学生受影响', '2015-06-09 08:32:00', '北方网', 'http://edu.enorth.com.cn/system/2015/06/08/030291017.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南发错试卷:工作人员称不便回应 延长考试时间来补救', '2015-06-09 08:13:00', '海外网', 'http://xianyu.haiwainet.cn/n/2015/0609/c352874-28816050.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南被曝发错高考试卷120名考生受影响', '2015-06-09 07:15:00', '网易', 'http://news.163.com/15/0609/07/ARLBM9AI00014Q4P.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南高考发错试卷网友:有无搞错!?', '2015-06-08 23:15:00', '川北在线', 'http://guangyuanol.cn/news/shehui/2015/0608/421363.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南县高考语文发错卷 120名学生受影响', '2015-06-08 21:59:00', '中国教育在线', 'http://gaozhong.eol.cn/jx/jiangxi/201506/t20150608_1270485.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南县检察院深入学校开展防校园暴力法治宣传', '2015-06-08 21:34:00', '人民网江西频道', 'http://jx.people.com.cn/n/2015/0608/c348251-25166963.html');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南高考发错卷:120名考生欲哭无泪', '2015-06-08 19:58:00', '快科技', 'http://news.mydrivers.com/1/433/433769.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南高考发错试卷 答题90分钟后宣布重考', '2015-06-08 19:35:00', '观察者', 'http://www.guancha.cn/broken-news/2015_06_08_322594.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '高考动态:江西定南4个高考考场发错试卷', '2015-06-08 18:53:00', '中国女性网', 'http://www.wgcmw.com/news/content_3327.htm');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '曝江西定南高考发错试卷 开考90分钟后收回', '2015-06-08 17:46:00', '搜狐', 'http://mt.sohu.com/20150608/n414639812.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '江西定南发错试卷120名高考生受影响 教体局:已补救', '2015-06-08 17:45:00', '央视网', 'http://news.cntv.cn/2015/06/08/ARTI1433756674316584.shtml');
INSERT INTO jx_gz_dn_praise VALUES ('2016-11-29 13:10:38', '定南大力发展绿色生态农业', '2015-06-08 17:39:00', '赣州市人民政府', 'http://www.ganzhou.gov.cn/zwgk/zwdt/bmdt/201506/t20150608_845837.htm');
