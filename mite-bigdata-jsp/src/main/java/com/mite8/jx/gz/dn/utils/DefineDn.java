package com.mite8.jx.gz.dn.utils;

import java.util.regex.Pattern;

/**
 * Author: blogchong
 * Time:  2016/11/25.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 宏定义-DN
 */
public class DefineDn {

    //抽取链接
    public static Pattern patternUrl = Pattern.compile("href=\"(.+?)\"");

    //解析问政列表个数
    public static Pattern patternPoliticsNum = Pattern.compile("createPageHTML\\((\\d*),");

    //解析口碑列表个数
    public static Pattern patternPraiseNum = Pattern.compile("找到相关新闻约(\\d*)个");

    //解析简历的名字，性别，年龄
    public static Pattern patternResumeNameXN = Pattern.compile("(.+?)（(.)，(\\d*)岁）");

    //解析简历列表，最大页码
    public static Pattern getPatternResumeMaxNum = Pattern.compile("/post/qiuzhi/pn(\\d*)/");

    //解析招聘关注人数
    public static Pattern getPatternHireCareNum = Pattern.compile("信息关注度：(\\d*)人次");


}
