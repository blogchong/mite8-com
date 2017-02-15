package com.mite8.jx.gz.dn.service.ask_politics.utils;

import net.sf.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/11/23.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  进行数据校验
 */
public class CheckAndStore {

    private static final Logger logger = Logger.getLogger(CheckAndStore.class.getName());

    //数据校验
    public static boolean checkId(JdbcTemplate jdbcTemplate, String id) {

        String query = "SELECT id FROM jx_gz_dn_ask_politics WHERE id = \"" + id + "\";";
        List<String> list = jdbcTemplate.query(query, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("id");
            }
        });

        if (list.size() > 1) {
            logger.info("ERROR - checkId - Please check mysql id[" + id + "]");
            return true;
        } else if (list.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    //数据入库
    public static boolean storeData(JdbcTemplate jdbcTemplate,JSONObject jsonObject, String updateTime) {
        int retNum = 0;
        try {
            String id = jsonObject.getString("id");
            String title = jsonObject.getString("title").replace("\"","\\\"").replace("'","\\'");
            String contents = jsonObject.getString("contents").replace("\"","\\\"").replace("'","\\'");
            String type = jsonObject.getString("type");
            String pTime = jsonObject.getString("p_time");
            String status = jsonObject.getString("status");
            String asker = jsonObject.getString("asker").replace("\"","\\\"").replace("'","\\'");
            String oSection = jsonObject.getString("o_section");
            String oTime = jsonObject.getString("o_time");
            String oContents = jsonObject.getString("o_contents").replace("\"","\\\"").replace("'","\\'");
            String fromUrl = jsonObject.getString("from_url");
            String listUrl = jsonObject.getString("list_url");

            String query = "insert into jx_gz_dn_ask_politics(update_time,id,title,contents," +
                    "type,p_time,status,asker,o_section,o_time,o_contents,from_url,list_url) " +
                    "values(\"" + updateTime + "\",\"" + id + "\", \"" + title + "\",\"" + contents + "\",\"" + type + "\"," +
                    "\"" + pTime + "\",\"" + status + "\",\"" + asker + "\",\"" + oSection + "\"," +
                    "\"" + oTime + "\",\"" + oContents + "\",\"" + fromUrl + "\",\"" + listUrl + "\")";

            retNum = jdbcTemplate.update(query);

            //存储house信息
            String houseWords = jsonObject.getString("house_words");
            String[] words = houseWords.split(",");
            if (words.length >= 1 && !houseWords.equals("")) {
                String queryHouse = "insert into jx_gz_dn_ask_politics_house(update_time,id,house_words) values";
                boolean flagKeyWords = false;
                for (String word: words){
                    if (!flagKeyWords) {
                        queryHouse = queryHouse + "(\"" + updateTime + "\", \"" + id + "\", \"" + word + "\")";
                        flagKeyWords = true;
                    } else {
                        queryHouse = queryHouse + "," +"(\"" + updateTime + "\", \"" + id + "\", \"" + word + "\")";
                    }
                }
                jdbcTemplate.update(queryHouse);
            }

            //存储情感数据
            int scorePraise = jsonObject.getInt("score_praise");
            int scoreAnger = jsonObject.getInt("score_anger");
            int scoreSad = jsonObject.getInt("score_sad");
            int scoreFear = jsonObject.getInt("score_fear");
            int scoreHate = jsonObject.getInt("score_hate");
            int scoreShock = jsonObject.getInt("score_shock");
            String wordsList = jsonObject.getString("words_list");
            String queryEmotion = "insert into jx_gz_dn_ask_politics_emotion(update_time,id,words_list," +
                    "score_praise,score_anger,score_sad,score_fear,score_hate,score_shock) " +
                    "values(\"" + updateTime + "\",\"" + id + "\", \""+ wordsList + "\", \"" + scorePraise + "\",\""
                    + scoreAnger + "\",\"" + scoreSad + "\"," +
                    "\"" + scoreFear + "\",\"" + scoreHate + "\",\""  + scoreShock + "\")";

            jdbcTemplate.update(queryEmotion);

        } catch (Exception e) {
            logger.info("ERROR - storeData :" + e);
        }

        if (retNum == 1) {
            return true;
        } else {
            return false;
        }
    }

}
