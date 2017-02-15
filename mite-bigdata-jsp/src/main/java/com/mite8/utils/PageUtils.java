package com.mite8.utils;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: blogchong
 * Created: 2016/8/24
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Des: 分页工具
 */
public class PageUtils {
    public static List getPageList(int page,int size,List list){
        List pageList = new ArrayList<>();
        if(page >= 1){
            for(int i= (page -1) * size;i< page * size && i< list.size();i++){
                pageList.add(list.get(i));
            }
        }
        return  pageList;
    }

    //结果分页
    public static JSONArray pageOpt(JSONArray jsonArray, int page , int size) {
        JSONArray jsonArray1 = new JSONArray();
        if(page > 0) {
            for (int i = (page - 1) * size; i < page * size && i < jsonArray.size(); i++) {
                jsonArray1.add(jsonArray.get(i));
            }
        }
        return jsonArray1;
    }
}
