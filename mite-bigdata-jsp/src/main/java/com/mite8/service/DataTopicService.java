package com.mite8.service;

import com.mite8.entity.DataTopicEntity;
import com.mite8.utils.DefineOut;
import com.mite8.utils.PageUtils;
import com.mite8.utils.TransferTime;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: blogchong
 * Time:  2016/12/7.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  data_topic服务
 */
@Service
public class DataTopicService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JSONObject dataTopicService(int size, int page, int type){

        if (page<0){
            page=1;
        }

        JSONObject jsonObject = new JSONObject();

        //福利待遇
        String query = "SELECT p_time,t_title,t_desc,t_url,t_type FROM mite_wechat_topics;";
        List<JSONObject> list = jdbcTemplate.query(query, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("p_time",resultSet.getString("p_time").trim());
                try {
                    jsonObject1.put("p_time2", TransferTime.stringToLong(resultSet.getString("p_time").trim(), DefineOut.TIME_FORMAT));
                } catch (Exception e){
                    jsonObject1.put("p_time2", 0);
                }
                jsonObject1.put("t_title",resultSet.getString("t_title"));
                jsonObject1.put("t_desc",resultSet.getString("t_desc"));
                jsonObject1.put("t_url",resultSet.getString("t_url"));
                jsonObject1.put("t_type",resultSet.getInt("t_type"));
                return jsonObject1;
            }
        });

        //主体部分
        List<JSONObject> listMain = new ArrayList<>();

        List<JSONObject> listType1 = new ArrayList<>();
        List<JSONObject> listType2 = new ArrayList<>();
        List<JSONObject> listType3 = new ArrayList<>();
        List<JSONObject> listType4 = new ArrayList<>();

        for(JSONObject jsonObject1: list){
            listMain.add(jsonObject1);
            int t_type = jsonObject1.getInt("t_type");
            if (t_type == 1) {
                listType1.add(jsonObject1);
            } else if (t_type == 2) {
                listType2.add(jsonObject1);
            } else if (t_type == 3) {
                listType3.add(jsonObject1);
            } else if (t_type == 4){
                listType4.add(jsonObject1);
            }
        }

        //进行排序
        if (type == 1) {
            listMain = sortListJson(listType1);
        } else if (type == 2) {
            listMain = sortListJson(listType2);
        } else if (type == 3) {
            listMain = sortListJson(listType3);
        } else if (type == 4) {
            listMain = sortListJson(listType4);
        } else {
            listMain = sortListJson(listMain);
        }
        int num = 4;
        listType1 = listSortAndCut(listType1, num);
        listType2 = listSortAndCut(listType2, num);
        listType3 = listSortAndCut(listType3, num);
        listType4 = listSortAndCut(listType4, num);

        //对page进行控制
        int up_flag = 0;
        int down_flag =1;

        if (page > 1){
            up_flag = 1;

            if ((listMain.size()%size)==0 && page > listMain.size()/size){
                page = listMain.size()/size;
            } else if ((listMain.size()%size)!=0 && page > (listMain.size()/size + 1)){
                page = listMain.size()/size + 1;
                down_flag = 0;
            }
        }

        if (page == 1){
            up_flag = 0;
        }else if (((listMain.size()%size)==0 && page == listMain.size()/size) || (listMain.size()%size)!=0 && page == (listMain.size()/size + 1)){
            down_flag = 0;
        }

        listMain = PageUtils.getPageList(page, size, listMain);

        jsonObject.put("list_main", listMain);
        jsonObject.put("list_type1", listType1);
        jsonObject.put("list_type2", listType2);
        jsonObject.put("list_type3", listType3);
        jsonObject.put("list_type4", listType4);
        jsonObject.put("up_flag", up_flag);
        jsonObject.put("down_flag",down_flag);
        jsonObject.put("type", type);
        jsonObject.put("page", page);

        return jsonObject;
    }

    //排序
    public static List<JSONObject> sortListJson(List<JSONObject> list){
        int iDataNum = list.size();
        for (int i = 0; i < iDataNum - 1; i++) {                      //必须进行N-1次的比较
            for (int j = 0 ; j < iDataNum - 1 - i; j++) {   //iDataNum - 1 - i之后的元素已经有序

                JSONObject jsonObjectJ = list.get(j);
                JSONObject jsonObjectJ1 = list.get(j + 1);
                long numJ = jsonObjectJ.getLong("p_time2");
                long numJ1 = jsonObjectJ1.getLong("p_time2");

                if (numJ < numJ1) {   //相邻两数进行比较，若前大后小则进行交换
                    list.set(j, jsonObjectJ1);
                    list.set(j+1, jsonObjectJ);
                }
            }
        }
        return list;
    }

    //列表排序以及裁剪
    public static List<JSONObject> listSortAndCut(List<JSONObject> list, int num) {
        list = sortListJson(list);
        int count = list.size();
        if (count > num){
            count = num;
        }
        List<JSONObject> listRet = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            listRet.add(list.get(i));
        }
        return listRet;
    }

}
