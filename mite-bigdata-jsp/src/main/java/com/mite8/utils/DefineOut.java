package com.mite8.utils;

/**
 * Author: blogchong
 * Time:  2016/8/24.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  宏定义
 */
public class DefineOut {

    //文本清理模板
    public static final String regEx = "[`~!@#$%^&*()+《❥》=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; //特殊字符集合

    //专题的文档总数
    public static final int TopicNum = 1102;

    //四中类型字典标志
    public static final String DIC_FLAG_SEG = "seg";
    public static final String DIC_FLAG_AMBIG = "ambig";
    public static final String DIC_FLAG_STOP = "stop";
    public static final String DIC_FLAG_SYNON = "synon";
    public static final String DIC_FLAG_SPE = "spe";
    public static final String DIC_FLAG_EMOTION = "emotion";

    //默认的字典
    public static final String DEFAULT_DIC = "default";

    //字典配置
    public static final String dic_ambig = "dic.ambigc";
    public static final String dic_seg = "dic.seg";
    public static final String dic_stop = "dic.stop";
    public static final String dic_synon = "dic.synon";
    public static final String dic_spe = "dic.spe";
    public static final String dic_emotion = "dic.emotion";

    public final static String timeFormat = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_FORMAT = "yyy-MM-dd";

    //一天的秒数
    public final static int DAY_S = 86400;
}
