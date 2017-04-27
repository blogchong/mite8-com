/*
Navicat MySQL Data Transfer

Source Server         : 阿里云MySQL
Source Server Version : 50714

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-01 16:54:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `mite_wechat_topics`
-- ----------------------------
DROP TABLE IF EXISTS `mite_wechat_topics`;
CREATE TABLE `mite_wechat_topics` (
  `p_time` varchar(255) NOT NULL COMMENT '发布时间',
  `t_title` varchar(255) NOT NULL COMMENT '文章标题',
  `t_desc` text NOT NULL COMMENT '描述',
  `t_url` varchar(255) NOT NULL COMMENT '原始链接',
  `t_type` int(11) NOT NULL COMMENT '类型：1=大数据观点 2=大数据挖掘 3=大数据技术 4=经验杂谈'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mite_wechat_topics
-- ----------------------------
INSERT INTO mite_wechat_topics VALUES ('2016-11-28', '聊一聊支付宝的芝麻信用评分模型', '就在昨天，支付宝上线了“圈子”，并且基本上是引领了一波讨论热潮。其中，一个很重要的信息就是，芝麻信用分很重要。', 'http://mp.weixin.qq.com/s/XYYn1iiKp5Mcw3eBSrVhbA', '1');
INSERT INTO mite_wechat_topics VALUES ('2016-11-17', '闲话用户画像&数据价值挖掘', '在流量红利耗尽，在效率至上的年代，如何挖掘有用的数据，用于提升业务的效率，至关重要！而画像提取作为一个基础方向，在未来会得到更多实用价值的证明，这就要求大数据从业者们，在数学建模、特征提取、NLP、文本挖掘等方面有更深一步的建树。', 'http://mp.weixin.qq.com/s/MM7kxamNYEd7pQYQj2PATw', '1');
INSERT INTO mite_wechat_topics VALUES ('2016-11-12', '闲话国内大数据发展简史&产业化落地', '从2009年到2016年，大数据领域从概念到实际的产业落地，经历了很多。逐渐的从一种“潮流”，转变为一种可增值变现的稳健产业。但迄今为止，它依然很难摆脱其他业务而单独存在，这也是我们值得深思的地方。', 'http://mp.weixin.qq.com/s/ajFMGgt53AAH96M8dmdx3Q', '1');
INSERT INTO mite_wechat_topics VALUES ('2016-11-09', '川普赢了，但美国数据同行们却输了', '川普赢了，但我们在美国的数据同行们却被“啪啪”打脸了。说好的“啤酒与尿布”呢，说好的“流感”呢？！', 'http://mp.weixin.qq.com/s/d5hHuoBW6U5qmIBvBUgMrg', '1');
INSERT INTO mite_wechat_topics VALUES ('2016-04-21', '大数据求职者说', '这是一个作为成年人的基本素质，而且经历久了你就会发现，特别是大数据这个圈子，真的很小，说不定你的烂尾事迹就传开了。', 'http://mp.weixin.qq.com/s/p8xsBhK2FYeVQnKug_Ae4g', '4');
INSERT INTO mite_wechat_topics VALUES ('2015-12-21', '你们是不是很缺大数据工程师？', '从所见所闻中简单的说一说大数据这个东西~~(1)我眼中的大数据现状！(2)大数据年份这东西！(3)企业在招什么样的大数据工程师？(4)我们真的需要算法工程师吗？(5)谈一谈薪酬，谈一谈人生！', 'http://mp.weixin.qq.com/s/r_bD_ZBRyjf3xFzj3g30xQ', '1');
INSERT INTO mite_wechat_topics VALUES ('2015-05-25', 'DT时代变革的反思', '在DT时代即将来临的今天，不止是数据处理以及数据获取这两个方面值得我们反思，还有其他的方方面面需要我们去思考。通过不断的反思，不断的改进，我们做好最充分的准备，迎接DT时代的到来！', 'http://mp.weixin.qq.com/s/EJiusRXsdEeyXGJzejBZGg', '1');
INSERT INTO mite_wechat_topics VALUES ('2016-09-20', '大数据职位画像-看看你是不是白混了贼多年！', '一份俺亲自手绘的2016年大数据需求画像，看看你有没有在拖整个世界的后腿！\r\n', 'http://mp.weixin.qq.com/s/eAfDlWc2pOcykWAd84LpVw', '2');
INSERT INTO mite_wechat_topics VALUES ('2015-05-13', '这些年，这些挖掘机算法，这些反思', '其实，就所谓算法而言，个人认为，我有个同事说的很对：所谓算法，并不是说那些复杂的数学模型才是算法，哪怕是你写的一个简单的计算公式，只要能够解决现有业务的痛点，有了自己的模型思路，它就是一个算法，只是它可能不够通用，只能解决特定业务需求而已。', 'http://mp.weixin.qq.com/s/hEB6AagQRuhBciPjy8Sncg', '2');
INSERT INTO mite_wechat_topics VALUES ('2015-12-16', '文本主题特征抽取实践与构想', '在当前个性化推荐大行其道的时候，那就不得不提用户画像。关于用户画像，不久之前跟一个朋友聊过这方面的话题，他有个观点我很认可。', 'http://mp.weixin.qq.com/s/dK3ECxyJS57A6YgMNaWr8Q', '2');
INSERT INTO mite_wechat_topics VALUES ('2015-11-05', '我所理解的大数据个性化推荐', '想起要写这篇文章，一方面是昨天终于把项亮写的《推荐系统实践》给看完了，另一方面是自己负责的推荐系统项目已经处于一个多版本迭代的阶段了，并且从最近的AB测试效果来看，新提交的算法模型还是有一定的进步的，如今已经把流量全部切换到了新算法中。', 'http://mp.weixin.qq.com/s/iDjra3RlebgXY_fGZZc_QA', '2');
INSERT INTO mite_wechat_topics VALUES ('2015-12-22', '热度TopN排名算法的设计', '我们在设计热度排名算法时，不必拘泥于这六大排名算法，多考虑一下实际业务场景。最主要的是对当前业务的热度有一个比较清晰的定位，这样才能根据实际情况进行算法设计以及相关调整，而不是对着算法照搬照抄。', 'http://mp.weixin.qq.com/s/SvSDdre2zo2ex2oz5CtlMQ', '2');
INSERT INTO mite_wechat_topics VALUES ('2016-11-22', '从0到1构建数据生态系列之四：与研发的爱恨情仇', '在数据生态的第一环中，最核心的问题就是基础数据的收集，这是一切的后续数据挖掘、使用的前提。而说到数据收集，通过埋点的数据收集则又是重要的一环，这就必然避免不了与客户端开发人员的交互。', 'http://mp.weixin.qq.com/s/j6v2c4-aM6fZU6CMhoFdXQ', '3');
INSERT INTO mite_wechat_topics VALUES ('2016-11-07', '从0到1构建数据生态系列之三：拆解架构图', '这一章主要阐述如何从局部入手，拆解架构图，进行阶段性的任务执行，从这个角度讲，上一章节的标题反倒是更适合这个了。其中，详细讲解了部分核心重点，包括架构图的拆解、机器资源的评估、Nginx的上报伪服务、以及基于Spark Streaming的数据清洗等。但是，个人认为，更重要的是期中阐述一些问题的思考方式、价值观等。诸如拆解整体规划的思考、扩展预留的思考、方案选择的对比衡量等。', 'http://mp.weixin.qq.com/s/mZ20LtiTkPhGncbagQ1-uA', '3');
INSERT INTO mite_wechat_topics VALUES ('2016-10-27', '从0到1构建数据生态系列之二：拓荒', '接上一篇《从0到1构建数据生态系列之一：蛮荒时代》，这篇我们来一起讲讲如何在数据的蛮荒时代来拓荒。', 'http://mp.weixin.qq.com/s/_juBh9gGjUoA6H36GHdL6A', '3');
INSERT INTO mite_wechat_topics VALUES ('2016-09-22', '从0到1构建数据生态系列之一：蛮荒时代', '无论是帝都还是魔都，还是广州深圳，亦或者是全国其他各地，都在搞大数据；不管是不到百人的微小公司，还是几百人上千人的中型公司，亦或者是上万的大型公司，都在需求数据岗位。', 'http://mp.weixin.qq.com/s/F2eajBbaa9q3Mf919hqYPg', '3');
INSERT INTO mite_wechat_topics VALUES ('2016-01-11', '米特吧大数据技术沙龙第二期干货分享', '第二期在2016年1月9号，于北京海淀区上地金隅嘉华大厦C座1008极客会议室举办。', 'http://mp.weixin.qq.com/s/mADZBqheoiL04B3EUigdJQ', '3');
INSERT INTO mite_wechat_topics VALUES ('2015-12-08', '米特吧大数据技术沙龙第一期干货分享', '12月5号，第一期的《米特吧大数据技术沙龙》在福码大厦B座702随身云会议室举行。', 'http://mp.weixin.qq.com/s/hybBBW3uxjVL7p4tTJ4PJQ', '3');
INSERT INTO mite_wechat_topics VALUES ('2015-12-28', '两座城池', '其实，一开始我本想把题目叫做《围城》，后来想想，估计会被钱钟书粉们给弄死，所以果断弃之~~然后题目叫“两座城池”，仔细想来，其实也是不恰当的。一个大都市，一个小县城，又何论两座城池？', 'http://mp.weixin.qq.com/s/D_V5AmEUwxUzV8UkdL3E2w', '4');
INSERT INTO mite_wechat_topics VALUES ('2016-01-14', '关于IT技术交流与分享的这点事儿', '如果你已经把文章翻到这，不管怎么说，最起码说明，你对于IT技术分享与交流这个事是关注的，或者是有看法的。其实我想表达的东西很简单，技术需要多多交流才能进步，而自身的收获则源自于他人无私的分享。', 'http://mp.weixin.qq.com/s/bOOFepeaiDMwHfCczp3yVw', '4');
INSERT INTO mite_wechat_topics VALUES ('2016-03-03', '我为什么选择离开北京', '在大数据这个技术圈摸爬滚打三年多，认识了不少技术上的朋友，特别是组织了几次线下大数据技术沙龙，认识了不少线下的技术朋友，突然这么一离开，不管怎么样，还是有诸多不舍的！', 'http://mp.weixin.qq.com/s/sJMdthVRm9jDTq3Bu91eig', '4');
INSERT INTO mite_wechat_topics VALUES ('2015-06-12', 'IT动物们，请放松你们的心灵', '本来我想把标题叫做“其实人生不是没有美，只是缺少发现美的心灵”，后来想想感觉太文绉绉了，俺又不是文人，俺只是个程序猿。对的，就是那种IT动物园里最常见的那种动物，每天游走于北京东北四环与东北六环之间，过着朝九晚九的“幸福”生活。', 'http://mp.weixin.qq.com/s/t_E_VLgCs6yvo18Oh2FwPA', '4');
INSERT INTO mite_wechat_topics VALUES ('2015-12-25', '大数据猿们，是时候想想这几个问题了', '其实这篇文章我很久前我就想写出来了，只是一直没有时间，直到昨天，一个人再次触动了我的神经。事情过程是这样滴，昨天技术群里有人贴了张图，然后提问...', 'http://mp.weixin.qq.com/s/W1O6f5hzYoQe5ebEQj5rMA', '1');
INSERT INTO mite_wechat_topics VALUES ('2015-10-27', '拨开大数据的迷雾', ' 对于初入这个领域的人来说，首先需要确定自己的兴趣所在，然后结合文中所说的脉络，找到自己的切入点，想一下子都整明白是不可能的，找准自己的兴趣点，然后逐渐完善自己对大数据的认知体系。', 'http://mp.weixin.qq.com/s/5bof-fzgdVOocGdxDkmXxw', '1');
INSERT INTO mite_wechat_topics VALUES ('2015-06-29', '翻译：Storm与Spark Streaming的对比', 'Storm和Spark streaming都是分布式的数据流式实时处理的开源框架。但是，他们也有一些很重要的差异，如下几点将是重点的差异对比。', 'http://mp.weixin.qq.com/s/bYJc59owbH-Z4D5oe-6v8A', '3');
INSERT INTO mite_wechat_topics VALUES ('2015-06-16', '浅析Apache Storm 0.10.0-beta发布：剑指Heron', '在推特发布Heron后没几天，Apache就发布了Storm的0.10.0，不可谓不及时。也难怪，Heron号称颠覆性的设计，虽然它暂时没有开源，虽然Storm已经占据了数据实时处理领域的半壁江山，但是依然危机感十足啊。', 'http://mp.weixin.qq.com/s/KqEUAp74fXmCitOkZrMH0g', '3');
INSERT INTO mite_wechat_topics VALUES ('2015-11-17', '华为的StreamSQL，你“抄袭”了我的创意', '关于StreamSQL，我是就在数个小时之前才知晓的，源自于《大数据上的流式SQL引擎--StreamSQL》这篇文章。', 'http://mp.weixin.qq.com/s/fRTYFdeb3LWaPiMs8HPf2w', '3');
INSERT INTO mite_wechat_topics VALUES ('2015-11-21', '推荐系统基础知识', '关于推荐系统的基础整理，是对于部门内部交流培训学习“推荐系统基础”的一个整理，比较基础。And，这基本是技术知识~~', 'http://mp.weixin.qq.com/s/RXSyPc2LJ0AaECDvlyb67g', '2');
INSERT INTO mite_wechat_topics VALUES ('2015-04-10', '互联网+ 你们还在玩概念？', '换联网+，其实早就不是什么新鲜玩意儿了，你们还在玩概念？什么是互联网+，百度百科上是这么定义的：“互联网+”是创新2.0下的互联网发展新形态、新业态，是知识社会创新2.0推动下的互联网形态演进。', 'http://mp.weixin.qq.com/s/jKw4BR_dW4YO5tjnZF_7zQ', '4');
INSERT INTO mite_wechat_topics VALUES ('2015-05-05', '你好，scala！', '说起scala，其实，早在几个月前我就认识它了。同事中有一个是scala的“忠实粉丝”，他一直“怂恿”我们使用scala，也一直在部门内极力推广它。但或许是面对不熟悉的事务，我们本能的有种恐惧感，让我不愿意去真正的接触它。', 'http://mp.weixin.qq.com/s/J7ho2HLAF8p8dMyOp92vCA', '4');
INSERT INTO mite_wechat_topics VALUES ('2016-03-28', '你好，大深圳', '辞别大北京的半个月后，我再次踏上了征程，这次的目的地是--深圳。买票的时候，安逸病又犯了，明明不是什么远途，依然手贱的买了一张卧票，只因自个不愿意去挤硬座（好吧，我承认这方面我越来越贱了，做惯了飞机不想做硬卧，坐惯了硬卧就没有买过硬座了）。', 'http://mp.weixin.qq.com/s/WsTr__hgpUudG-tgCS9N-w', '4');
INSERT INTO mite_wechat_topics VALUES ('2015-05-27', '阿里欲瓜分网络文学蛋糕 还来得及吗？', '之所以关注网络文学这个领域，缘自于个人在一年多以前“客串”过几个月的网络写手(好歹也是写过数十万字，领过大纵横稿费的选手啊)，所以对网络文学这一块一直保持着比较高度的注意力。', 'http://mp.weixin.qq.com/s/l28DkDYbMwvnSsWRJ3WSLw', '4');
INSERT INTO mite_wechat_topics VALUES ('2016-12-09', '数据化政务舆情的探索', '如果我们对互联网数据的进一步深挖，结合互联网自产的开放型数据与政府的共享数据，必定能让社会问题剖析的更加精准化、反馈执行的更加有效率等等，以期实现真正的精准化、效率化社会治理。', 'http://mp.weixin.qq.com/s/hwJm6K_7yptukpNCjJFiww', '1');
INSERT INTO mite_wechat_topics VALUES ('2016-12-17', '从0到1构建数据生态系列之五：让你的数据生态更高效', '在数据时代，效率至上，所谓效率，不单纯是整体业务的效率，同样也是对应于内部效率的提升。所以，在不同的阶段下，我们都需要随时反思如何能够进一步的提升效率，结合阶段性的成本投入，进行灵活的健全自己的数据生态效率体系。', 'https://mp.weixin.qq.com/s/XXwkqTJC2msJI2A3OeN5Bg', '3');
INSERT INTO mite_wechat_topics VALUES ('2016-12-19', '用数据挖一挖豆瓣5.3的《长城》水军力量到底有多强大', '“数据虫巢”爬取了截止12月18号上午10时，电影《长城》的所有相关数据，包括评分，评分分布，所有短评(去重后累计20191条)，以及对应短评的评分，所有长影评(去重后累计2458条)，以及对应长影评的相关属性。', 'https://mp.weixin.qq.com/s/4tVirdq2t6mi_qqr8oWx0g', '2');
INSERT INTO mite_wechat_topics VALUES ('2016-12-21', '要说起雾霾，那到底是不是北京的醇厚？', '截止2016年12月20日X时，数据虫巢以搜索“雾霾口罩”为爬取入口，爬取了以防霾口罩为主共63页，1617个相关物品累计804812条评论。要说起雾霾，那到底是不是北京的最醇厚？对此，趁着这波雾霾来袭，我们来用数据挖一挖。', 'https://mp.weixin.qq.com/s/24aLxAcvViAaL1_rEbRRUA', '2');
INSERT INTO mite_wechat_topics VALUES ('2016-12-27', '我的2016年终总结', '马上就2017了，想想2016年这一年，过得还是挺有意思的，不吐槽吐槽还是怪可惜的。我希望的是2017年，能让工作与生活的节奏更好一些，多挑战一些技术，多体验一些生活！不管怎么样，是想过的更好一些，无关于钱，而是生活的质量。', 'https://mp.weixin.qq.com/s/BVqToQnqSU2HXFC5337Bxw', '4');
INSERT INTO mite_wechat_topics VALUES ('2017-01-03', '从0到1构建数据生态系列之六：数据价值挖掘', '这估计《从0到1构建数据生态系列》的最后一篇，主题是数据价值。在之前，我们所有做的一切一切，都是基础，那么其最终的目的是什么？当然，结果很明显，就是数据价值，那么，作为数据生态的最上层，所谓的数据价值又是以什么形式体现的呢？', 'http://mp.weixin.qq.com/s/l7SGjBTUSsUTfyHegjU_AA', '3');
INSERT INTO mite_wechat_topics VALUES ('2016-12-31', '再见，2016！', '或许对于我来说，整个2016最大的收获，不是薪水涨了多少，技术能力强了多少，看了多少好看的东西，认识了多少新的朋友...而是，心态的变化。', 'http://mp.weixin.qq.com/s/aQ_mWrGxKOvoHvozJaxJEA', '4');
INSERT INTO mite_wechat_topics VALUES ('2017-01-11', '2017年，这两个大数据岗位一定会火！', '当然，能够上升到诸如大数据架构师这种级别的人，依然是市场的香馍馍。基于以上这些情况，在大数据领域中，还是有些岗位需求量会走高的。是的，2017年一定会火的。', 'http://mp.weixin.qq.com/s/_BnXedozspcKojiSEe2Fsw', '1');
INSERT INTO mite_wechat_topics VALUES ('2017-01-24', '写给想要跨界、初入大数据领域，以及想了解数据业务模式的朋友', '在阿法狗大放异彩的今天，AI人工智能已经被推到了风尖浪口，但是，相信我，那个还太远，目前还属于高端玩家玩的东西，现在要做的是数据化，没有数据化的基础，妄谈智能化都是扯淡。', 'http://mp.weixin.qq.com/s/AB4wc3wIkwLroSNnYjg8EA', '1');
INSERT INTO mite_wechat_topics VALUES ('2017-02-03', '大数据跨界，从这里开始', '你需要能够做出分辨，这些岗位到底是属于什么定位，是偏平台搭建、是偏数据架构、是偏数据处理、是偏业务分析、是偏数据业务挖掘、是偏算法研究等等，结合自己掌握的技术点，才能做更好的选择。', 'http://mp.weixin.qq.com/s/7iTWHvFPerWjdQF7uk0enQ', '1');
INSERT INTO mite_wechat_topics VALUES ('2017-02-07', '小破公司到底要不要搞大数据？', '我认为大数据更重要的是一种处理数据，解决问题的思路、模式，并且是有别于传统应用数据的方式，以及思考问题的方式。', 'http://mp.weixin.qq.com/s/NIW1TvB7-XnEgeQDtX6BOA', '1');
INSERT INTO mite_wechat_topics VALUES ('2017-02-08', '作为大数据从业人员，如何写好一份可堪入目的简历？', '所以整个简历的书写，总结起来其实很简单，别一股脑儿平铺陈述，重要的是对口，分得清主次，最后，经得住考验。', 'http://mp.weixin.qq.com/s/h53kKnKFE3mgqR9gJVBuuQ', '1');
INSERT INTO mite_wechat_topics VALUES ('2017-02-13', '演讲以及知识分享的艺术', '最后，希望这些东西能够对一些有分享需求的朋友带来帮助，其实对那些需要接受知识的朋友也是有帮助的，告诉你如何去接受新的知识，如何掌握自己听讲的节奏。', 'http://mp.weixin.qq.com/s/z9znJGTZNS9FDICxE8_PXQ', '4');
INSERT INTO mite_wechat_topics VALUES ('2017-02-14', '如何打造类似数据虫巢官网系列教程之一：介绍以及准备工作', '其实目的很明确，让玩爬虫、数据分析、以及数据可视化的同行朋友，打通数据从收集，到处理，到分析，到网站数据可视化整套流程。', 'http://mp.weixin.qq.com/s/ij1m7AegCo5I2KyUAHCURg', '2');
INSERT INTO mite_wechat_topics VALUES ('2017-02-16', '如何打造类似数据虫巢官网系列教程之二：爬虫是怎么炼成的', '本文接上一篇《如何打造类似数据虫巢官网系列教程之一：介绍已经准备工作》，不清楚前面剧情的童鞋可以先看看。这篇文章重点在于解决“数据虫巢官网”的底层数据问题，即那些分析数据的原始数据的来源。', 'http://mp.weixin.qq.com/s/k6f1l6kd7EWAO4ziAgK4Tg', '2');
INSERT INTO mite_wechat_topics VALUES ('2017-02-23', '百度奉命牵头筹建深度学习实验室，这事你怎么看', '但这对于互联网行业来说，本身就不是个问题，因为他自身的造血能力足够强，在行业内已经形成了一个良性的大数据人才培训闭环，所以，影响并不会有想象中大。', 'http://mp.weixin.qq.com/s/zEgSgR4XUWj6iKw7M4g1pA', '1');
