package com.mite8.service;

import com.mite8.utils.CollectionsSort;
import com.mite8.utils.mite_restful.MiteGovUtils;
import net.sf.json.JSONArray;
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
 * Desc:  行业数据报告
 */
@Service
public class BigdataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public JSONObject bigData() {
        JSONObject jsonObject = new JSONObject();

        //获取均价
        String queryAvgPay = "SELECT truncate(sum(work_income_avg)/count(1),0) as value FROM mite_position_final WHERE work_income_avg != 0;";
        List<Integer> listAvgPay = jdbcTemplate.query(queryAvgPay, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("value");
            }
        });
        int avg_pay = 0;
        if (listAvgPay.size() >= 1) {
            avg_pay = listAvgPay.get(0);
        }
        jsonObject.put("avg_pay", avg_pay);

        //取地域需求
        String queryCity = "SELECT work_place_city as name,count(1) as value FROM mite_position_final WHERE work_place_city != \"其他\" GROUP BY work_place_city ORDER BY value desc;";
        List<JSONObject> listCity = jdbcTemplate.query(queryCity, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listCity", MiteGovUtils.cutListAndMergeOther(listCity,15,0));

        //取学历需求
        String queryEdu = "SELECT edu as name, count(1) as value FROM mite_position_result GROUP BY edu;";
        List<JSONObject> listEdu = jdbcTemplate.query(queryEdu, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listEdu",listEdu);

        //福利待遇
        String queryDy = "SELECT company_welfare_tag as name, count(1) as value FROM mite_company_welfare_tag GROUP BY company_welfare_tag ORDER BY value desc;";
        List<JSONObject> listDy = jdbcTemplate.query(queryDy, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listDy",MiteGovUtils.cutListAndMergeOther(listDy, 25, 0));

        //收入
        String queryIncome = "SELECT income as name, count(1) as value FROM mite_position_result GROUP BY income ORDER BY name;";
        List<JSONObject> listIncome = jdbcTemplate.query(queryIncome, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        List<JSONObject> listIn = new ArrayList<>(10);
        for(int i=0; i<10; i++){
            listIn.add(new JSONObject());
        }
        for (JSONObject jsonObject1: listIncome){
            String name = jsonObject1.getString("name");
            if (name.equals("0-5K")){
                listIn.set(0, jsonObject1);
            } else if (name.equals("5K-10K")){
                listIn.set(1, jsonObject1);
            }else if (name.equals("10K-15K")){
                listIn.set(2, jsonObject1);
            }else if (name.equals("15K-20K")){
                listIn.set(3, jsonObject1);
            }else if (name.equals("20K-25K")){
                listIn.set(4, jsonObject1);
            }else if (name.equals("25K-30K")){
                listIn.set(5, jsonObject1);
            }else if (name.equals("30K-35K")){
                listIn.set(6, jsonObject1);
            }else if (name.equals("35K-40K")){
                listIn.set(7, jsonObject1);
            }else if (name.equals("40K及以上")){
                listIn.set(8, jsonObject1);
            }else if (name.equals("面议")){
                listIn.set(9, jsonObject1);
            }
        }
        jsonObject.put("listIn", CollectionsSort.listSortF(listIn));

        //经验
        String queryExp = "SELECT exp as name, count(1) as value FROM mite_position_result GROUP BY exp ORDER BY value DESC;";
        List<JSONObject> listExp = jdbcTemplate.query(queryExp, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listExp", listExp);

        //职业方向
        String queryTech = "SELECT tech as name, count(1) as value FROM mite_position_result GROUP BY tech ORDER BY value DESC;";
        List<JSONObject> listTech = jdbcTemplate.query(queryTech, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listTech", listTech);

        //规模
        String queryScale = "SELECT scale as name, count(1) as value FROM mite_position_result GROUP BY scale ORDER BY value DESC;";
        List<JSONObject> listScaleTmp = jdbcTemplate.query(queryScale, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        List<JSONObject> listScale = new ArrayList<>(6);
        for(int i=0; i<6; i++){
            listScale.add(new JSONObject());
        }
        for (JSONObject jsonObject1: listScaleTmp){
            String name = jsonObject1.getString("name");
            if (name.equals("未知")){
                listScale.set(0, jsonObject1);
            }else if (name.equals("0-100人")){
                listScale.set(1, jsonObject1);
            }else if (name.equals("100-300人")){
                listScale.set(2, jsonObject1);
            }else if (name.equals("300-1000")){
                JSONObject jsonObject11 = new JSONObject();
                jsonObject11.put("name", "300-1000人");
                jsonObject11.put("value", jsonObject1.get("value"));
                listScale.set(3, jsonObject11);
            }else if (name.equals("1000-10000人")){
                listScale.set(4, jsonObject1);
            }else if (name.equals("10000人及以上")){
                listScale.set(5, jsonObject1);
            }
        }
        jsonObject.put("listScale", CollectionsSort.listSortF(listScale));

        return jsonObject;
    }

}
