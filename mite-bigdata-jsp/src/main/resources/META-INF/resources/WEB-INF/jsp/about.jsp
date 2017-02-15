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
    <title>数据虫巢 - 关于我们</title>
    <!-- 引入 echarts.js -->

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
            当前位置：  <a href=/about>关于我们</a>
        </div>

       <div class="box" style="width: 100%;height:5px;"></div>

        <div class="box" style="width: 100%;height:540px;">
           <div class="box mb25">
               <div class="box" style="width: 2%;float: left;height:540px;"></div>
               <div class="box" style="width: 96%;float: left;height:540px;">
                   <p align="center"><span style="font-weight:bold;"><font size="5">数据虫巢</font></span></p>
                   <p>&nbsp;&nbsp;</p>
                   <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们是一个年轻的数据科学工作室团队，专注于多元化的数据采集、存储、建模，挖掘与可视化，希望使用技术、数据改变生活，实现数据驱动的精准化运营、决策，以及提升业务治理能力。</p>
                   <p>&nbsp;</p>
                   <p><span style="font-weight:bold;"><font size="3">我们团队：</font></span></p>
                   <p>·毕业于全国重点一流大学 - 哈尔滨工业大学(211/985重点院校)。</p>
                   <p>·拥有大数据近6年的行业经验，精通数据的爬取、存储、挖掘，以及分析等大数据技术。</p>
                   <p> &nbsp;</p>
                   <p><span style="font-weight:bold;"><font size="3">我们擅长：</font></span></p>
                   <p><span style="font-weight:bold;">·政务舆情：</span>依赖于公开的政府以及相关机构的官网数据，以及结合地方性门户数据，针对性的进行诸如财政统计分析、问政舆情、人才&招聘舆情、外界口碑舆情、地方房产舆情以及教育舆情等数据化分析，进行政务信息的全面数据化、精准化，进一步提升政府的社会治理水平。</p>
                   <p><span style="font-weight:bold;">·行业报告：</span>依赖于互联网开放的行业信息数据，进行收据爬取、收集、建模，以及挖掘分析等，提供垂直行业的大数据数据咨询服务，提供精准化的行业数据分析报告，诸如房产走势分析报告、大数据职位需求分析报告等。</p>
                   <p> &nbsp;</p>
                   <p><span style="font-weight:bold;"><font size="3">联系我们：</font></span></p>
                   <p>·官方微信公众号：数据虫巢(ID: blogchong)</p>
                   <p>·邮箱：blogchong@qq.com</p>
                   <p>·微信：mute88</p>
                   <p>·地址：中国·深圳·南山</p>
               </div>
              <div class="box" style="width: 2%;float: left;height:540px;"></div>
           </div>
       </div>

      <div class="box" style="width: 100%;height:30px;"></div>

       </div>
    </div>
    <!-----------------内页  end------------------>

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