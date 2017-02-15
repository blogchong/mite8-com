package com.mite8.utils;

import java.text.DecimalFormat;

/**
 * Author: blogchong
 * Time:  2016/10/24.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  保留指定位数的double值
 */
public class CutDoubleValue {
    public static double cutDoubleValue(Double value, int num) {
        String dfStr = "#.";
        if (num <= 0) {
            return value;
        } else {
            for (int i = 0; i < num; i++){
                dfStr = dfStr + "#";
            }
            DecimalFormat df = new DecimalFormat(dfStr);
            try {
                return Double.parseDouble(df.format(value));
            } catch (Exception e) {}
        }
        return value;
    }

//    public static void main(String[] args) {
//        System.out.println(cutDoubleValue(1.44566,4));
//    }

}
