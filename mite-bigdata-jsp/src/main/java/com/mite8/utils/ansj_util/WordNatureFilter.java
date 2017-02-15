package com.mite8.utils.ansj_util;

import org.ansj.recognition.impl.FilterRecognition;

/**
 * Author: blogchong
 * Time:  2016/10/10.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  组合原生的词性过滤器
 */
public class WordNatureFilter {
    public static FilterRecognition wordNatureFilter(FilterRecognition fitler) {
        //卡w标点符号
        fitler.insertStopNatures("w");
        //卡r代词
        fitler.insertStopNatures("r");
        //卡m数词
        fitler.insertStopNatures("m");
        //卡q量词
        fitler.insertStopNatures("q");
        //卡d副词
        fitler.insertStopNatures("d");
        //卡p介词
        fitler.insertStopNatures("p");
        //卡c连词
        fitler.insertStopNatures("c");
        //卡u助词
        fitler.insertStopNatures("u");
        //卡e叹词
        fitler.insertStopNatures("e");
        //卡y语气词
        fitler.insertStopNatures("y");
        //卡o拟声词
        fitler.insertStopNatures("o");
        //卡s处所词
        fitler.insertStopNatures("s");
        //卡l习用语
        fitler.insertStopNatures("l");
        //卡i成语
        fitler.insertStopNatures("i");
        //卡v成语
        fitler.insertStopNatures("v");
        //卡f方位词
        fitler.insertStopNatures("f");
        //卡t时间词
        fitler.insertStopNatures("t");

        return fitler;
    }
}
