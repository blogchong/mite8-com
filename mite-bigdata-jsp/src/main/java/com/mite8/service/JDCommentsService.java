package com.mite8.service;

import com.mite8.utils.CollectionsSort;
import com.mite8.utils.mite_restful.MiteGovUtils;
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
 * Time:  2016/12/5.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  行业-JD数据报告
 */
@Service
public class JDCommentsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public JSONObject jdComments() {
        JSONObject jsonObject = new JSONObject();

        //获取地域
        String queryArea = "SELECT * FROM insight_jd_comments_r_area;";
        List<JSONObject> listArea = jdbcTemplate.query(queryArea, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });

        List<JSONObject> listArea2 = new ArrayList<>();
        int count = 0;
        for (JSONObject jsonObject1: listArea){
            if (count<15){
                listArea2.add(jsonObject1);
                count++;
            }
        }
        jsonObject.put("area2", listArea2);

        //获取月份
        String queryMonth = "SELECT * FROM insight_jd_comments_r_month;";
        List<JSONObject> listMonth = jdbcTemplate.query(queryMonth, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });

        //获取时段
        String queryHours = "SELECT * FROM insight_jd_comments_r_hours;";
        List<JSONObject> listHours = jdbcTemplate.query(queryHours, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });


        //获取BJ-天
        String queryDay = "SELECT * FROM insight_jd_comments_r_day2016bj;";
        List<JSONObject> listDay = jdbcTemplate.query(queryDay, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });

        jsonObject.put("area", listArea);
        jsonObject.put("month", CollectionsSort.listSortF(listMonth));
        jsonObject.put("hours", CollectionsSort.listSortF(listHours));
        jsonObject.put("day", CollectionsSort.listSortF(listDay));

        return jsonObject;
    }

}
