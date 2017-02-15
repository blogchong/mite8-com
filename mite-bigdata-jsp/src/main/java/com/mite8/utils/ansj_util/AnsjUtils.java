package com.mite8.utils.ansj_util;

import com.mite8.utils.CollectionsSort;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: blogchong
 * Time:  2016/11/1.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  通用性工具
 */
public class AnsjUtils {

    //计算Top5个评论点赞数
    public static int countCommentsTop5(String comments) {

        int retValue = 0;
        List<Integer> list = new ArrayList<>();
        String[] comValus = comments.split(" ");
        for (String value : comValus){
            try{
                int valueTmp = Integer.parseInt(value);
                list.add(valueTmp);
            }catch (Exception e){
            }
        }

        //排序裁剪
        list = CollectionsSort.listSortAndCut(list, 5);
        for (Integer value : list) {
            retValue = retValue + value;
        }

        return retValue;
    }

    //平滑计算
    public static double countLn(int num, double max) {
        if (num <= max) {
            return Math.log((double) num + 1) / Math.log(max + 1);
        } else {
            return 1;
        }
    }

    //平滑计算
    public static double countLn(double num, double max) {
        if (num <= max) {
            return Math.log(num + 1) / Math.log(max + 1);
        } else {
            return 1;
        }
    }

}
