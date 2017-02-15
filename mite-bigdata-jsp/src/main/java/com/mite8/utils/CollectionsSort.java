package com.mite8.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: blogchong
 * Time:  2016/10/22.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  集合排序工具
 */
public class CollectionsSort {

    //JSONArray排序- 冒泡排序
    public static JSONArray jsonArraySort(JSONArray jsonArray) {
        int iDataNum = jsonArray.size();
        for (int i = 0; i < iDataNum - 1; i++) {                      //必须进行N-1次的比较
            for (int j = 0 ; j < iDataNum - 1 - i; j++) {   //iDataNum - 1 - i之后的元素已经有序

                JSONObject jsonObjectJ = jsonArray.getJSONObject(j);
                JSONObject jsonObjectJ1 = jsonArray.getJSONObject(j+1);
                int numJ = jsonObjectJ.getInt("topic_num");
                int numJ1 = jsonObjectJ1.getInt("topic_num");

                if (numJ < numJ1) {   //相邻两数进行比较，若前大后小则进行交换
                    jsonArray.set(j, jsonObjectJ1);
                    jsonArray.set(j+1, jsonObjectJ);
                }
            }
        }
        return jsonArray;
    }

    //List排序
    public static List<Integer> listSort(List<Integer> list) {
        int iDataNum = list.size();
        for (int i = 0; i < iDataNum - 1; i++) {                      //必须进行N-1次的比较
            for (int j = 0 ; j < iDataNum - 1 - i; j++) {   //iDataNum - 1 - i之后的元素已经有序

                if (list.get(j) < list.get(j+1)) {   //相邻两数进行比较，若前大后小则进行交换
                    int tmp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, tmp);
                }
            }
        }
        return list;
    }

    //倒序
    public static List<JSONObject> listSortF(List<JSONObject> list) {

        int iDataNum = list.size();
        for (int j = 0; j < (iDataNum - 1)/2; j++) {                      //必须进行N-1次的比较

            JSONObject jsonObjectJ = list.get(j);
            JSONObject jsonObjectJ1 = list.get(iDataNum - 1 - j);
            list.set(j, jsonObjectJ1);
            list.set(iDataNum - 1 - j, jsonObjectJ);
        }
        return list;

    }

    //列表排序以及裁剪
    public static List<Integer> listSortAndCut(List<Integer> list, int num) {
        list = listSort(list);
        int count = list.size();
        if (count > num){
            count = num;
        }
        List<Integer> listRet = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            listRet.add(list.get(i));
        }
        return listRet;
    }

    //针对于KOL列表的排序
    public static List<JSONObject> listSortKolList(List<JSONObject> list) {
        int iDataNum = list.size();
        for (int i = 0; i < iDataNum - 1; i++) {                      //必须进行N-1次的比较
            for (int j = 0 ; j < iDataNum - 1 - i; j++) {   //iDataNum - 1 - i之后的元素已经有序
                JSONObject jsonObjectJ = list.get(j);
                int topicNumJ = jsonObjectJ.getInt("topic_num");
                double kqiJ = jsonObjectJ.getDouble("kqi");

                long timeJ = 0;
                long timeJ1 = 0;

                JSONObject jsonObjectJ1 = list.get(j+1);
                int topicNumJ1 = jsonObjectJ1.getInt("topic_num");
                double kqiJ1 = jsonObjectJ1.getDouble("kqi");

                try {
                    timeJ = TransferTime.stringToLong(jsonObjectJ.getString("update_time"), DefineOut.timeFormat)/1000;
                    timeJ1 = TransferTime.stringToLong(jsonObjectJ1.getString("update_time"), DefineOut.timeFormat)/1000;
                }catch (Exception e) {}

                if (timeJ < timeJ1 || (timeJ == timeJ1 && topicNumJ < topicNumJ1) || (timeJ == timeJ1 && topicNumJ == topicNumJ1 && kqiJ < kqiJ1)) {   //相邻两数进行比较，若前大后小则进行交换
                    list.set(j, jsonObjectJ1);
                    list.set(j+1, jsonObjectJ);
                }
            }
        }
        return list;
    }

}
