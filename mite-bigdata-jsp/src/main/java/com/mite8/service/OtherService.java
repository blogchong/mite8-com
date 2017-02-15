package com.mite8.service;

import com.mite8.entity.GovEmotionEntity;
import com.mite8.entity.GovFinanceEntity;
import com.mite8.utils.CollectionsSort;
import com.mite8.utils.MapSort;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: blogchong
 * Time:  2016/12/2.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  首页+about
 */
@Service
public class OtherService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //首页
    public JSONObject getIndex() {

        JSONObject jsonObject = new JSONObject();

        //国税
        String query = "SELECT p_month,t_income,t_b_g_income,t_b_d_income,t_b_c_income FROM jx_gz_dn_finance ORDER BY p_month;";
        List<GovFinanceEntity> list = jdbcTemplate.query(query, new RowMapper<GovFinanceEntity>() {
            @Override
            public GovFinanceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                GovFinanceEntity govFinanceEntity = new GovFinanceEntity();
                govFinanceEntity.setpMoth(resultSet.getString("p_month"));
                govFinanceEntity.settIncome(resultSet.getDouble("t_income"));
                govFinanceEntity.settBGIncome(resultSet.getDouble("t_b_g_income"));
                govFinanceEntity.settBDIncome(resultSet.getDouble("t_b_d_income"));
                govFinanceEntity.settBCIncome(resultSet.getDouble("t_b_c_income"));
                return govFinanceEntity;
            }
        });

        //月份维度
        List<String> listMonth = new ArrayList<>();

        //t_income
        List<Double> list_t_income_2014 = new ArrayList<>();
        List<Double> list_t_income_2015 = new ArrayList<>();
        List<Double> list_t_income_2016 = new ArrayList<>();

        //t_b_g_income
        List<Double> list_t_b_g_income_2014 = new ArrayList<>();
        List<Double> list_t_b_g_income_2015 = new ArrayList<>();
        List<Double> list_t_b_g_income_2016 = new ArrayList<>();

        //t_b_d_income
        List<Double> list_t_b_d_income_2014 = new ArrayList<>();
        List<Double> list_t_b_d_income_2015 = new ArrayList<>();
        List<Double> list_t_b_d_income_2016 = new ArrayList<>();

        //t_b_c_income
        List<Double> list_t_b_c_income_2014 = new ArrayList<>();
        List<Double> list_t_b_c_income_2015 = new ArrayList<>();
        List<Double> list_t_b_c_income_2016 = new ArrayList<>();

        double t_b_g_income_2014_max = 0;
        double t_b_g_income_2015_max = 0;
        double t_b_g_income_2016_max = 0;
        double t_b_d_income_2014_max = 0;
        double t_b_d_income_2015_max = 0;
        double t_b_d_income_2016_max = 0;
        double t_b_c_income_2014_max = 0;
        double t_b_c_income_2015_max = 0;
        double t_b_c_income_2016_max = 0;

        for (GovFinanceEntity govFinanceEntity: list){
            String p_moth = govFinanceEntity.getpMoth();
            double t_income = govFinanceEntity.gettIncome();
            double t_b_g_income = govFinanceEntity.gettBGIncome();
            double t_b_d_income = govFinanceEntity.gettBDIncome();
            double t_b_c_income = govFinanceEntity.gettBCIncome();

            if(p_moth.contains("2014")){

                list_t_income_2014.add(t_income);
                list_t_b_g_income_2014.add(t_b_g_income);
                list_t_b_d_income_2014.add(t_b_d_income);
                list_t_b_c_income_2014.add(t_b_c_income);

                t_b_g_income_2014_max = t_b_g_income;
                t_b_d_income_2014_max = t_b_d_income;
                t_b_c_income_2014_max = t_b_c_income;

            } else if(p_moth.contains("2015")){

                list_t_income_2015.add(t_income);
                list_t_b_g_income_2015.add(t_b_g_income);
                list_t_b_d_income_2015.add(t_b_d_income);
                list_t_b_c_income_2015.add(t_b_c_income);

                //生成月份维度表
                listMonth.add(p_moth.split("2015")[1]+"月");

                t_b_g_income_2015_max = t_b_g_income;
                t_b_d_income_2015_max = t_b_d_income;
                t_b_c_income_2015_max = t_b_c_income;

            } else if (p_moth.contains("2016")){

                list_t_income_2016.add(t_income);
                list_t_b_g_income_2016.add(t_b_g_income);
                list_t_b_d_income_2016.add(t_b_d_income);
                list_t_b_c_income_2016.add(t_b_c_income);

                t_b_g_income_2016_max = t_b_g_income;
                t_b_d_income_2016_max = t_b_d_income;
                t_b_c_income_2016_max = t_b_c_income;

            }
        }

        jsonObject.put("t_b_g_income_2014_max", t_b_g_income_2014_max);
        jsonObject.put("t_b_d_income_2014_max", t_b_d_income_2014_max);
        jsonObject.put("t_b_c_income_2014_max", t_b_c_income_2014_max);

        jsonObject.put("t_b_g_income_2015_max", t_b_g_income_2015_max);
        jsonObject.put("t_b_d_income_2015_max", t_b_d_income_2015_max);
        jsonObject.put("t_b_c_income_2015_max", t_b_c_income_2015_max);

        jsonObject.put("t_b_g_income_2016_max", t_b_g_income_2016_max);
        jsonObject.put("t_b_d_income_2016_max", t_b_d_income_2016_max);
        jsonObject.put("t_b_c_income_2016_max", t_b_c_income_2016_max);

        jsonObject.put("p_month", listMonth);


        jsonObject.put("list_t_income_2014", list_t_income_2014);
        jsonObject.put("list_t_income_2015", list_t_income_2015);
        jsonObject.put("list_t_income_2016", list_t_income_2016);

        jsonObject.put("list_t_b_g_income_2014", list_t_b_g_income_2014);
        jsonObject.put("list_t_b_g_income_2015", list_t_b_g_income_2015);
        jsonObject.put("list_t_b_g_income_2016", list_t_b_g_income_2016);

        jsonObject.put("list_t_b_d_income_2014", list_t_b_d_income_2014);
        jsonObject.put("list_t_b_d_income_2015", list_t_b_d_income_2015);
        jsonObject.put("list_t_b_d_income_2016", list_t_b_d_income_2016);

        jsonObject.put("list_t_b_c_income_2014", list_t_b_c_income_2014);
        jsonObject.put("list_t_b_c_income_2015", list_t_b_c_income_2015);
        jsonObject.put("list_t_b_c_income_2016", list_t_b_c_income_2016);

        //教育
        JSONArray jsonArray = new JSONArray();
        String queryEdu = "SELECT province as name, number as value FROM jx_gz_dn_edu_gk;";
        List<JSONObject> listEdu = jdbcTemplate.query(queryEdu, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","定南");
        for (JSONObject jsonObject2: listEdu){
            List<JSONObject> list2 = new ArrayList<>();
            list2.add(jsonObject1);
            list2.add(jsonObject2);
            jsonArray.add(list2);
        }
        jsonObject.put("edu", jsonArray);

        //简历年龄分布
        String queryResumeAge = "SELECT age as name,count(1) as value FROM jx_gz_dn_resume_count WHERE age >=16 AND age <= 60 AND p_time >= \"2015-01-01\" GROUP BY age ORDER BY age;";
        List<JSONObject> listResumeAge = jdbcTemplate.query(queryResumeAge, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listResumeAge", MiteGovUtils.listSortF(listResumeAge));

        //招聘经验分布
        String queryHireExp = "SELECT exp as name,count(1) as value FROM jx_gz_dn_hire WHERE p_time >= \"2015-01-01\"  GROUP BY exp;";
        List<JSONObject> listHireExp = jdbcTemplate.query(queryHireExp, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listHireExp", listHireExp);

        //部门分布
        String querySection = "SELECT o_section as name,count(1) as value FROM jx_gz_dn_ask_politics WHERE p_time >= \"2015-01-01\" GROUP BY o_section ORDER BY value DESC;";
        List<JSONObject> listSection = jdbcTemplate.query(querySection, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("section", MiteGovUtils.cutListAndMergeOther(listSection, 20, 1));

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
        for (JSONObject jsonObject12: listScaleTmp){
            String name = jsonObject12.getString("name");
            if (name.equals("未知")){
                listScale.set(0, jsonObject12);
            }else if (name.equals("0-100人")){
                listScale.set(1, jsonObject12);
            }else if (name.equals("100-300人")){
                listScale.set(2, jsonObject12);
            }else if (name.equals("300-1000")){
                JSONObject jsonObject11 = new JSONObject();
                jsonObject11.put("name", "300-1000人");
                jsonObject11.put("value", jsonObject12.get("value"));
                listScale.set(3, jsonObject11);
            }else if (name.equals("1000-10000人")){
                listScale.set(4, jsonObject12);
            }else if (name.equals("10000人及以上")){
                listScale.set(5, jsonObject12);
            }
        }
        jsonObject.put("listScale", CollectionsSort.listSortF(listScale));

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

        jsonObject.put("time","2015-01-01");


        //雾霾分析-获取地域
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
        jsonObject.put("wm_area", listArea);

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
        jsonObject.put("wm_day", CollectionsSort.listSortF(listDay));

        return jsonObject;
    }

}
