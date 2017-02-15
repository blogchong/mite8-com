package com.mite8.utils.mite_restful;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: blogchong
 * Time:  2016/12/2.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  对应接口的部分工具类
 */
public class MiteGovUtils {

    //List进行裁剪，并且合并最后部分, type = 1合并，=0不合并，只是裁剪
    public static List<JSONObject> cutListAndMergeOther(List<JSONObject> list,int num, int type) {

        if (list.size() < num) {
            return list;
        } else {
            List<JSONObject> listRe = new ArrayList<>();
            int count = 0;
            if (type == 0) {
                for (JSONObject jsonObject : list) {
                    if (count < num){
                        listRe.add(jsonObject);
                        count++;
                    } else {
                        break;
                    }
                }
            } else {
                int otherNum = 0;
                for (JSONObject jsonObject: list){
                    if(count < num){
                        listRe.add(jsonObject);
                        count++;
                    } else {
                        otherNum += jsonObject.getInt("value");
                    }
                }
                if (otherNum > 0) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", "其他");
                    jsonObject.put("value", otherNum + "");
                    listRe.add(jsonObject);
                }
            }
            return listRe;
        }
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
}
