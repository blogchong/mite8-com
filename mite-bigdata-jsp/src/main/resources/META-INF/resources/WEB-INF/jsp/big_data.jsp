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
    <title>数据虫巢 - 行业报告 - 大数据职位需求报告</title>
    <!-- 引入 echarts.js -->
    <script src="/js/echarts.min.js" ></script>
    <script src='/js/echarts-wordcloud.js'></script>

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
         <div class="box mb25">
            <span style="font-weight:bold;">行业数据洞察报告列表</span>： <a href="/insight/jd_comments_wm">【雾霾影响数据分析报告】</a> || <a href="/insight/big_data">【大数据职位需求数据分析报告】</a>
         </div>
         <div class="box"><p> </p></div>
        <div class="box mb25">
            当前位置： <a href="/insight_type?a_id=10001">行业洞察</a>　>>　<a href="/insight/big_data">大数据职位需求报告</a>
        </div>
        <div class="box mb25">
            <p> </p>
        </div>

        <div class="box mb25">
            <p> </p>
        </div>
        <div class="box mb25">
            <span style="font-weight:bold;">大数据职位需求数据分析报告案例：</span> 爬取了智联招聘、前程无忧、拉勾网、中华英才网等主流招聘网站大数据领域相关等近一个月内(2016八月下旬以及九月上旬数据)的职位数据，共4600份真实的企业大数据相关JD数据，进行大数据职位需求全方面的分析挖掘，详细报告点击 大数据观 -> 数据挖掘专题 -> <a href="http://mp.weixin.qq.com/s/eAfDlWc2pOcykWAd84LpVw">《大数据职位画像-看看你是不是白混了贼多年！》</a>。
        </div>

        <div class="box" style="width: 100%;height:4px;"></div>

        <div class="box mb25">

            <!----------平均薪酬&学历分布------------->
            <div class = "box" style="width: 100%; height:350px">
               <div id="div_avg_pay" style="width: 48%;float: left;height:350px;"></div>
               <div style="width: 4%;float: left;height:350px;"></div>
               <div id="div_edu" style="width: 48%;float: left;height:350px;"></div>
            </div>

          <div class="box" style="width: 100%;height:25px;"></div>

          <!----------词云&需求city------------->
          <div class = "box" style="width: 100%; height:350px">
             <div id="div_words" style="width: 48%;float: left;height:350px;"></div>
             <div style="width: 4%;float: left;height:350px;"></div>
             <div id="div_city" style="width: 48%;float: left;height:350px;"></div>
          </div>

          <div class="box" style="width: 100%;height:25px;"></div>

          <!----------经验&收入------------->
          <div class = "box" style="width: 100%; height:350px">
             <div id="div_income" style="width: 48%;float: left;height:350px;"></div>
             <div style="width: 4%;float: left;height:350px;"></div>
             <div id="div_exp" style="width: 48%;float: left;height:350px;"></div>
          </div>

          <div class="box" style="width: 100%;height:25px;"></div>

          <!----------方向&规模------------->
         <div class = "box" style="width: 100%; height:350px">
           <div id="div_tech" style="width: 48%;float: left;height:350px;"></div>
           <div style="width: 4%;float: left;height:350px;"></div>
           <div id="div_scale" style="width: 48%;float: left;height:350px;"></div>
         </div>

        </div>
        <div class="box" style="width: 100%;height:10px;"></div>
      </div>
    </div>
    <!-----------------内页  end------------------>

    <!---------收入income脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_income'));
        // 指定图表的配置项和数据
        var context  = eval('${data.listIn}');
        //名称数组
        var nameArray = new Array();
        //数据
        var valueArray= new Array();
        //进行数据处理
        for (var i=context.length; i>0; i--) {
            nameArray.push(context[i-1].name)
            valueArray.push(context[i-1].value)
        }

        option = {
            "title": {
                "text": "大数据职位薪酬分布",
                "subtext": " --数据来源于8/9月几大主流招聘网站的大数据职位JD，并对薪酬进行结构化分析"
            },
            "tooltip": {
                "trigger": "axis",
                "axisPointer": {
                    "type": "shadow",
                    textStyle: {
                        color: "#fff"
                    }

                },
            },
            "grid": {
                "borderWidth": 0,
                textStyle: {
                    color: "#fff"
                }
            },
            "calculable": true,
            "xAxis": [{
                "type": "category",
                "axisLine": {
                    lineStyle: {
                        color: '#90979c'
                    }
                },
                "splitLine": {
                    "show": false
                },
                "axisTick": {
                    "show": false
                },
                "splitArea": {
                    "show": false
                },
                "axisLabel": {
                    "interval": 0,
                     "rotate": 60
                },
                "data": nameArray
            }],
            "yAxis": [{
                "type": "value",
                "splitLine": {
                    "show": false
                },
                "axisLine": {
                    lineStyle: {
                        color: '#90979c'
                    }
                },
                "axisTick": {
                    "show": false
                },
                "axisLabel": {
                    "interval": 0,

                },
                "splitArea": {
                    "show": false
                },

            }],
            "series": [{
                    "name": "需求人数",
                    "type": "bar",
                    "stack": "月薪",
                    "barMaxWidth": 35,
                    "barGap": "10%",
                    "itemStyle": {
                        "normal": {
                            "label": {
                                "textStyle": {
                                    "color": "#fff"
                                },
                                "position": "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    "data":valueArray
                }, {
                    "name": "需求人数",
                    "type": "line",
                    "stack": "月薪",
                    symbolSize:10,
                    symbol:'circle',
                    "itemStyle": {
                        "normal": {
                            "barBorderRadius": 0,
                            "label": {
                                "show": true,
                                "position": "top",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    "data": valueArray
                },
            ]
        }
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

   <!---------经验exp脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_exp'));
        var context  = eval('${data.listExp}');
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
        var dataStyle = {
            normal: {
                label: {
                    show: false
                },
                labelLine: {
                    show: false
                },
                shadowBlur: 40,
                shadowColor: 'rgba(40, 40, 40,0.5)',
            }
        };
        option = {
            title: {
                text: '大数据职位经验需求分布',
                "subtext": " --数据来源于8/9月几大主流招聘网站的大数据职位JD，并对经验需求进行结构化分析"
            },

            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: '6%',
                top: '33%',
                data: nameArray
            },
            series: [{
                name: '经验需求占比',
                type: 'pie',
                radius: ['30%', '50%'],
                center : ['50%', '60%'],
                avoidLabelOverlap: false,
                itemStyle: dataStyle,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        formatter: function(param) {
                            return param.percent.toFixed(0) + '%';
                        },
                        textStyle: {
                            fontSize: '25',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                data:context
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

   <!---------技术方向tech脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_tech'));
        var context  = eval('${data.listTech}');
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
                text: '大数据职位技术细分需求分布',
                "subtext": " --数据来源于8/9月几大主流招聘网站的大数据职位JD，并对技术进行结构化分析",
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '需求人数',
                    type: 'pie',
                    radius : '50%',
                    center: ['50%', '50%'],
                    data:context,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

   <!---------规模scale脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_scale'));
        var context  = eval('${data.listScale}');
        //名称数组
        var nameArray = new Array();
        //数据
        var data= new Array();
        //进行数据处理
        for (var i=context.length; i>0; i--) {
            nameArray.push(context[i-1].name)
            data.push(context[i-1].value)
        }
        // 指定图表的配置项和数据
        var markLineData = [];
        for (var i = 1; i < data.length; i++) {
            markLineData.push([{
                xAxis: i - 1,
                yAxis: data[i - 1],
                value: (data[i] / data[i - 1] * 100).toFixed(2)
            }, {
                xAxis: i,
                yAxis: data[i]
            }]);
        }
        option = {
            title: {
                text: '大数据职位企业招聘规模需求分布',
                "subtext": " --数据来源于8/9月几大主流招聘网站的大数据职位JD，并对企业规模进行结构化分析",
            },
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '60',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : nameArray,
                    axisTick: {
                        alignWithLabel: true
                    },
                    "axisLabel": {
                        "interval": 0,
                         "rotate": 60
                    },
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'需求人数',
                    type:'bar',
                    barWidth: '60%',
                    data: data,

                    markLine: {
                        label: {
                            normal: {
                                position: 'middle'
                            }
                        },
                        lineStyle: {
                            normal: {
                                type: 'solid'
                            }
                        },
                        data: markLineData
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

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