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
    <title>数据虫巢 - 政务舆情 - 江西·赣州·定南 - 教育舆情</title>
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
           <span style="font-weight:bold;">舆情分析列表</span>： <a href=/gov/jx_gz_dn/finance>【财政统计分析】</a> || <a href=/gov/jx_gz_dn/politics>【问政舆情】</a> || <a href=/gov/jx_gz_dn/hire>【人才&招聘舆情】</a> || <a href=/gov/jx_gz_dn/praise>【外界口碑舆情】</a> || <a href=/gov/jx_gz_dn/house>【房产舆情】</a> || <a href=/gov/jx_gz_dn/edu>【教育舆情】</a>
        </div>
        <div class="box"><p> </p></div>
        <div class="box mb25">
            当前位置： <a href="/gov/addr_type?a_id=111">政务舆情</a>　>>　<a href="/gov/addr_type?a_id=111">江西·赣州·定南</a>　>>　<a href=/gov/jx_gz_dn/edu>教育舆情</a>
        </div>

        <div class="box" style="width: 100%;height:2px;"></div>
            <div class="box mb25">
               <!----------edu------------->
               <div class = "box" style="width: 100%; height:500px">
                    <div id="div_edu" style="width: 100%;float: left;height:500px;"></div>
               </div>
            </div>
            <div class="box" style="width: 100%;height:5px;"></div>
       </div>
    </div>
    <!-----------------内页  end------------------>

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