<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<c:set var="ctx" value="${pageContext['request'].contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <title>数据虫巢 - 数据行业洞察</title>
    <script src="/js/now_where.js" ></script>
    <script src="/js/op_page1.js" ></script>
    <script src="/js/op_page2.js" ></script>

    <link rel="stylesheet" href="/css/all.css" />
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
    <script src="/js/canvas-nest.min.js" count="200" zindex="-2" opacity="0.5" color="47,135,193" type="text/javascript"></script>

    <!--------------------header begin-------------------->
    <!---------------logo beign---------->
    <div class="box logoBar2 cf">
        <div class="logoBarLeft">
            <a class="logo bg" href="/" target="_blank">数据虫巢</a>
        </div>
    </div>
    <!---------------logo end------------->
    <!---------------导航 beign--------->
    <div class="box navList2">
        <div id="lava"></div>
        <ul id="ulNav">
            <li class="liNav firstLiNav shortNav"><a href="/" class="aNav aNavSel">首页</a></li>
            <li class="liNav longNav"><a href="/gov/addr_type?a_id=111" class="aNav">政务舆情</a></li>
            <li class="liNav longNav"><a href="/insight_type?a_id=10001" class="aNav">行业洞察</a></li>
            <li class="liNav longNav"><a href="/data_topic" class="aNav">大数据观</a></li>
            <li class="liNav longNav"><a href="/about" class="aNav">关于我们</a></li>
        </ul>
    </div>
    <!---------------导航 end----------->
    <!--------------------header end---------------------->

    <!-----------------内页  begin------------------>
    <div class="contentBk nyBk">
        <div class="nyBkIn">
            <div class="box position mb25">当前位置：
                <!--function position2(　>>　,01111) parse begin-->
                大数据观 >> <a href="/data_topic">文章列表</a> >> <a href="/data_topic?type=${data.type}"><script type="text/javascript">document.write(opt_info(${data.type}))</script></a>
                <!--function: position2(　>>　,01111) parse end  0ms cost! -->
            </div>
            <div class="box listBk cf">
                <!--left begin-->
                <div class="listL" style="width:600px;">
                    <ul class="newsList">

                         <c:forEach items="${data.list_main}" var="ds">
                            <li>
                                <h2 class="tit"><a href="${ds.t_url}" target="_blank">${ds.t_title}</a><span> ${ds.p_time}</span></h2>
                                <span class="abs">${ds.t_desc}</span>
                            </li>
                         </c:forEach>

                    </ul>
				    <div class="pages">
                        <!--function selectpage() parse begin-->
                        <a href="/data_topic?type=${data.type}&page=${data.page-1}"><span><script type="text/javascript">document.write(opt_page_up(${data.up_flag}))</script></span></a>&nbsp;&nbsp;当前页[${data.page}]&nbsp;&nbsp;<a href="/data_topic?type=${data.type}&page=${data.page+1}"><script type="text/javascript">document.write(opt_page_down(${data.down_flag}))</script></a>
                        <!--function: selectpage() parse end  0ms cost! -->
                    </div>

                </div>
                <!--left end-->
                <!--right begin-->
                <div class="listR">
                    <a class="bar mb10" href="/data_topic?type=1">大数据观点主题</a>
                    <ul class="zytzList cf mb35">
                        <c:forEach items="${data.list_type1}" var="ds">
                            <li><a href="${ds.t_url}" target="_blank">${ds.t_title}</a></li>
                        </c:forEach>
                    </ul>
                    <a class="bar mb20" href="/data_topic?type=2">大数据挖掘主题</a>
                    <ul class="zytzList cf mb35">
                        <c:forEach items="${data.list_type2}" var="ds">
                            <li><a href="${ds.t_url}" target="_blank">${ds.t_title}</a></li>
                        </c:forEach>
                    </ul>
                    <a class="bar mb20" href="/data_topic?type=3">大数据技术主题</a>
                    <ul class="zytzList cf mb35">
                        <c:forEach items="${data.list_type3}" var="ds">
                            <li><a href="${ds.t_url}" target="_blank">${ds.t_title}</a></li>
                        </c:forEach>
                    </ul>
                    <a class="bar mb20" href="/data_topic?type=4">感悟杂谈主题</a>
                    <ul class="zytzList cf mb35">
                        <c:forEach items="${data.list_type4}" var="ds">
                            <li><a href="${ds.t_url}" target="_blank">${ds.t_title}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <!--right end-->
            </div>
        </div>

    </div>

    <!-----------------内页  end------------------>

    <!---------avg脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_avg_pay'));
        // 指定图表的配置项和数据
        option = {
            title: [{
                text: '平均薪酬-${data.avg_pay}￥',
                x: 'center',
                y: 'center',
                textStyle: {
                    color: '#4682B4',
                    fontWeight: 'bolder',
                    fontSize: 18,
                }
            },{
                  text: '大数据职位平均薪酬',
                  subtext: ' --通过4600份大数据相关JD需求，计算的平均薪酬'
            }
            ],
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series: [{
                name: '平均薪酬',
                hoverAnimation: false,
                legendHoverLink: false,
                type: 'pie',
                radius: ['48%', '53%'],
                label: {
                    normal: {
                        show: false,
                    }
                },
                data: [{
                    itemStyle: {
                        normal: {
                            color: '#30d61c',
                        }
                    }
                }]
            }, {
                type: 'pie',
                radius: ['44%', '46%'],
                label: {
                    normal: {
                        show: false,
                    }
                },
                data: [{
                    itemStyle: {
                        normal: {
                            color: '#999'
                        }
                    }
                }]
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

    <!---------edu脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_edu'));
        var context  = eval('${data.listEdu}');
        //名称数组
        var nameArray = new Array();
        //数据
        var valueArray= new Array();
        //进行数据处理
        for (var i=context.length; i>0; i--) {
            nameArray.push(context[i-1].name)
            valueArray.push(context[i-1].value)
        }
        // 指定图表的配置项和数据
        option = {
            title : {
                text: '大数据职位学历分布',
                subtext: ' --数据来源于8/9月几大主流招聘网站的大数据职位JD',
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} 次"
            },
            legend: {
                x : 'center',
                y : 'bottom',
                data:nameArray
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true,
                        type: ['pie', 'funnel']
                    },
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'学历分布',
                    type:'pie',
                    radius : [30, 110],
                    center : ['50%', '50%'],
                    roseType : 'area',
                    data:context
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>


    <!---------words脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_words'));
        var context  = eval('${data.listDy}');
        // 指定图表的配置项和数据
         var emotion_data=eval('${data.emotion_words}');
                option = {
                      title:{
                          text:"大数据招聘福利待遇特征云分布",
                          subtext: ' --数据来源于8/9月几大主流招聘网站的大数据职位JD'
                      },
                      tooltip: {},
                        toolbox: {
                            show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                      series: [{
                          type: 'wordCloud',
                          gridSize: 20,
                          sizeRange: [13, 30],
                          rotationRange: [0, 0],
                          shape: 'circle',
                          radius: 70,
                          textStyle: {
                              normal: {
                                  color: function() {
                                      return 'rgb(' + [
                                          Math.round(Math.random() * 160),
                                          Math.round(Math.random() * 160),
                                          Math.round(Math.random() * 160)
                                      ].join(',') + ')';
                                  }
                              },
                              emphasis: {
                                  shadowBlur: 10,
                                  shadowColor: '#333'
                              }
                          },
                          data: context
                      }]
                  };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>


    <!---------city脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_city'));
        var context  = eval('${data.listCity}');
        //名称数组
        var nameArray = new Array();
        //数据
        var valueArray= new Array();
        //进行数据处理
        for (var i=context.length; i>0; i--) {
            nameArray.push(context[i-1].name)
            valueArray.push(context[i-1].value)
        }
        // 指定图表的配置项和数据
        option = {
            title: {
                text: '大数据城市需求排行榜Top15',
                subtext: ' --数据来源于8/9月几大主流招聘网站的大数据职位JD'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            toolbox: {
                show: true,
                feature: {
                    restore: {},
                    dataView: {readOnly: false},
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: nameArray
            },
            series: [
                {
                    name: '城市大数据需求排行榜',
                    type: 'bar',
                    data: valueArray
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>


    <!----------------底部信息 begin---------------->
    <div class="bot">
        <div class="box cf">
            <div class="contactUs">
                <h2 class="contactIcon bg cf"></h2>
                <span class="phoneEmail phoneEmailL">
                    <b class="telIcon cz bg"></b>
                    <div class="telNum">
                        <p>mute88</span></p>
                    </div>
                </span>
                <span class="phoneEmail phoneEmailR"><b class="telIcon tel bg"></b>中国·深圳</span>
                <span class="phoneEmail phoneEmailL"><b class="telIcon email bg"></b>blogchong@qq.com</span>
                <span class="phoneEmail phoneEmailR"><b class="telIcon">QQ</b>874450476</span>
            </div>
            <div class="weixin">
                <span class="wxIcon bg"></span>
                <h2> 【数据虫巢】 官方微信公众号</h2>
            </div>
        </div>
    </div>
  <!----------------底部信息 end---------------->



</body>
</html>