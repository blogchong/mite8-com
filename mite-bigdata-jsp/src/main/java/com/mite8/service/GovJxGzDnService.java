package com.mite8.service;

import com.mite8.entity.GovEmotionEntity;
import com.mite8.entity.GovFinanceEntity;
import com.mite8.jx.gz.dn.entity.EmotionEntity;
import com.mite8.utils.MapSort;
import com.mite8.utils.mite_restful.MiteGovUtils;
import com.mongodb.util.JSON;
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
 * Desc: 政务 - jx·gz·dn - 问政舆情
 */
@Service
public class GovJxGzDnService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //问政
    public JSONObject govJxGzDnPolitics(){
        JSONObject jsonObject = new JSONObject();

        //类型分布
        String queryType = "SELECT type as name,count(1) as value FROM jx_gz_dn_ask_politics WHERE p_time >= \"2015-01-01\" GROUP BY type ORDER BY value DESC;";
        List<JSONObject> listType = jdbcTemplate.query(queryType, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("type", listType);

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

        //咨询人分布-TopN
        String queryAsk = "SELECT asker as name,count(1) as value FROM jx_gz_dn_ask_politics WHERE p_time >= \"2015-01-01\" GROUP BY asker ORDER BY value DESC;";
        List<JSONObject> listAsk = jdbcTemplate.query(queryAsk, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("ask", MiteGovUtils.cutListAndMergeOther(listAsk, 15, 0));
        jsonObject.put("ask_top", 15);

        //情感词云&情感分布
        String queryEmotion = "SELECT b.words_list as words,b.score_praise as \"praise\",b.score_anger as \"anger\",b.score_fear \"fear\",b.score_hate as \"hate\",b.score_sad as \"sad\",b.score_shock as \"shock\" FROM jx_gz_dn_ask_politics a JOIN jx_gz_dn_ask_politics_emotion b ON a.id = b.id WHERE a.p_time >= \"2015-01-01\";";
        List<GovEmotionEntity> listEmotionAnd = jdbcTemplate.query(queryEmotion, new RowMapper<GovEmotionEntity>() {
            @Override
            public GovEmotionEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                GovEmotionEntity govEmotionEntity = new GovEmotionEntity();
                govEmotionEntity.setWords(resultSet.getString("words").trim());
                govEmotionEntity.setPraise(resultSet.getInt("praise"));
                govEmotionEntity.setAnger(resultSet.getInt("anger"));
                govEmotionEntity.setFear(resultSet.getInt("fear"));
                govEmotionEntity.setHate(resultSet.getInt("hate"));
                govEmotionEntity.setSad(resultSet.getInt("sad"));
                govEmotionEntity.setShock(resultSet.getInt("shock"));
                return govEmotionEntity;
            }
        });
        int praise = 0;
        int fear = 0;
        int hate = 0;
        int sad = 0;
        int shock = 0;
        int anger = 0;
        Map<String, Integer> mapEmotion = new HashMap<>();
        for (GovEmotionEntity govEmotionEntity: listEmotionAnd) {
            praise += govEmotionEntity.getPraise();
            fear += govEmotionEntity.getFear();
            hate += govEmotionEntity.getHate();
            sad += govEmotionEntity.getSad();
            shock += govEmotionEntity.getShock();
            anger += govEmotionEntity.getAnger();
            String words = govEmotionEntity.getWords();
            String[] tmp = words.split(",");
            if (tmp.length > 1 && !words.equals("")){
                for(String word : tmp) {
                    if (mapEmotion.containsKey(word)) {
                        mapEmotion.put(word, mapEmotion.get(word) + 1);
                    } else {
                        mapEmotion.put(word, 1);
                    }
                }
            }
        }
        //情感分布
        int max = 0;
        if (max < praise) { max = praise;}
        if (max < fear) { max = fear;}
        if (max < hate) { max = hate; }
        if (max < sad) { max = sad; }
        if (max < anger) { max = anger; }
        if (max < shock) { max = shock; }
        List<JSONObject> listEmotion = new ArrayList<>();
        List<Integer> listEmotionValue = new ArrayList<>();
        JSONObject jsonObjectPraise = new JSONObject();
        jsonObjectPraise.put("text", "褒");
        jsonObjectPraise.put("max", max);
        listEmotion.add(jsonObjectPraise);
        listEmotionValue.add(praise);
        JSONObject jsonObjectFear = new JSONObject();
        jsonObjectFear.put("text", "惧");
        jsonObjectFear.put("max", max);
        listEmotion.add(jsonObjectFear);
        listEmotionValue.add(fear);
        JSONObject jsonObjectAnger = new JSONObject();
        jsonObjectAnger.put("text", "怒");
        jsonObjectAnger.put("max", max);
        listEmotion.add(jsonObjectAnger);
        listEmotionValue.add(anger);
        JSONObject jsonObjectHate = new JSONObject();
        jsonObjectHate.put("text", "厌");
        jsonObjectHate.put("max", max);
        listEmotion.add(jsonObjectHate);
        listEmotionValue.add(hate);
        JSONObject jsonObjectSad = new JSONObject();
        jsonObjectSad.put("text", "哀");
        jsonObjectSad.put("max", max);
        listEmotion.add(jsonObjectSad);
        listEmotionValue.add(sad);
        JSONObject jsonObjectShock = new JSONObject();
        jsonObjectShock.put("text", "惊");
        jsonObjectShock.put("max", max);
        listEmotion.add(jsonObjectShock);
        listEmotionValue.add(shock);
        jsonObject.put("emotion_name", listEmotion);
        jsonObject.put("emotion_value", listEmotionValue);

        //情感词云
        List<JSONObject> listEmotionWords = new ArrayList<>();
        mapEmotion = MapSort.sortByValueAndCut(mapEmotion, 100);
        for(String key: mapEmotion.keySet()) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("name", key);
            jsonObject1.put("value", mapEmotion.get(key));
            listEmotionWords.add(jsonObject1);
        }
        jsonObject.put("emotion_words", listEmotionWords);

        jsonObject.put("time", "2015-01-01");

        return jsonObject;
    }

    //财政
    public JSONObject govJxGzDnFinance(){
        JSONObject jsonObject = new JSONObject();

        String query = "SELECT p_month,m_income,m_n_income,m_n_pay,t_income,t_b_g_income,t_b_d_income,t_b_c_income,t_c_z_income," +
                "t_c_d_income,t_s_y_income,t_s_n_income,t_n_pay,t_j_income,t_j_pay FROM jx_gz_dn_finance ORDER BY p_month;";
        List<GovFinanceEntity> list = jdbcTemplate.query(query, new RowMapper<GovFinanceEntity>() {
            @Override
            public GovFinanceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                GovFinanceEntity govFinanceEntity = new GovFinanceEntity();
                govFinanceEntity.setpMoth(resultSet.getString("p_month"));
                govFinanceEntity.setmIncome(resultSet.getDouble("m_income"));
                govFinanceEntity.setmNIncome(resultSet.getDouble("m_n_income"));
                govFinanceEntity.setmNPay(resultSet.getDouble("m_n_pay"));
                govFinanceEntity.settIncome(resultSet.getDouble("t_income"));
                govFinanceEntity.settBGIncome(resultSet.getDouble("t_b_g_income"));
                govFinanceEntity.settBDIncome(resultSet.getDouble("t_b_d_income"));
                govFinanceEntity.settBCIncome(resultSet.getDouble("t_b_c_income"));
                govFinanceEntity.settCZIncome(resultSet.getDouble("t_c_z_income"));
                govFinanceEntity.settCDIncome(resultSet.getDouble("t_c_d_income"));
                govFinanceEntity.settSYIncome(resultSet.getDouble("t_s_y_income"));
                govFinanceEntity.settSNIncome(resultSet.getDouble("t_s_n_income"));
                govFinanceEntity.settNPay(resultSet.getDouble("t_n_pay"));
                govFinanceEntity.settJIncome(resultSet.getDouble("t_j_income"));
                govFinanceEntity.settJPay(resultSet.getDouble("t_j_pay"));
                return govFinanceEntity;
            }
        });

        //月份维度
        List<String> listMonth = new ArrayList<>();

        //m_income数据
        List<Double> list_m_income_2014 = new ArrayList<>();
        List<Double> list_m_income_2015 = new ArrayList<>();
        List<Double> list_m_income_2016 = new ArrayList<>();

        //m_n_income
        List<Double> list_m_n_income_2014 = new ArrayList<>();
        List<Double> list_m_n_income_2015 = new ArrayList<>();
        List<Double> list_m_n_income_2016 = new ArrayList<>();

        //m_n_pay
        List<Double> list_m_n_pay_2014 = new ArrayList<>();
        List<Double> list_m_n_pay_2015 = new ArrayList<>();
        List<Double> list_m_n_pay_2016 = new ArrayList<>();

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

        //t_c_z_income
        List<Double> list_t_c_z_income_2014 = new ArrayList<>();
        List<Double> list_t_c_z_income_2015 = new ArrayList<>();
        List<Double> list_t_c_z_income_2016 = new ArrayList<>();

        //t_c_d_income
        List<Double> list_t_c_d_income_2014 = new ArrayList<>();
        List<Double> list_t_c_d_income_2015 = new ArrayList<>();
        List<Double> list_t_c_d_income_2016 = new ArrayList<>();

        //t_s_y_income
        List<Double> list_t_s_y_income_2014 = new ArrayList<>();
        List<Double> list_t_s_y_income_2015 = new ArrayList<>();
        List<Double> list_t_s_y_income_2016 = new ArrayList<>();

        //t_s_n_income
        List<Double> list_t_s_n_income_2014 = new ArrayList<>();
        List<Double> list_t_s_n_income_2015 = new ArrayList<>();
        List<Double> list_t_s_n_income_2016 = new ArrayList<>();

        //t_n_pay
        List<Double> list_t_n_pay_2014 = new ArrayList<>();
        List<Double> list_t_n_pay_2015 = new ArrayList<>();
        List<Double> list_t_n_pay_2016 = new ArrayList<>();

        //t_j_income
        List<Double> list_t_j_income_2014 = new ArrayList<>();
        List<Double> list_t_j_income_2015 = new ArrayList<>();
        List<Double> list_t_j_income_2016 = new ArrayList<>();

        //t_j_pay
        List<Double> list_t_j_pay_2014 = new ArrayList<>();
        List<Double> list_t_j_pay_2015 = new ArrayList<>();
        List<Double> list_t_j_pay_2016 = new ArrayList<>();

        double t_b_g_income_2014_max = 0;
        double t_b_g_income_2015_max = 0;
        double t_b_g_income_2016_max = 0;
        double t_b_d_income_2014_max = 0;
        double t_b_d_income_2015_max = 0;
        double t_b_d_income_2016_max = 0;
        double t_b_c_income_2014_max = 0;
        double t_b_c_income_2015_max = 0;
        double t_b_c_income_2016_max = 0;
        double t_c_z_income_2014_max = 0;
        double t_c_z_income_2015_max = 0;
        double t_c_z_income_2016_max = 0;
        double t_c_d_income_2014_max = 0;
        double t_c_d_income_2015_max = 0;
        double t_c_d_income_2016_max = 0;
        double t_s_y_income_2014_max = 0;
        double t_s_y_income_2015_max = 0;
        double t_s_y_income_2016_max = 0;
        double t_s_n_income_2014_max = 0;
        double t_s_n_income_2015_max = 0;
        double t_s_n_income_2016_max = 0;

        for (GovFinanceEntity govFinanceEntity: list){
            String p_moth = govFinanceEntity.getpMoth();
            double m_income = govFinanceEntity.getmIncome();
            double m_n_income = govFinanceEntity.getmNIncome();
            double m_n_pay = govFinanceEntity.getmNPay();
            double t_income = govFinanceEntity.gettIncome();
            double t_b_g_income = govFinanceEntity.gettBGIncome();
            double t_b_d_income = govFinanceEntity.gettBDIncome();
            double t_b_c_income = govFinanceEntity.gettBCIncome();
            double t_c_z_income = govFinanceEntity.gettCZIncome();
            double t_c_d_income = govFinanceEntity.gettCDIncome();
            double t_s_y_income = govFinanceEntity.gettSYIncome();
            double t_s_n_income = govFinanceEntity.gettSNIncome();
            double t_n_pay = govFinanceEntity.gettNPay();
            double t_j_income = govFinanceEntity.gettJIncome();
            double t_j_pay = govFinanceEntity.gettJPay();

            if(p_moth.contains("2014")){

                list_m_income_2014.add(m_income);
                list_m_n_income_2014.add(m_n_income);
                list_m_n_pay_2014.add(m_n_pay);
                list_t_income_2014.add(t_income);
                list_t_b_g_income_2014.add(t_b_g_income);
                list_t_b_d_income_2014.add(t_b_d_income);
                list_t_b_c_income_2014.add(t_b_c_income);
                list_t_c_z_income_2014.add(t_c_z_income);
                list_t_c_d_income_2014.add(t_c_d_income);
                list_t_s_y_income_2014.add(t_s_y_income);
                list_t_s_n_income_2014.add(t_s_n_income);
                list_t_n_pay_2014.add(t_n_pay);
                list_t_j_income_2014.add(t_j_income);
                list_t_j_pay_2014.add(t_j_pay);

                    t_b_g_income_2014_max = t_b_g_income;
                    t_b_d_income_2014_max = t_b_d_income;
                    t_b_c_income_2014_max = t_b_c_income;
                    t_c_z_income_2014_max = t_c_z_income;
                    t_c_d_income_2014_max = t_c_d_income;
                    t_s_y_income_2014_max = t_s_y_income;
                    t_s_n_income_2014_max = t_s_n_income;

            } else if(p_moth.contains("2015")){

                list_m_income_2015.add(m_income);
                list_m_n_income_2015.add(m_n_income);
                list_m_n_pay_2015.add(m_n_pay);
                list_t_income_2015.add(t_income);
                list_t_b_g_income_2015.add(t_b_g_income);
                list_t_b_d_income_2015.add(t_b_d_income);
                list_t_b_c_income_2015.add(t_b_c_income);
                list_t_c_z_income_2015.add(t_c_z_income);
                list_t_c_d_income_2015.add(t_c_d_income);
                list_t_s_y_income_2015.add(t_s_y_income);
                list_t_s_n_income_2015.add(t_s_n_income);
                list_t_n_pay_2015.add(t_n_pay);
                list_t_j_income_2015.add(t_j_income);
                list_t_j_pay_2015.add(t_j_pay);

                //生成月份维度表
                listMonth.add(p_moth.split("2015")[1]+"月");

                    t_b_g_income_2015_max = t_b_g_income;
                    t_b_d_income_2015_max = t_b_d_income;
                    t_b_c_income_2015_max = t_b_c_income;
                    t_c_z_income_2015_max = t_c_z_income;
                    t_c_d_income_2015_max = t_c_d_income;
                    t_s_y_income_2015_max = t_s_y_income;
                    t_s_n_income_2015_max = t_s_n_income;

            } else if (p_moth.contains("2016")){

                list_m_income_2016.add(m_income);
                list_m_n_income_2016.add(m_n_income);
                list_m_n_pay_2016.add(m_n_pay);
                list_t_income_2016.add(t_income);
                list_t_b_g_income_2016.add(t_b_g_income);
                list_t_b_d_income_2016.add(t_b_d_income);
                list_t_b_c_income_2016.add(t_b_c_income);
                list_t_c_z_income_2016.add(t_c_z_income);
                list_t_c_d_income_2016.add(t_c_d_income);
                list_t_s_y_income_2016.add(t_s_y_income);
                list_t_s_n_income_2016.add(t_s_n_income);
                list_t_n_pay_2016.add(t_n_pay);
                list_t_j_income_2016.add(t_j_income);
                list_t_j_pay_2016.add(t_j_pay);

                    t_b_g_income_2016_max = t_b_g_income;
                    t_b_d_income_2016_max = t_b_d_income;
                    t_b_c_income_2016_max = t_b_c_income;
                    t_c_z_income_2016_max = t_c_z_income;
                    t_c_d_income_2016_max = t_c_d_income;
                    t_s_y_income_2016_max = t_s_y_income;
                    t_s_n_income_2016_max = t_s_n_income;

            }
        }

        jsonObject.put("t_b_g_income_2014_max", t_b_g_income_2014_max);
        jsonObject.put("t_b_d_income_2014_max", t_b_d_income_2014_max);
        jsonObject.put("t_b_c_income_2014_max", t_b_c_income_2014_max);
        jsonObject.put("t_c_z_income_2014_max", t_c_z_income_2014_max);
        jsonObject.put("t_c_d_income_2014_max", t_c_d_income_2014_max);
        jsonObject.put("t_s_y_income_2014_max", t_s_y_income_2014_max);
        jsonObject.put("t_s_n_income_2014_max", t_s_n_income_2014_max);

        jsonObject.put("t_b_g_income_2015_max", t_b_g_income_2015_max);
        jsonObject.put("t_b_d_income_2015_max", t_b_d_income_2015_max);
        jsonObject.put("t_b_c_income_2015_max", t_b_c_income_2015_max);
        jsonObject.put("t_c_z_income_2015_max", t_c_z_income_2015_max);
        jsonObject.put("t_c_d_income_2015_max", t_c_d_income_2015_max);
        jsonObject.put("t_s_y_income_2015_max", t_s_y_income_2015_max);
        jsonObject.put("t_s_n_income_2015_max", t_s_n_income_2015_max);

        jsonObject.put("t_b_g_income_2016_max", t_b_g_income_2016_max);
        jsonObject.put("t_b_d_income_2016_max", t_b_d_income_2016_max);
        jsonObject.put("t_b_c_income_2016_max", t_b_c_income_2016_max);
        jsonObject.put("t_c_z_income_2016_max", t_c_z_income_2016_max);
        jsonObject.put("t_c_d_income_2016_max", t_c_d_income_2016_max);
        jsonObject.put("t_s_y_income_2016_max", t_s_y_income_2016_max);
        jsonObject.put("t_s_n_income_2016_max", t_s_n_income_2016_max);

        jsonObject.put("p_month", listMonth);

        jsonObject.put("list_m_income_2014", list_m_income_2014);
        jsonObject.put("list_m_income_2015", list_m_income_2015);
        jsonObject.put("list_m_income_2016", list_m_income_2016);

        jsonObject.put("list_m_n_income_2014", list_m_n_income_2014);
        jsonObject.put("list_m_n_income_2015", list_m_n_income_2015);
        jsonObject.put("list_m_n_income_2016", list_m_n_income_2016);

        jsonObject.put("list_m_n_pay_2014", list_m_n_pay_2014);
        jsonObject.put("list_m_n_pay_2015", list_m_n_pay_2015);
        jsonObject.put("list_m_n_pay_2016", list_m_n_pay_2016);

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

        jsonObject.put("list_t_c_z_income_2014", list_t_c_z_income_2014);
        jsonObject.put("list_t_c_z_income_2015", list_t_c_z_income_2015);
        jsonObject.put("list_t_c_z_income_2016", list_t_c_z_income_2016);

        jsonObject.put("list_t_c_d_income_2014", list_t_c_d_income_2014);
        jsonObject.put("list_t_c_d_income_2015", list_t_c_d_income_2015);
        jsonObject.put("list_t_c_d_income_2016", list_t_c_d_income_2016);

        jsonObject.put("list_t_s_y_income_2014", list_t_s_y_income_2014);
        jsonObject.put("list_t_s_y_income_2015", list_t_s_y_income_2015);
        jsonObject.put("list_t_s_y_income_2016", list_t_s_y_income_2016);

        jsonObject.put("list_t_s_n_income_2014", list_t_s_n_income_2014);
        jsonObject.put("list_t_s_n_income_2015", list_t_s_n_income_2015);
        jsonObject.put("list_t_s_n_income_2016", list_t_s_n_income_2016);

        jsonObject.put("list_t_n_pay_2014", list_t_n_pay_2014);
        jsonObject.put("list_t_n_pay_2015", list_t_n_pay_2015);
        jsonObject.put("list_t_n_pay_2016", list_t_n_pay_2016);

        jsonObject.put("list_t_j_income_2014", list_t_j_income_2014);
        jsonObject.put("list_t_j_income_2015", list_t_j_income_2015);
        jsonObject.put("list_t_j_income_2016", list_t_j_income_2016);

        jsonObject.put("list_t_j_pay_2014", list_t_j_pay_2014);
        jsonObject.put("list_t_j_pay_2015", list_t_j_pay_2015);
        jsonObject.put("list_t_j_pay_2016", list_t_j_pay_2016);

        return jsonObject;
    }

    //人才与招聘
    public JSONObject govJxGzDnResumeAndHire(){
        JSONObject jsonObject = new JSONObject();

        //求职薪酬均值
        String queryAvgPay = "SELECT truncate(SUM(pay_count)/COUNT(1),0) as avg_pay FROM jx_gz_dn_resume_count WHERE pay_count != 0 AND p_time >= \"2015-01-01\";";
        List<Integer> listAvgPay = jdbcTemplate.query(queryAvgPay, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("avg_pay");
            }
        });
        int avg_pay = 0;
        if (listAvgPay.size() >= 1) {
            avg_pay = listAvgPay.get(0);
        }
        jsonObject.put("avg_pay", avg_pay);

        //招聘薪酬均值
        String queryAvgSalary = "SELECT truncate(SUM(salary_count)/COUNT(1),0) as avg_salary FROM jx_gz_dn_hire_count WHERE salary_count != 0 AND p_time >= \"2015-01-01\";";
        List<Integer> listAvgSalary = jdbcTemplate.query(queryAvgSalary, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("avg_salary");
            }
        });
        int avg_salary = 0;
        if (listAvgSalary.size() >= 1) {
            avg_salary = listAvgSalary.get(0);
        }
        jsonObject.put("avg_salary", avg_salary);

        //招聘学历分布
        String queryHireEdu = "SELECT edu as name, count(1) as value FROM jx_gz_dn_hire WHERE p_time >= \"2015-01-01\" GROUP BY edu;";
        List<JSONObject> listHireEdu = jdbcTemplate.query(queryHireEdu, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listHireEdu", listHireEdu);

        //简历学历分布
        String queryResumeEdu = "SELECT edu as name, count(1) as value FROM jx_gz_dn_resume WHERE p_time >= \"2015-01-01\" GROUP BY edu;";
        List<JSONObject> listResumeEdu = jdbcTemplate.query(queryResumeEdu, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getString("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listResumeEdu", listResumeEdu);

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

        //求职男女比例分布
        String queryResumeGender = "SELECT gender as name, count(1) as value FROM jx_gz_dn_resume WHERE p_time >= \"2015-01-01\" GROUP BY gender;";
        List<JSONObject> listResumeGender = jdbcTemplate.query(queryResumeGender, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listResumeGender", listResumeGender);

        //招聘企业规模分布
        String queryHireComScale = "SELECT company_scale as name, count(1) as value FROM jx_gz_dn_hire WHERE p_time >= \"2015-01-01\" GROUP BY company_scale;";
        List<JSONObject> listHireComScale = jdbcTemplate.query(queryHireComScale, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listHireComScale", listHireComScale);

        //招聘企业性质分布
        String queryHireComNature = "SELECT company_nature as name, count(1) as value FROM jx_gz_dn_hire WHERE p_time >= \"2015-01-01\" GROUP BY company_nature;";
        List<JSONObject> listHireComNature = jdbcTemplate.query(queryHireComNature, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listHireComNature", listHireComNature);

        jsonObject.put("time", "2015-01-01");

        return jsonObject;
    }

    //口碑
    public JSONObject govJxGzDnPraise(){
        JSONObject jsonObject = new JSONObject();

        //情感词云&情感分布
        String queryEmotion = "SELECT words_list as words,score_praise as \"praise\",score_anger as \"anger\",score_fear \"fear\",score_hate as \"hate\",score_sad as \"sad\",score_shock as \"shock\"  FROM jx_gz_dn_praise_score where p_time >= \"2015-01-01\";";
        List<GovEmotionEntity> listEmotionAnd = jdbcTemplate.query(queryEmotion, new RowMapper<GovEmotionEntity>() {
            @Override
            public GovEmotionEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                GovEmotionEntity govEmotionEntity = new GovEmotionEntity();
                govEmotionEntity.setWords(resultSet.getString("words").trim());
                govEmotionEntity.setPraise(resultSet.getInt("praise"));
                govEmotionEntity.setAnger(resultSet.getInt("anger"));
                govEmotionEntity.setFear(resultSet.getInt("fear"));
                govEmotionEntity.setHate(resultSet.getInt("hate"));
                govEmotionEntity.setSad(resultSet.getInt("sad"));
                govEmotionEntity.setShock(resultSet.getInt("shock"));
                return govEmotionEntity;
            }
        });
        int praise = 0;
        int fear = 0;
        int hate = 0;
        int sad = 0;
        int shock = 0;
        int anger = 0;
        Map<String, Integer> mapEmotion = new HashMap<>();
        for (GovEmotionEntity govEmotionEntity: listEmotionAnd) {
            praise += govEmotionEntity.getPraise();
            fear += govEmotionEntity.getFear();
            hate += govEmotionEntity.getHate();
            sad += govEmotionEntity.getSad();
            shock += govEmotionEntity.getShock();
            anger += govEmotionEntity.getAnger();
            String words = govEmotionEntity.getWords();
            String[] tmp = words.split(",");
            if (tmp.length > 1 && !words.equals("")){
                for(String word : tmp) {
                    if (mapEmotion.containsKey(word)) {
                        mapEmotion.put(word, mapEmotion.get(word) + 1);
                    } else {
                        mapEmotion.put(word, 1);
                    }
                }
            }
        }
        //情感分布
        int max = 0;
        if (max < praise) { max = praise;}
        if (max < fear) { max = fear;}
        if (max < hate) { max = hate; }
        if (max < sad) { max = sad; }
        if (max < anger) { max = anger; }
        if (max < shock) { max = shock; }
        List<JSONObject> listEmotion = new ArrayList<>();
        List<Integer> listEmotionValue = new ArrayList<>();
        JSONObject jsonObjectPraise = new JSONObject();
        jsonObjectPraise.put("text", "褒");
        jsonObjectPraise.put("max", max);
        listEmotion.add(jsonObjectPraise);
        listEmotionValue.add(praise);
        JSONObject jsonObjectFear = new JSONObject();
        jsonObjectFear.put("text", "惧");
        jsonObjectFear.put("max", max);
        listEmotion.add(jsonObjectFear);
        listEmotionValue.add(fear);
        JSONObject jsonObjectAnger = new JSONObject();
        jsonObjectAnger.put("text", "怒");
        jsonObjectAnger.put("max", max);
        listEmotion.add(jsonObjectAnger);
        listEmotionValue.add(anger);
        JSONObject jsonObjectHate = new JSONObject();
        jsonObjectHate.put("text", "厌");
        jsonObjectHate.put("max", max);
        listEmotion.add(jsonObjectHate);
        listEmotionValue.add(hate);
        JSONObject jsonObjectSad = new JSONObject();
        jsonObjectSad.put("text", "哀");
        jsonObjectSad.put("max", max);
        listEmotion.add(jsonObjectSad);
        listEmotionValue.add(sad);
        JSONObject jsonObjectShock = new JSONObject();
        jsonObjectShock.put("text", "惊");
        jsonObjectShock.put("max", max);
        listEmotion.add(jsonObjectShock);
        listEmotionValue.add(shock);
        jsonObject.put("emotion_name", listEmotion);
        jsonObject.put("emotion_value", listEmotionValue);

        //情感词云
        List<JSONObject> listEmotionWords = new ArrayList<>();
        mapEmotion = MapSort.sortByValueAndCut(mapEmotion, 150);
        for(String key: mapEmotion.keySet()) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("name", key);
            jsonObject1.put("value", mapEmotion.get(key));
            listEmotionWords.add(jsonObject1);
        }
        jsonObject.put("emotion_words", listEmotionWords);

        jsonObject.put("time","2015-01-01");

        //news来源
        String queryFrom = "SELECT news_from as name, count(1) as value FROM jx_gz_dn_praise GROUP BY news_from ORDER BY value DESC;";
        List<JSONObject> listFrom = jdbcTemplate.query(queryFrom, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });
        jsonObject.put("listFrom", MiteGovUtils.cutListAndMergeOther(listFrom,15,0));

        return jsonObject;
    }

    //房产
    public JSONObject govJxGzDnHouse(){
        JSONObject jsonObject = new JSONObject();

        //投诉
        String queryTs = "SELECT house_words as name, count(1) as value FROM jx_gz_dn_ask_politics_house GROUP BY house_words ORDER BY value desc;";
        List<JSONObject> listHouseTs = jdbcTemplate.query(queryTs, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name", resultSet.getString("name"));
                jsonObject1.put("value", resultSet.getInt("value"));
                return jsonObject1;
            }
        });

        jsonObject.put("listHouseTs", MiteGovUtils.cutListAndMergeOther(listHouseTs, 20, 0));
        jsonObject.put("house_top", 20);

        //AVG
        String queryAvgPrice = "SELECT truncate(SUM(price)/COUNT(1),0) as value FROM jx_gz_dn_house;";
        List<Integer> listAvgPrice = jdbcTemplate.query(queryAvgPrice, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("value");
            }
        });
        int avg_price = 0;
        if (listAvgPrice.size()>0){avg_price = listAvgPrice.get(0);}
        jsonObject.put("avg_price",avg_price);

        //AVG
        String queryMaxPrice = "SELECT MAX(price) as value FROM jx_gz_dn_house;";
        List<Integer> listMaxPrice = jdbcTemplate.query(queryMaxPrice, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("value");
            }
        });
        int max_price = 0;
        if (listMaxPrice.size()>0){max_price = listMaxPrice.get(0);}
        jsonObject.put("max_price", max_price);

        return jsonObject;
    }

    //教育
    public JSONObject govJxGzDnEdu(){
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

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("edu", jsonArray);
        return jsonObject;
    }

}
