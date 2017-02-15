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
    <title>数据虫巢 - 行业报告 - 雾霾数据分析报告</title>
    <!-- 引入 echarts.js -->
    <script src="/js/echarts.min.js" ></script>
    <script src="/js/china.js"></script>

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
            当前位置： <a href="/insight_type?a_id=10001">行业洞察</a>　>>　<a href="/insight/jd_comments_wm">雾霾数据分析报告</a>
        </div>
        <div class="box mb25">
            <p> </p>
        </div>

        <div class="box mb25">
            <p> </p>
        </div>
        <div class="box mb25">
            <span style="font-weight:bold;">雾霾数据分析报告案例：</span> 数据来自于JD平台雾霾口罩等相关雾霾物品购买评论数据，供1617个相关物品累计804812个评论，对雾霾的影响进行分析挖掘，提供详细的雾霾影响分析报告，详细报告点击 大数据观 -> 数据挖掘专题 -> <a href="https://mp.weixin.qq.com/s/24aLxAcvViAaL1_rEbRRUA">《要说雾霾，到底是不是北京的醇厚？》</a>。
        </div>

        <div class="box" style="width: 100%;height:4px;"></div>

        <div class="box mb25">

            <!----------地域分布------------->
            <div class = "box" style="width: 100%; height:500px">
               <div id="div_area" style="width: 100%;float: left;height:500px;"></div>
            </div>

          <div class="box" style="width: 100%;height:25px;"></div>

            <div class="box" style="width: 100%;height:25px;"></div>

            <!----------BJ-Day分布------------->
            <div class = "box" style="width: 100%; height:450px">
               <div id="div_day" style="width: 100%;float: left;height:450px;"></div>
            </div>

          <!----------地域2排行榜------------->
          <div class = "box" style="width: 100%; height:350px">
             <div id="div_area2" style="width: 48%;float: left;height:350px;"></div>
             <div style="width: 4%;float: left;height:350px;"></div>
             <div id="div_month" style="width: 48%;float: left;height:350px;"></div>
          </div>

           <div class="box" style="width: 100%;height:25px;"></div>

            <!----------全局时段分布------------->
            <div class = "box" style="width: 100%; height:350px">
               <div id="div_hours" style="width: 100%;float: left;height:350px;"></div>
            </div>

        </div>
        <div class="box" style="width: 100%;height:10px;"></div>
      </div>
    </div>
    <!-----------------内页  end------------------>

    <!---------地域-------->
     <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_area'));

        var context  = eval('${data.area}');
        //名称数组
        var nameArray = new Array();
        //数据
        var valueArray= new Array();

        //进行数据处理
        for (var i=context.length; i>0; i--) {
            nameArray.push(context[i-1].name)
            valueArray.push(context[i-1].value)
        }

        function randomData() {
            return Math.round(Math.random()*1000);
        }

        option = {
            title: {
                text: '响应雾霾影响地域分布',
                subtext: ' -- 基于JD平台1617个雾霾口罩相关物品，累计804812个评论做的相对分布分析。'
            },
            tooltip: {
                trigger: 'item'
            },
            toolbox: {
                show: true,
                feature: {
                    restore: {},
                    dataView: {readOnly: false},
                    saveAsImage: {}
                }
            },
            visualMap: {
                min: 0,
                max: 55000,
                left: 'left',
                top: 'bottom',
                text: ['高','低'],           // 文本，默认为数值文本
                calculable: true
            },
            series: [
                {
                    name: '人数*K',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data:context
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

    <!---------city排行脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_area2'));
        var context  = eval('${data.area2}');
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
                text: '响应雾霾影响省份排行榜Top15',
                subtext: ' -- 基于JD平台1617个雾霾口罩相关物品，累计804812个评论做的相对分布分析。'
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
                    name: '响应雾霾影响省份排行榜',
                    type: 'bar',
                    data: valueArray
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

    <!---------month分布脚本------------>
     <script type="text/javascript">
             // 基于准备好的dom，初始化echarts实例
             var myChart = echarts.init(document.getElementById('div_month'));
             var context  = eval('${data.month}');
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
                    text: '雾霾随月份变化趋势',
                    subtext: ' -- 基于JD平台1617个雾霾口罩相关物品，累计804812个评论做的相对分布分析。'
                },
                tooltip : {
                    trigger: 'axis'
                },

                toolbox: {
                    show : true,
                    feature : {
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        dataView: {},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
              grid: {
                  top: 80,
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true
              },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : nameArray
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        name: '受影响人数(*K)',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : [
                    {
                        name:'每个月雾霾影响情况',
                        type:'bar',
                        data:valueArray,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
         // 使用刚指定的配置项和数据显示图表。
         myChart.setOption(option);
     </script>

    <!---------hours脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_hours'));
             var context  = eval('${data.hours}');
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
                    text: '24个时段，购买防雾霾物品的变化趋势',
                    subtext: ' -- 基于JD平台1617个雾霾口罩相关物品，累计804812个评论做的相对分布分析。'
                },
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                   show : true,
                   feature : {
                       magicType : {show: true, type: ['line', 'bar']},
                       restore : {show: true},
                       dataView: {},
                       saveAsImage : {show: true}
                   }
                },
                grid: {
                    top: 80,
                    left: 55
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : nameArray
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        name : '购买人数(*K)'
                    }
                ],
                series : [
                    {
                        name:'24小时购买人数变化趋势',
                        type:'line',
                        areaStyle: {normal: {}},
                        data:valueArray
                    }
                ]
            };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

     <!---------BJ-Day分布计算脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_day'));
        var context  = eval('${data.day}');
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
                text: '北京2016年，截止2016-12-20受雾霾影响变化趋势',
                subtext: ' -- 基于JD平台1617个雾霾口罩相关物品，累计804812个评论做的相对分布分析。'
            }],
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                top: 80,
                left: 55
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
                name: '受影响人数(*K)',
                axisLabel: {
                    formatter: '{value}'
                }
            },
            series: [
                {
                    name:'北京2016全年段受雾霾影响变化趋势',
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