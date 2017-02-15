package com.mite8.jx.gz.dn.service.public_praise.utils;

import com.mite8.utils.DefineOut;
import com.mite8.utils.TransferTime;
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
    public static boolean checkTitle(JdbcTemplate jdbcTemplate, String title, String fromUrl) {

        String query = "SELECT title FROM jx_gz_dn_praise WHERE title = \"" + title + "\" and from_url = \"" + fromUrl + "\";";
        List<String> list = jdbcTemplate.query(query, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("title");
            }
        });

        if (list.size() > 1) {
            logger.info("ERROR - checkId - Please check mysql title[" + title + "] fromUrl[" + fromUrl + "]");
            return true;
        } else if (list.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    //数据入库
    public static boolean storeDataPraise(JdbcTemplate jdbcTemplate,JSONObject jsonObject, String updateTime) {
        int retNum = 0;
        try {
            String title = jsonObject.getString("title").replace("\"","\\\"").replace("'","\\'");
            String newsFrom = jsonObject.getString("news_from").replace("\"","\\\"").replace("'", "\\'");
            String pTime = jsonObject.getString("p_time");
            String fromUrl = jsonObject.getString("from_url");
            String wordsList = jsonObject.getString("words_list");


            String query = "insert into jx_gz_dn_praise(update_time,title,p_time,news_from,from_url)" +
                    "values(\"" + updateTime + "\",\"" + title + "\", \"" + pTime + "\", \"" + newsFrom + "\",\"" + fromUrl + "\")";

            retNum = jdbcTemplate.update(query);

            //存储情感数据
            int scorePraise = jsonObject.getInt("score_praise");
            int scoreAnger = jsonObject.getInt("score_anger");
            int scoreSad = jsonObject.getInt("score_sad");
            int scoreFear = jsonObject.getInt("score_fear");
            int scoreHate = jsonObject.getInt("score_hate");
            int scoreShock = jsonObject.getInt("score_shock");
            String queryEmotion = "insert into jx_gz_dn_praise_score(update_time,p_time,from_url,words_list," +
                    "score_praise,score_anger,score_sad,score_fear,score_hate,score_shock) " +
                    "values(\"" + updateTime + "\",\"" + pTime + "\", \""+ fromUrl + "\", \""+ wordsList + "\", \""
                    + scorePraise + "\",\""
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
