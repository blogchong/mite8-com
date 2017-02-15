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
    <title>数据虫巢 - 政务舆情 - 江西·赣州·定南 - 财政数据分析</title>
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
            当前位置： <a href="/gov/addr_type?a_id=111">政务舆情</a>　>>　<a href="/gov/addr_type?a_id=111">江西·赣州·定南</a>　>>　<a href=/gov/jx_gz_dn/finance>财政统计分析</a>
        </div>

       <div class="box" style="width: 100%;height:8px;"></div>

       <div class="box mb25">
           <!----------国税/地税/政府财政部分------------->
           <div class = "box" style="width: 100%; height:400px">
                <div id="div_t_b_income" style="width: 100%;float: left;height:400px;"></div>
           </div>

           <div class="box" style="width: 100%;height:35px;"></div>

           <!----------上划中央/地方收入部分------------->
           <div class = "box" style="width: 100%; height:400px">
               <div id="div_t_c_income" style="width: 100%;float: left;height:400px;"></div>
           </div>

           <div class="box" style="width: 100%;height:35px;"></div>

          <!----------税收/非收税收入部分------------->
          <div class = "box" style="width: 100%; height:400px">
              <div id="div_t_s_income" style="width: 100%;float: left;height:400px;"></div>
          </div>

          <div class="box" style="width: 100%;height:35px;"></div>

            <!----------当月一般公共性预算支出&累积总收入------------->
            <div class = "box" style="width: 100%; height:350px">
               <div id="div_t_income" style="width: 48%;float: left;height:350px;"></div>
               <div style="width: 4%;float: left;height:350px;"></div>
               <div id="div_m_n_pay" style="width: 48%;float: left;height:350px;"></div>
            </div>

            <div class="box"  style="width: 100%;height:5px;"></div>

            <!----------当月财政总收入&当月一般公共性预算收入------------->
            <div class = "box" style="width: 100%; height:350px">
4                <div id="div_m_income" style="width: 48%;float: left;height:350px;"></div>
                <div style="width: 4%;float: left;height:350px;"></div>
                <div id="div_m_n_income" style="width: 48%;float: left;height:350px;"></div>
