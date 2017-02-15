package com.mite8.Insight.movie_great_wall;

import java.util.regex.Pattern;

/**
 * Author: blogc
 * Time:  2016/12/17.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 电影工具类
 */
public class MovieUtils {

    public static String cookie = "ll=\"118282\"; bid=XYm0GA2naQE; _ga=GA1.2.1380766106.1481176812; ct=y; ps=y; dbcl2=\"155146471:GlybEtoKDrM\"; ck=pVvV; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1482028547%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DEsPtiqlbNYSKnPQgN55F7Xx82NHfVB2yC1RJyhrLujW%26wd%3D%26eqid%3Da18b6bcc0000debf000000065855f5fa%22%5D; __utmt=1; _vwo_uuid_v2=1EBE832098B2769E78BAD7AD6B2F62B2|69c57e8a9548f0a1ce18c3f22085ac72; ap=1; push_noty_num=0; push_doumail_num=0; _pk_id.100001.8cb4=05d864574cda383c.1481700927.5.1482028557.1481965771.; _pk_ses.100001.8cb4=*; __utma=30149280.1380766106.1481176812.1481969644.1482028548.13; __utmb=30149280.6.9.1482028556618; __utmc=30149280; __utmz=30149280.1482028548.13.7.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=30149280.15514";
    public static String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

    public static String jd_cookie = "unpl=V2_ZzNtbUJSQhd2ChFTf0paUmICEVpLU0ocJgtCUX8dWQxhCxUKclRCFXIURldnGl0UZwoZXEtcQx1FCHZXchBYAWcCGllyBBNNIEwHDCRSBUE3XHxcFVUWF3RaTwEoSVoAYwtBDkZUFBYhW0IAKElVVTUFR21yVEMldQl2VHMfVAVmBRtaQGdzEkU4dlFyHV8AYDMTbUNnAUEpAE5TeRFZSGcLFFVCVkUccgp2VUsa; __jdv=122270672|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_140323f65b7f40268588b2545548796f|1482228500466; _jrda=1; 3AB9D23F7A4B3C9B=LP5QVNVJCCT5AMQ7Q4SJZ3WGWROSJKJ5KB35VQXY74JFMDECI6FPOS25HNYKSNLRVZFUM66DQUYJXOXCNT6NGDWKRU; user-key=3c109945-7fe2-46c2-82db-34c4f4737579; cn=1; ipLoc-djd=1-72-2799-0; ipLocation=%u5317%u4EAC; areaId=1; __jda=122270672.1979107863.1481084647.1482228500.1482240694.4; __jdb=122270672.8.1979107863|4.1482240694; __jdc=122270672; __jdu=1979107863";

    //抽取链接
    public static Pattern patternKH = Pattern.compile("\\((\\d.+)\\)");

    //抽取链接
    public static Pattern patternScore = Pattern.compile("(.*)%");

    //抽取链接
    public static Pattern patternScoreCommentsAll = Pattern.compile("全部 \\((\\d+)\\)");

    //抽取链接
    public static Pattern patternScoreCommentsEach = Pattern.compile("星的评论 \\((\\d+)\\)");

    //抽取链接
    public static Pattern patternPage = Pattern.compile("data-total-page=\"(.*)\"");

    //抽取链接
    public static Pattern patternCID = Pattern.compile("/review/(\\d+)/");

    //抽取链接
    public static Pattern patternCY = Pattern.compile("用 (\\d+)");

    //抽取链接
    public static Pattern patternDP = Pattern.compile("看过\\((\\d+)\\)");

    //抽取链接
    public static Pattern patternDPCID = Pattern.compile("input value=\"(\\d+)\" type");

    //抽取链接
    public static Pattern patternDPL = Pattern.compile("class=\"allstar(\\d+) rating\" title=");


}
