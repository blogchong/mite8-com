package com.mite8.utils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: blogchong
 * Time:  2016/8/24.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  清除特殊字符
 */
public class CleanStr {

    public static String cleanStr(String srcStr) {
        Pattern pattern = Pattern.compile(DefineOut.regEx);
        Matcher matcher = pattern.matcher(srcStr);
        return matcher.replaceAll("");
    }

}
