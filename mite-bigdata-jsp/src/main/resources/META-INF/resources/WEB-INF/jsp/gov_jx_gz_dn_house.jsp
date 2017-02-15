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
    <title>数据虫巢 - 政务舆情 - 江西·赣州·定南 - 房产舆情</title>
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
            当前位置：  <a href="/gov/addr_type?a_id=111">政务舆情</a>　>>　<a href="/gov/addr_type?a_id=111">江西·赣州·定南</a>　>>　<a href=/gov/jx_gz_dn/house>房产舆情</a>
        </div>

        <div class="box" style="width: 100%;height:12px;"></div>

        <div class="box mb25">
           <!----------房产均价/楼盘投诉排行------------->
            <div class = "box" style="width: 100%; height:450px">
                <div id="div_avg" style="width: 48%;float: left;height:450px;"></div>
                <div style="width: 4%;float: left;height:420px;"></div>
                <div id="div_ts" style="width: 48%;float: left;height:450px;"></div>
           </div>
        </div>

       <div class="box" style="width: 100%;height:15px;"></div>

       </div>
    </div>
    <!-----------------内页  end------------------>

   <!---------楼盘投诉脚本------------>
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_ts'));
        var context  = eval('${data.listHouseTs}');
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
            title: {
                text: '楼盘被投诉排行榜Top${data.house_top}',
                subtext: ' --数据来自定南人民政府自[2015-01-01]起的问政数据'
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
                    name: '楼盘投诉排行榜',
                    type: 'bar',
                    data: valueArray
                }
            ]
        };
       // 使用刚指定的配置项和数据显示图表。
       myChart.setOption(option);
   </script>

     <!---------楼盘均价脚本------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_avg'));
        option = {
            title: {
                text: '2016年定南楼盘均价',
                subtext: ' --数据来自定南地方门户网站房产频道'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}￥"
            },
            toolbox: {
                show: true,
                feature: {
                    restore: {},
                    dataView: {readOnly: false},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '楼盘价格表盘',
                    type: 'gauge',
                    detail: {formatter:'{value}￥'},
                    data: [{value: ${data.avg_price}, name: '楼盘均价'}],
                    max: ${data.max_price},
                    radius: '70%',
                    x: "80%",
                    y: "50%",
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