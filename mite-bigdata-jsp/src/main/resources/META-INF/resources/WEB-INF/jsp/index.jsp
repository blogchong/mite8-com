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
    <title>数据虫巢 - 提供专业的大数据解决方案</title>
    <!-- 引入 echarts.js -->
    <script src="/js/echarts.min.js" ></script>
    <script src="/js/china.js"></script>
    <script src='/js/echarts-wordcloud.min.js'></script>

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
            <p> </p>
        </div>
        <div class="box mb25">
            当前位置：  <a href=/>首页</a>
        </div>

        <div class="box mb25">
            <p> </p>
        </div>
         <div class="box" style="width: 100%;height:40px;">
            <div class="box mb25">
                <span style="font-weight:bold;">·政务大数据解决方案案例：</span> <a href="/gov/addr_type?a_id=111">江西·赣州·定南-政务舆情</a>　>>　<a href="/gov/addr_type?a_id=111">查看更多【政务舆情】...</a>
            </div>
        </div>

       <div class="box" style="width: 100%;height:2px;"></div>

        <div class="box mb25">
           <!----------国税/地税/政府财政部分------------->
           <div class = "box" style="width: 100%; height:400px">
                <div id="div_t_b_income" style="width: 100%;float: left;height:400px;"></div>
           </div>

           <div class="box" style="width: 100%;height:25px;"></div>

           <!----------教育部分------------->
           <div class = "box" style="width: 100%; height:400px">
               <div id="div_edu" style="width: 100%;float: left;height:400px;"></div>
           </div>

          <div class="box" style="width: 100%;height:10px;"></div>

          <!----------招聘/问政排行------------->
           <div class = "box" style="width: 100%; height:400px">
               <div id="div_age" style="width: 48%;float: left;height:400px;"></div>
               <div style="width: 4%;float: left;height:400px;"></div>
               <div id="div_section" style="width: 48%;float: left;height:400px;"></div>
          </div>
        </div>

        <div class="box" style="width: 100%;height:30px;"></div>

        <div class = "box" style="width: 100%; height:40px">
              <div class="box mb25">
                  <span style="font-weight:bold;">·行业洞察数据报告案例：</span> <a href="/insight/jd_comments_wm">雾霾影响数据分析报告</a> | <a href="/insight/big_data">大数据职位需求报告</a>　>>　<a href="/insight_type?a_id=10001">查看更多【行业数据报告】...</a>
              </div>
        </div>

        <div class="box mb25">

            <!----------BJ-Day分布------------->
            <div class = "box" style="width: 100%; height:450px">
               <div id="div_day" style="width: 100%;float: left;height:450px;"></div>
            </div>

            <div class="box" style="width: 100%;height:15px;"></div>

            <!----------地域分布------------->
            <div class = "box" style="width: 100%; height:500px">
               <div id="div_wm_area" style="width: 100%;float: left;height:500px;"></div>
            </div>

            <div class="box" style="width: 100%;height:15px;"></div>

            <!----------大数据词云/企业招聘规模------------->
             <div class = "box" style="width: 100%; height:400px">
                 <div id="div_words2" style="width: 48%;float: left;height:400px;"></div>
                 <div style="width: 4%;float: left;height:400px;"></div>
                 <div id="div_scale" style="width: 48%;float: left;height:400px;"></div>
            </div>
        </div>

       <div class="box" style="width: 100%;height:10px;"></div>

       </div>
    </div>
    <!-----------------内页  end------------------>

    <!---------地域-------->
     <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_wm_area'));

        var context  = eval('${data.wm_area}');
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

    <!---------words脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_words2'));
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

    <!---------BJ-Day分布计算脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_day'));
        var context  = eval('${data.wm_day}');
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

    <!---------应对部门脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_section'));
            var context  = eval('${data.section}');
            // 指定图表的配置项和数据
           option = {
               title : {
                   text: '问政部门分布图',
                   subtext: ' --自[${data.time}]起，问政涉及部门分布'
               },
               tooltip : {
                   trigger: 'item',
                   formatter: "{a} <br/>{b} : {c} ({d}%)"
               },
               toolbox: {
                   show : true,
                   feature : {
                    restore: {},
                    dataView: {readOnly: false},
                    saveAsImage: {}
                   }
               },
               calculable : true,
               series : [
                   {
                       name:'次数',
                       type:'pie',
                       radius : [10, 110],
                       center : ['50%', 200],
                       roseType : 'area',
                       x: '50%',               // for funnel
                       max: 40,                // for funnel
                       sort : 'ascending',     // for funnel
                       data:context
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

     <!---------教育脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_edu'));
          var myHosName = '始发地 - 定南县';
          option = null;
          var geoCoordMap = {
                        '定南': [115.03,24.78],
                        '安徽': [117.17,31.52],
                        '北京': [116.24,39.55],
                        '重庆': [106.54,29.59],
                        '福建': [119.18,26.05],
                        '甘肃': [103.51,36.04],
                        '广东': [113.14,23.08],
                        '广西': [108.19,22.48],
                        '贵州': [106.42,26.35],
                        '海南': [110.20,20.02],
                        '河北': [114.30,38.02],
                        '河南': [113.40,34.46],
                        '黑龙江': [126.36,45.44],
                        '湖北': [114.17,30.35],
                        '湖南': [112.59,28.12],
                        '吉林': [125.19,43.54],
                        '江苏': [118.46,32.03],
                        '江西': [115.55,28.40],
                        '辽宁': [123.25,41.48],
                        '内蒙古': [111.41,40.48],
                        '宁夏': [106.16,38.27],
                        '山东': [117.0,36.40],
                        '山西': [112.33,37.54],
                        '陕西': [108.57,34.17],
                        '上海': [121.29,31.14],
                        '四川': [104.04,30.4],
                        '天津': [117.12,39.02],
                        '西藏': [91.08,29.39],
                        '新疆': [87.36,43.45],
                        '云南': [102.42,25.04],
                        '浙江': [120.1,30.16],
                        '香港': [115.12,21.23],
                        '澳门': [115.07,21.33],
                        '台湾': [121.3,25.03],
                        '青海': [101.48,36.38]
          };

          var BJData = eval('${data.edu}');

          var planePath = "path://M917.965523 917.331585c0 22.469758-17.891486 40.699957-39.913035 40.699957-22.058388 0-39.913035-18.2302-39.913035-40.699957l-0.075725-0.490164-1.087774 0c-18.945491-157.665903-148.177807-280.296871-306.821991-285.4748-3.412726 0.151449-6.751774 0.562818-10.240225 0.562818-3.450589 0-6.789637-0.410346-10.202363-0.524956-158.606321 5.139044-287.839661 127.806851-306.784128 285.436938l-1.014096 0 0.075725 0.490164c0 22.469758-17.854647 40.699957-39.913035 40.699957s-39.915082-18.2302-39.915082-40.699957l-0.373507-3.789303c0-6.751774 2.026146-12.903891 4.91494-18.531052 21.082154-140.712789 111.075795-258.241552 235.432057-312.784796C288.420387 530.831904 239.989351 444.515003 239.989351 346.604042c0-157.591201 125.33352-285.361213 279.924387-285.361213 154.62873 0 279.960203 127.770012 279.960203 285.361213 0 97.873098-48.391127 184.15316-122.103966 235.545644 124.843356 54.732555 215.099986 172.863023 235.808634 314.211285 2.437515 5.290493 4.01443 10.992355 4.01443 17.181311L917.965523 917.331585zM719.822744 346.679767c0-112.576985-89.544409-203.808826-199.983707-203.808826-110.402459 0-199.944821 91.232864-199.944821 203.808826s89.542362 203.808826 199.944821 203.808826C630.278335 550.488593 719.822744 459.256752 719.822744 346.679767z";
          //    简笔人2
          //    var  planePath="path://M621.855287 587.643358C708.573965 540.110571 768 442.883654 768 330.666667 768 171.608659 648.609267 42.666667 501.333333 42.666667 354.057399 42.666667 234.666667 171.608659 234.666667 330.666667 234.666667 443.22333 294.453005 540.699038 381.59961 588.07363 125.9882 652.794383 21.333333 855.35859 21.333333 1002.666667L486.175439 1002.666667 1002.666667 1002.666667C1002.666667 815.459407 839.953126 634.458526 621.855287 587.643358Z";

          var convertData = function(data) {
              var res = [];
              for (var i = 0; i < data.length; i++) {
                  var dataItem = data[i];
                  var fromCoord = geoCoordMap[dataItem[0].name];
                  var toCoord = geoCoordMap[dataItem[1].name];
                  if (fromCoord && toCoord) {
                      res.push({
                          fromName: dataItem[0].name,
                          toName: dataItem[1].name,
                          coords: [fromCoord, toCoord]
                      });
                  }
              }
              return res;
          };

          var color = ['#a6c84c', '#ffa022', '#46bee9'];
          var mySeries = [];
          [
              [myHosName, BJData]
          ].forEach(function(item, i) {
              mySeries.push({ //线
                  name: item[0],
                  //                      name: item[0] + ' Top10',
                  type: 'lines',
                  zlevel: 1,
                  effect: {
                      show: true,
                      period: 6,
                      trailLength: 0.7,
                      color: '#fff',
                      symbolSize: 3
                  },
                  lineStyle: {
                      normal: {
                          color: color[0],
                          width: 0,
                          curveness: 0.2
                      }
                  },
                  data: convertData(item[1])
              }, { //移动 点
                  name: item[0],
                  //                      name: item[0] + ' Top10',
                  type: 'lines',
                  zlevel: 2,
                  effect: {
                      show: true,
                      period: 6,
                      trailLength: 0,
                      symbol: planePath,
                      symbolSize: 15
                  },
                  lineStyle: {
                      normal: {
                          color: color[1],
                          width: 1,
                          opacity: 0.4,
                          curveness: 0.2
                      }
                  },
                  data: convertData(item[1])
              }, { //省份圆点
                  name: item[0],
                  //                      name: item[0] + ' Top10',
                  type: 'effectScatter',
                  coordinateSystem: 'geo',
                  zlevel: 2,
                  rippleEffect: {
                      brushType: 'stroke'
                  },
                  label: {
                      normal: {
                          show: true,
                          position: 'right',
                          formatter: '{b}'
                      }
                  },
                  symbolSize: function(val) {
                      return val[2] / 6;
                  },
                  itemStyle: {
                      normal: {
                          color: function(params) {
                              var tmp = params.data.value[2]
                              if (tmp < 100) {
                                  return 'green';
                              } else if (tmp > 150) {
                                  return 'red'
                              } else
                                  return 'yellow';
                          }
                      }
                  },
                  data: item[1].map(function(dataItem) {
                      return {
                          name: dataItem[1].name,
                          value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
                      };
                  })
              });
          });

          option = {
              title: {
                  text: '2016高考人才流向图[模拟]',
                  subtext: ' --模拟2016高考人才流向其他省市的动态迁徙图'
              },
              tooltip: {
                  trigger: 'item',
                  formatter: function(params) {
                      if (params.seriesIndex == 2 || params.seriesIndex == 5 || params.seriesIndex == 8) {
                          return params.name + '<br>' + params.seriesName + ':' + params.data.value[2] + ' 人次';
                      } else if (params.seriesIndex == 1 || params.seriesIndex == 4 || params.seriesIndex == 7) {
                          return params.data.fromName + '→' + params.data.toName;
                      }
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
              legend: {
                  orient: 'vertical',
                  top: '6%',
                  left: 'center',
                  data: [myHosName],
                  selectedMode: 'single'
              },
              geo: {
                  map: 'china',
                  label: {
                      emphasis: {
                          show: false
                      }
                  },
                  roam: true,
                  itemStyle: {
                      normal: {
                          areaColor: '#323c48',
                          borderColor: '#404a59'
                      },
                      emphasis: {
                          areaColor: '#2a333d'
                      }
                  }
              },
              series: mySeries
          };


          if (option && typeof option === "object") {
              myChart.setOption(option, true);
          }

          window.onresize = function() {
              myChart.resize();
          }
       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
   </script>

    <!---------t_b_income分部门累积收入脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_t_b_income'));
            var list_month  = eval('${data.p_month}');
            var list_t_income_2014 = eval('${data.list_t_income_2014}');
            var list_t_income_2015 = eval('${data.list_t_income_2015}');
            var list_t_income_2016 = eval('${data.list_t_income_2016}');
            var list_t_b_g_income_2014 = eval('${data.list_t_b_g_income_2014}');
            var list_t_b_g_income_2015 = eval('${data.list_t_b_g_income_2015}');
            var list_t_b_g_income_2016 = eval('${data.list_t_b_g_income_2016}');
            var list_t_b_d_income_2014 = eval('${data.list_t_b_d_income_2014}');
            var list_t_b_d_income_2015 = eval('${data.list_t_b_d_income_2015}');
            var list_t_b_d_income_2016 = eval('${data.list_t_b_d_income_2016}');
            var list_t_b_c_income_2014 = eval('${data.list_t_b_c_income_2014}');
            var list_t_b_c_income_2015 = eval('${data.list_t_b_c_income_2015}');
            var list_t_b_c_income_2016 = eval('${data.list_t_b_c_income_2016}');
            // 指定图表的配置项和数据
            option = {
                baseOption: {
                    timeline: {
                        axisType: 'category',
                        autoPlay: true,
                        playInterval: 1000,
                        data: [
                            '2014-01-01','2015-01-01','2016-01-01',
                        ],
                        label: {
                            formatter : function(s) {
                                return (new Date(s)).getFullYear();
                            }
                        }
                    },
                    title: {
                        subtext: ' --2014/2015/2016分部门收入，数据来自国江西省定南县政府官网'
                    },
                    tooltip: {},
                    toolbox : {
                        'show':true,
                        orient : 'vertical',
                        x: 'right',
                        y: 'center',
                        'feature':{
                            'restore':{'show':true},
                            'dataView': {},
                            'saveAsImage':{'show':true}
                        }
                    },
                    legend: {
                        x: 'right',
                        data: ['总收入','国税收入', '地税收入', '财政部门收入'],
                        selected: {
                            '总收入': true
                        },
                        top: 40
                    },
                    calculable : true,
                    grid: {
                        top: 80,
                        bottom: 85
                    },
                    xAxis: [
                        {
                            'type':'category',
                            'axisLabel':{'interval':0},
                            'data':list_month,
                            splitLine: {show: false}
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '累计收入（万元）'
                        }
                    ],
                    series: [
                        {name: '总收入', type: 'bar'},
                        {name: '国税收入', type: 'bar'},
                        {name: '地税收入', type: 'bar'},
                        {name: '财政部门收入', type: 'bar'},
                        {
                            name: '总收入占比图',
                            type: 'pie',
                            center: ['28%', '40%'],
                            radius: '28%'
                        }
                    ]
                },
                options: [
                    {
                        title: {text: '2014年累计分部门收入'},
                        series: [
                            {data: list_t_income_2014},
                            {data: list_t_b_g_income_2014},
                            {data: list_t_b_d_income_2014},
                            {data: list_t_b_c_income_2014},
                            {data: [
                                {name: '国税收入', value: ${data.t_b_g_income_2014_max}},
                                {name: '地税收入', value: ${data.t_b_d_income_2014_max}},
                                {name: '财政部门收入', value: ${data.t_b_c_income_2014_max}}
                            ]}
                        ]
                    },
                    {
                        title : {text: '2015年累计分部门收入'},
                        series : [
                            {data: list_t_income_2015},
                            {data: list_t_b_g_income_2015},
                            {data: list_t_b_d_income_2015},
                            {data: list_t_b_c_income_2015},
                            {data: [
                                {name: '国税收入', value: ${data.t_b_g_income_2015_max}},
                                {name: '地税收入', value: ${data.t_b_d_income_2015_max}},
                                {name: '财政部门收入', value: ${data.t_b_c_income_2015_max}}
                            ]}
                        ]
                    },
                    {
                        title : {text: '2016年累计分部门收入'},
                        series : [
                            {data: list_t_income_2016},
                            {data: list_t_b_g_income_2016},
                            {data: list_t_b_d_income_2016},
                            {data: list_t_b_c_income_2016},
                            {data: [
                                {name: '国税收入', value: ${data.t_b_g_income_2016_max}},
                                {name: '地税收入', value: ${data.t_b_d_income_2016_max}},
                                {name: '财政部门收入', value: ${data.t_b_c_income_2016_max}}
                            ]}
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