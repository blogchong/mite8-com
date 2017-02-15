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
    <title>数据虫巢 - 政务舆情 - 江西·赣州·定南 - 人才&招聘舆情</title>
    <!-- 引入 echarts.js -->
    <script src="/js/echarts.min.js" ></script>

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
           <span style="font-weight:bold;">舆情分析列表</span>： <a href=/gov/jx_gz_dn/finance>【财政统计分析】</a> || <a href=/gov/jx_gz_dn/politics>【问政舆情】</a> || <a href=/gov/jx_gz_dn/hire>【人才&招聘舆情】</a> || <a href=/gov/jx_gz_dn/praise>【外界口碑舆情】</a> || <a href=/gov/jx_gz_dn/house>【房产舆情】</a> || <a href=/gov/jx_gz_dn/edu>【教育舆情】</a>
        </div>
        <div class="box"><p> </p></div>
        <div class="box mb25">
            当前位置：  <a href="/gov/addr_type?a_id=111">政务舆情</a>　>>　<a href="/gov/addr_type?a_id=111">江西·赣州·定南</a>　>>　<a href=/gov/jx_gz_dn/hire>人才&招聘舆情</a>
        </div>

        <div class="box" style="width: 100%;height:4px;"></div>

        <div class="box mb25">
            <!----------均值&学历分布------------->
            <div class = "box" style="width: 100%; height:350px">
               <div id="div_avg" style="width: 48%;float: left;height:350px;"></div>
               <div style="width: 4%;float: left;height:350px;"></div>
               <div id="div_edu" style="width: 48%;float: left;height:350px;"></div>
            </div>

            <!----------求职年龄分布&经验分布------------->
            <div class = "box" style="width: 100%; height:350px">
               <div id="div_age" style="width: 48%;float: left;height:350px;"></div>
               <div style="width: 4%;float: left;height:350px;"></div>
               <div id="div_com" style="width: 48%;float: left;height:350px;"></div>
            </div>
          </div>

        <div class="box" style="width: 100%;height:4px;"></div>

       </div>
    </div>
    <!-----------------内页  end------------------>

     <!---------企业规模、性质计算脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_com'));
        var context  = eval('${data.listHireComScale}');
        //名称数组
        var nameArray = new Array();
        //数据
        var valueArray= new Array();

        //进行数据处理
        for (var i=context.length; i>0; i--) {
            nameArray.push(context[i-1].name)
            valueArray.push(context[i-1].value)
        }

        var context2  = eval('${data.listHireComNature}');
        //名称数组
        var nameArray2 = new Array();
        //数据
        var valueArray2= new Array();

        //进行数据处理
        for (var i=context2.length; i>0; i--) {
            nameArray2.push(context2[i-1].name)
            valueArray2.push(context2[i-1].value)
        }

        var context  = eval('${data.listHireComScale}');
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
                label: {show:false},
                labelLine: {show:false},
                shadowBlur: 40,
                shadowColor: 'rgba(40, 40, 40,0.8)',
            }
        };

        option = {
             title: [{
                text: '招聘企业规模/性质分布',
                subtext: ' --自[2015-01-01]起，企业招聘规模/性质分布情况'
            },{
                subtext: '【企业规模分布】',
                x: '22%',
                y: '13%'
            },{
                 subtext: '【企业性质分布】',
                 x: '70%',
                 y: '13%'
             }],
             toolbox: {
                 show: true,
                 feature: {
                     restore: {},
                     dataView: {readOnly: false},
                     saveAsImage: {}
                 }
             },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: [{
                orient: 'vertical',
                x: '1%',
                top: '35%',
                data:nameArray
            },
            {
                orient: 'vertical',
                x: '51%',
                top: '22%',
                data:nameArray2
            }],
            series: [
                {
                    name:'企业规模分布',
                    type:'pie',
                    radius: ['20%', '32%'],
                    center: ['33%', '50%'],
                    avoidLabelOverlap: false,
                    itemStyle: dataStyle,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            formatter: function (param) {
                                return param.percent.toFixed(0) + '%';
                            },
                            textStyle: {
                                fontSize: '24',
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
                },
                {
                    name:'企业性质分布',
                    type:'pie',
                    radius: ['20%', '32%'],
                    center: ['80%', '50%'],
                    avoidLabelOverlap: false,
                    itemStyle: dataStyle,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            formatter: function (param) {
                                return param.percent.toFixed(0) + '%';
                            },
                            textStyle: {
                                fontSize: '24',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: true

                        }

                    },
                    data:context2
                }
            ]
        };
       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
   </script>

     <!---------age计算脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_age'));
        var listHireExp  = eval('${data.listHireExp}');
        var context  = eval('${data.listResumeAge}');
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
            title: [{
                text: '求职年龄变化趋势/招聘经验需求分布',
                subtext: ' --自[2015-01-01]起，求职人数随年龄变化/招聘需求经验分布'
            },{
                subtext: '【招聘经验需求分布】',
                x: '56%',
                y: '10%'
            }],
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['人数最多对应的年龄','人数最少对应的年龄']
            },
            toolbox: {
                show: true,
                feature: {
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    dataView: {readOnly: false},
                    saveAsImage: {}
                }
            },
            xAxis:  {
                type: 'category',
                boundaryGap: false,
                data: nameArray
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value}'
                }
            },
            series: [
                 {
                    name: '招聘对应的经验需求',
                    type: 'pie',
                    radius : '26%',
                    center: ['68%', '45%'],
                    data:listHireExp,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.9)'
                        }
                    }
                },
                {
                    name:'求职年龄分布',
                    type:'line',
                    data:valueArray,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

     <!---------edu计算脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_edu'));
        var listResumeEdu  = eval('${data.listResumeEdu}');
        var listHireEdu  = eval('${data.listHireEdu}');
        // 指定图表的配置项和数据
        option = {
            title : [{
                text: '求职/招聘学历分布对比',
                subtext: ' --自[2015-01-01]起，收集的地方门户招聘数据'
            },
            {
                subtext: '【招聘学历分布图】',
                x: '13%',
                y: '10%'
            },
            {
                subtext: '【求职学历分布图】',
                x: '62%',
                y: '10%'
            }],
            toolbox: {
                show: true,
                feature: {
                    restore: {},
                    dataView: {readOnly: false},
                    saveAsImage: {}
                }
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '学历对应的招聘次数',
                    type: 'pie',
                    radius : '38%',
                    center: ['25%', '50%'],
                    data:listHireEdu,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                },
                {
                    name: '学历对应人数',
                    type: 'pie',
                    radius : '38%',
                    center: ['75%', '50%'],
                    data:listResumeEdu,
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

    <!---------薪酬均值脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_avg'));
            // 指定图表的配置项和数据
           option = {
               title : {
                   text: '招聘/求职平均薪酬对比',
                   subtext: ' --自[2015-01-01]起，收集的地方门户招聘数据',
               },
               toolbox: {
                   show: true,
                   feature: {
                       restore: {},
                       dataView: {readOnly: false},
                       saveAsImage: {}
                   }
               },
               "series": [
                   {
                       "center": [
                           "25.0%",
                           "50%"
                       ],
                       "radius": [
                           "39%",
                           "40%"
                       ],
                       "clockWise": false,
                       "hoverAnimation": false,
                       "type": "pie",
                       "itemStyle": {
                           "normal": {
                               "label": {
                                   "show": true,
                                   "textStyle": {
                                       "fontSize": 12,
                                       "fontWeight": "bold"
                                   },
                                   "position": "center"
                               },
                               "labelLine": {
                                   "show": false
                               },
                               "color": "#5886f0",
                               "borderColor": "#5886f0",
                               "borderWidth": 16
                           },
                           "emphasis": {
                               "label": {
                                   "textStyle": {
                                       "fontSize": 12,
                                       "fontWeight": "bold"
                                   }
                               },
                               "color": "#5886f0",
                               "borderColor": "#5886f0",
                               "borderWidth": 16
                           }
                       },
                       "data": [
                           {
                               "value": ${data.avg_pay},
                               "name": "求职-薪酬均值${data.avg_pay}￥"
                           },
                           {
                               "name": " ",
                               "value": ${data.avg_pay},
                               "itemStyle": {
                                   "normal": {
                                       "label": {
                                           "show": false
                                       },
                                       "labelLine": {
                                           "show": false
                                       },
                                       "color": "#5886f0",
                                       "borderColor": "#5886f0",
                                       "borderWidth": 0
                                   },
                                   "emphasis": {
                                       "color": "#5886f0",
                                       "borderColor": "#5886f0",
                                       "borderWidth": 0
                                   }
                               }
                           }
                       ]
                   },
                   {
                       "center": [
                           "75.0%",
                           "50%"
                       ],
                       "radius": [
                           "39%",
                           "40%"
                       ],
                       "clockWise": false,
                       "hoverAnimation": false,
                       "type": "pie",
                       "itemStyle": {
                           "normal": {
                               "label": {
                                   "show": true,
                                   "textStyle": {
                                       "fontSize": 12,
                                       "fontWeight": "bold"
                                   },
                                   "position": "center"
                               },
                               "labelLine": {
                                   "show": false
                               },
                               "color": "#ee3a3a",
                               "borderColor": "#ee3a3a",
                               "borderWidth": 16
                           },
                           "emphasis": {
                               "label": {
                                   "textStyle": {
                                       "fontSize": 12,
                                       "fontWeight": "bold"
                                   }
                               },
                               "color": "#ee3a3a",
                               "borderColor": "#ee3a3a",
                               "borderWidth": 16
                           }
                       },
                       "data": [
                           {
                               "value": ${data.avg_salary},
                               "name": "招聘-薪酬均值${data.avg_salary}￥"
                           },
                           {
                               "name": " ",
                               "value": ${data.avg_salary},
                               "itemStyle": {
                                   "normal": {
                                       "label": {
                                           "show": false
                                       },
                                       "labelLine": {
                                           "show": false
                                       },
                                       "color": "#ee3a3a",
                                       "borderColor": "#ee3a3a",
                                       "borderWidth": 0
                                   },
                                   "emphasis": {
                                       "color": "#ee3a3a",
                                       "borderColor": "#ee3a3a",
                                       "borderWidth": 0
                                   }
                               }
                           }
                       ]
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