4           </div>

           <!----------累计一般公共预算支出&基金收入/支出------------->
           <div class = "box" style="width: 100%; height:350px">
               <div id="div_t_n_pay" style="width: 48%;float: left;height:350px;"></div>
               <div style="width: 4%;float: left;height:350px;"></div>
               <div id="div_t_j_income" style="width: 48%;float: left;height:350px;"></div>
          </div>
        </div>
       <div class="box"  style="width: 100%;height:30px;"></div>

    </div>
    <!-----------------内页  end------------------>

    <!---------t_j_income基金收入与支出脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_t_j_income'));
            var list_month  = eval('${data.p_month}');
            var list_t_j_income_2014 = eval('${data.list_t_j_income_2014}');
            var list_t_j_income_2015 = eval('${data.list_t_j_income_2015}');
            var list_t_j_income_2016 = eval('${data.list_t_j_income_2016}');
            var list_t_j_pay_2014 = eval('${data.list_t_j_pay_2014}');
            var list_t_j_pay_2015 = eval('${data.list_t_j_pay_2015}');
            var list_t_j_pay_2016 = eval('${data.list_t_j_pay_2016}');

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
                        subtext: ' --2014/2015/2016基金累计收入与支出，数据来自江西省定南县政府官网'
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
                        data: ['累计基金收入', '累计基金支出'],
                        top: 55
                    },
                    calculable : true,
                    grid: {
                        top: 80,
                        x: 80,
                        bottom: 80
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
                            name: '累计收入/支出（万元）'
                        }
                    ],
                    series: [
                        {
                            name: '累计基金收入',
                            type: 'line',
                            areaStyle: {normal: {}},
                        },
                        {
                            name: '累计基金支出',
                            type: 'line',
                            areaStyle: {normal: {}},
                        }
                    ]
                },
                options: [
                    {
                        title: {text: '2014年基金累计收入与支出'},
                        series: [
                            {data: list_t_j_income_2014},
                            {data: list_t_j_pay_2014}
                        ]
                    },
                    {
                        title : {text: '2015年基金累计收入与支出'},
                        series : [
                            {data: list_t_j_income_2015},
                            {data: list_t_j_pay_2015}
                        ]
                    },
                    {
                        title : {text: '2016年基金累计收入与支出'},
                        series : [
                            {data: list_t_j_income_2016},
                            {data: list_t_j_pay_2016}
                        ]
                    }
                ]
            };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

    <!---------t_s_income是否税收累积收入脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_t_s_income'));
            var list_month  = eval('${data.p_month}');
            var list_t_income_2014 = eval('${data.list_t_income_2014}');
            var list_t_income_2015 = eval('${data.list_t_income_2015}');
            var list_t_income_2016 = eval('${data.list_t_income_2016}');
            var list_t_s_y_income_2014 = eval('${data.list_t_s_y_income_2014}');
            var list_t_s_y_income_2015 = eval('${data.list_t_s_y_income_2015}');
            var list_t_s_y_income_2016 = eval('${data.list_t_s_y_income_2016}');
            var list_t_s_n_income_2014 = eval('${data.list_t_s_n_income_2014}');
            var list_t_s_n_income_2015 = eval('${data.list_t_s_n_income_2015}');
            var list_t_s_n_income_2016 = eval('${data.list_t_s_n_income_2016}');

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
                        subtext: ' --2014/2015/2016分税收与否收入，数据来自江西省定南县政府官网'
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
                        data: ['总收入','税收收入', '非税收收入'],
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
                        {name: '总收入', type: 'line'},
                        {name: '税收收入', type: 'bar'},
                        {name: '非税收收入', type: 'bar'},
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
                        title: {text: '2014年累计分税收与否收入'},
                        series: [
                            {data: list_t_income_2014},
                            {data: list_t_s_y_income_2014},
                            {data: list_t_s_n_income_2014},
                            {data: [
                                {name: '税收收入', value: ${data.t_s_y_income_2014_max}},
                                {name: '非税收收入', value: ${data.t_s_n_income_2014_max}}
                            ]}
                        ]
                    },
                    {
                        title : {text: '2015年累计分税收与否收入'},
                        series : [
                            {data: list_t_income_2015},
                            {data: list_t_s_y_income_2015},
                            {data: list_t_s_n_income_2015},
                            {data: [
                                {name: '税收收入', value: ${data.t_s_y_income_2015_max}},
                                {name: '非税收收入', value: ${data.t_s_n_income_2015_max}}
                            ]}
                        ]
                    },
                    {
                        title : {text: '2016年累计分税收与否收入'},
                        series : [
                            {data: list_t_income_2016},
                            {data: list_t_s_y_income_2016},
                            {data: list_t_s_n_income_2016},
                            {data: [
                                {name: '税收收入', value: ${data.t_s_y_income_2016_max}},
                                {name: '非税收收入', value: ${data.t_s_n_income_2016_max}}
                            ]}
                        ]
                    }
                ]
            };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

    <!---------t_c_income分层次累积收入脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_t_c_income'));
            var list_month  = eval('${data.p_month}');
            var list_t_income_2014 = eval('${data.list_t_income_2014}');
            var list_t_income_2015 = eval('${data.list_t_income_2015}');
            var list_t_income_2016 = eval('${data.list_t_income_2016}');
            var list_t_c_z_income_2014 = eval('${data.list_t_c_z_income_2014}');
            var list_t_c_z_income_2015 = eval('${data.list_t_c_z_income_2015}');
            var list_t_c_z_income_2016 = eval('${data.list_t_c_z_income_2016}');
            var list_t_c_d_income_2014 = eval('${data.list_t_c_d_income_2014}');
            var list_t_c_d_income_2015 = eval('${data.list_t_c_d_income_2015}');
            var list_t_c_d_income_2016 = eval('${data.list_t_c_d_income_2016}');

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
                        subtext: ' --2014/2015/2016分层次收入，数据来自江西省定南县政府官网'
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
                        data: ['总收入','上划中央收入', '地方收入'],
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
                        {name: '总收入', type: 'line'},
                        {name: '上划中央收入', type: 'line'},
                        {name: '地方收入', type: 'line'},
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
                        title: {text: '2014年累计分层次收入'},
                        series: [
                            {data: list_t_income_2014},
                            {data: list_t_c_z_income_2014},
                            {data: list_t_c_d_income_2014},
                            {data: [
                                {name: '上划中央收入', value: ${data.t_c_z_income_2014_max}},
                                {name: '地方收入', value: ${data.t_c_d_income_2014_max}}
                            ]}
                        ]
                    },
                    {
                        title : {text: '2015年累计分层次收入'},
                        series : [
                            {data: list_t_income_2015},
                            {data: list_t_c_z_income_2015},
                            {data: list_t_c_d_income_2015},
                            {data: [
                                {name: '上划中央收入', value: ${data.t_c_z_income_2015_max}},
                                {name: '地方收入', value: ${data.t_c_d_income_2015_max}}
                            ]}
                        ]
                    },
                    {
                        title : {text: '2016年累计分层次收入'},
                        series : [
                            {data: list_t_income_2016},
                            {data: list_t_c_z_income_2016},
                            {data: list_t_c_d_income_2016},
                            {data: [
                                {name: '上划中央收入', value: ${data.t_c_z_income_2016_max}},
                                {name: '地方收入', value: ${data.t_c_d_income_2016_max}}
                            ]}
                        ]
                    }
                ]
            };

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
                        subtext: ' --2014/2015/2016分部门收入，数据来自江西省定南县政府官网'
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

    <!---------t_income累积总收入脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_t_income'));
            var list_month  = eval('${data.p_month}');
            var list_2014 = eval('${data.list_t_income_2014}');
            var list_2015 = eval('${data.list_t_income_2015}');
            var list_2016 = eval('${data.list_t_income_2016}');
            // 指定图表的配置项和数据
            option = {
                title: {
                    text: '累计总收入变化趋势',
                    subtext: ' -- 对比2014/2015/2016三年累计总收入'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['2014年','2015年','2016年'],
                    padding: 60
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
                        data : list_month
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        name : '累计总收入(万元)'
                    }
                ],
                series : [
                    {
                        name:'2014年',
                        type:'line',
                        areaStyle: {normal: {}},
                        data:list_2014
                    },
                    {
                        name:'2015年',
                        type:'line',
                        areaStyle: {normal: {}},
                        data:list_2015
                    },
                    {
                        name:'2016年',
                        type:'line',
                        areaStyle: {normal: {}},
                        data:list_2016
                    }
                ]
            };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>

    <!---------m_income当月总收入脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_m_income'));
            var list_month  = eval('${data.p_month}');
            var list_m_income_2014 = eval('${data.list_m_income_2014}');
            var list_m_income_2015 = eval('${data.list_m_income_2015}');
            var list_m_income_2016 = eval('${data.list_m_income_2016}');
            // 指定图表的配置项和数据
           option = {
               title : {
                   text: '单月总收入变化趋势',
                   subtext: ' -- 对比2014/2015/2016三年每月总收入'
               },
               tooltip : {
                   trigger: 'axis'
               },
               legend: {
                   data:['2014年','2015年','2016年'],
                   padding: 60
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
               grid : {
                   top: 80,
                   right: 55
               },
               xAxis : [
                   {
                       type : 'category',
                       boundaryGap : false,
                       data : list_month
                   }
               ],
               yAxis : [
                   {
                       type : 'value',
                       name: '月总收入(万元)',
                       axisLabel : {
                           formatter: '{value}'
                       }
                   }
               ],
               series : [
                   {
                       name:'2014年',
                       type:'line',
                       data:list_m_income_2014,
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
                   },
                   {
                       name:'2015年',
                       type:'line',
                       data:list_m_income_2015,
                       markPoint : {
                           data : [
                               {type : 'max', name: '最大值'},
                               {type : 'min', name: '最小值'}
                           ]
                       },
                       markLine : {
                           data : [
                               {type : 'average', name : '平均值'}
                           ]
                       }
                   },
                   {
                      name:'2016年',
                      type:'line',
                      data:list_m_income_2016,
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

    <!---------m_n_income当月一般性公共预算收入脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_m_n_income'));
            var list_month  = eval('${data.p_month}');
            var list_m_n_income_2014 = eval('${data.list_m_n_income_2014}');
            var list_m_n_income_2015 = eval('${data.list_m_n_income_2015}');
            var list_m_n_income_2016 = eval('${data.list_m_n_income_2016}');
            // 指定图表的配置项和数据
           option = {
               title : {
                   text: '单月一般性公共预算收入变化趋势',
                   subtext: ' -- 对比2014/2015/2016三年每月一般性公共预算收入'
               },
               tooltip : {
                   trigger: 'axis'
               },
               legend: {
                   data:['2014年','2015年','2016年'],
                   padding: 60
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
               grid : {
                   top: 80,
                   right: 55
               },
               xAxis : [
                   {
                       type : 'category',
                       boundaryGap : false,
                       data : list_month
                   }
               ],
               yAxis : [
                   {
                       type : 'value',
                       name: '月公共收入(万元)',
                       axisLabel : {
                           formatter: '{value}'
                       }
                   }
               ],
               series : [
                   {
                       name:'2014年',
                       type:'bar',
                       data:list_m_n_income_2014,
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
                   },
                   {
                       name:'2015年',
                       type:'bar',
                       data:list_m_n_income_2015,
                       markPoint : {
                           data : [
                               {type : 'max', name: '最大值'},
                               {type : 'min', name: '最小值'}
                           ]
                       },
                       markLine : {
                           data : [
                               {type : 'average', name : '平均值'}
                           ]
                       }
                   },
                   {
                      name:'2016年',
                      type:'bar',
                      data:list_m_n_income_2016,
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

    <!---------m_n_pay当月一般性公共预算支出脚本------------>
    <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('div_m_n_pay'));
            var list_month  = eval('${data.p_month}');
            var list_2014 = eval('${data.list_m_n_pay_2014}');
            var list_2015 = eval('${data.list_m_n_pay_2015}');
            var list_2016 = eval('${data.list_m_n_pay_2016}');
            // 指定图表的配置项和数据
           option = {
               title : {
                   text: '单月一般性公共预算支出变化趋势',
                   subtext: ' -- 对比2014/2015/2016三年每月一般性公共预算支出'
               },
               tooltip : {
                   trigger: 'axis'
               },
               legend: {
                   data:['2014年','2015年','2016年'],
                   padding: 60
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
               grid : {
                   top: 80,
                   right: 57
               },
               xAxis : [
                   {
                       type : 'category',
                       boundaryGap : false,
                       data : list_month
                   }
               ],
               yAxis : [
                   {
                       type : 'value',
                       name: '月公共支出(万元)',
                       axisLabel : {
                           formatter: '{value}'
                       }
                   }
               ],
               series : [
                   {
                       name:'2014年',
                       type:'line',
                       data:list_2014,
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
                   },
                   {
                       name:'2015年',
                       type:'line',
                       data:list_2015,
                       markPoint : {
                           data : [
                               {type : 'max', name: '最大值'},
                               {type : 'min', name: '最小值'}
                           ]
                       },
                       markLine : {
                           data : [
                               {type : 'average', name : '平均值'}
                           ]
                       }
                   },
                   {
                      name:'2016年',
                      type:'line',
                      data:list_2016,
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

    <!---------t_t_n_pay累计一般公共预算支出------------>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('div_t_n_pay'));

        var list_month  = eval('${data.p_month}');
        var list_2014 = eval('${data.list_t_n_pay_2014}');
        var list_2015 = eval('${data.list_t_n_pay_2015}');
        var list_2016 = eval('${data.list_t_n_pay_2016}');

       option = {
           title: {
                   text: '累积一般性公共预算支出变化趋势',
                   subtext: ' -- 对比2014/2015/2016三年累积一般性公共预算支出'
           },
           legend: {
               data: ['2014年', '2015年', '2016年'],
               align: 'left',
               padding: 60
           },
           grid : {
              top: 80,
              left: 60
           },
           toolbox: {
               // y: 'bottom',
               feature: {
                   restore : {show: true},
                   dataView: {},
                   saveAsImage: {
                       pixelRatio: 2
                   }
               }
           },
           tooltip: {},
           xAxis: {
               data: list_month,
               silent: false,
               splitLine: {
                   show: false
               }
           },
           yAxis: {
           },
           series: [{
               name: '2014年',
               type: 'bar',
               stack: 'one',
               data: list_2014,
               animationDelay: function (idx) {
                   return idx * 10;
               }
           }, {
               name: '2015年',
               type: 'bar',
               stack: 'one',
               data: list_2015,
               animationDelay: function (idx) {
                   return idx * 10 + 100;
               }
           }, {
               name: '2016年',
               type: 'bar',
               stack: 'one',
               data: list_2016,
               animationDelay: function (idx) {
                   return idx * 10 + 100;
               }
           }],
           animationEasing: 'elasticOut',
           animationDelayUpdate: function (idx) {
               return idx * 5;
           }
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