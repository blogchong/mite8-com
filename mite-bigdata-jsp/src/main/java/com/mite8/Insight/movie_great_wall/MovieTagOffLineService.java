package com.mite8.Insight.movie_great_wall;

import com.mite8.jx.gz.dn.entity.EmotionEntity;
import com.mite8.jx.gz.dn.utils.LoadEmotionDictionary;
import com.mite8.utils.DefineOut;
import com.mite8.utils.TransferTime;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Author: blogc
 * Time:  2016/12/18.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 离线处理tag，进行拆分
 */
@Service
public class MovieTagOffLineService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(MovieTagOffLineService.class.getName());

    public void movieTagOffLine(){

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        String queryEmotion = "SELECT a.m_id,a.c_id,e_type as c_type,words_list as lists,b.c_level\n" +
                "FROM insight_movie_emotion a \n" +
                "JOIN insight_movie_comments b on a.c_id=b.c_id\n" +
                "WHERE words_list != \"\"\n" +
                "GROUP BY m_id,c_id,c_type,words_list,c_level;";
        List<JSONObject> listEmotion = jdbcTemplate.query(queryEmotion, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("m_id", resultSet.getString("m_id"));
                jsonObject1.put("c_id", resultSet.getString("c_id"));
                jsonObject1.put("c_type", resultSet.getString("c_type"));
                jsonObject1.put("c_level", resultSet.getString("c_level"));
                jsonObject1.put("lists", resultSet.getString("lists"));
                return jsonObject1;
            }
        });

        int count_emotion = 0;
        for (JSONObject jsonObject: listEmotion){

            String m_id = jsonObject.getString("m_id");
            String c_id = jsonObject.getString("c_id");
            String c_type = jsonObject.getString("c_type");
            String c_level = jsonObject.getString("c_level");
            String lists = jsonObject.getString("lists");

            String[] words = lists.split(",");
            List<JSONObject> list = new ArrayList<>();
            for (String word: words){

                JSONObject jsonObject_emotion = new JSONObject();
                jsonObject_emotion.put("m_id", m_id);
                jsonObject_emotion.put("c_id", c_id);
                jsonObject_emotion.put("c_type", c_type);
                jsonObject_emotion.put("c_level", c_level);
                jsonObject_emotion.put("t_type", "1");

                jsonObject_emotion.put("c_tag", word);
                jsonObject_emotion.put("t_level", "0");
                jsonObject_emotion.put("t_degree", "0");
                if (LoadEmotionDictionary.dicEmotion.containsKey(word)) {

                    EmotionEntity emotionEntity = LoadEmotionDictionary.dicEmotion.get(word);
                    String type = emotionEntity.getType();
                    double strength = emotionEntity.getStrength();
                    if (type.equals("PD") || type.equals("PH") || type.equals("PG")
                            || type.equals("PB") || type.equals("PK")){
                        jsonObject_emotion.put("t_level", "1");
                    } else if (type.equals("NA")){
                        jsonObject_emotion.put("t_level", "2");
                    } else if (type.equals("NB") || type.equals("NJ") || type.equals("NH")
                            || type.equals("PF")){
                        jsonObject_emotion.put("t_level", "3");
                    } else if (type.equals("NI") || type.equals("NC") || type.equals("NG")){
                        jsonObject_emotion.put("t_level", "4");
                    } else if (type.equals("NE") || type.equals("ND") || type.equals("NN")
                            || type.equals("NK") || type.equals("NL")){
                        jsonObject_emotion.put("t_level", "5");
                    } else if (type.equals("PC")){
                        jsonObject_emotion.put("t_level", "6");
                    }
                    jsonObject_emotion.put("t_degree", strength);
                }
                list.add(jsonObject_emotion);
            }

            storeListData(jdbcTemplate,updateTime,list);
            count_emotion++;
        }

        logger.info("EMOTION OK. NUM["+count_emotion+"]");

        String queryStar= "SELECT a.m_id,a.c_id,b.c_type,stars_list as lists,b.c_level\n" +
                "FROM insight_movie_star a \n" +
                "JOIN insight_movie_comments b on a.c_id=b.c_id\n" +
                "WHERE stars_list != \"\"\n" +
                "GROUP BY m_id,c_id,c_type,stars_list,c_level;";
        List<JSONObject> listStar = jdbcTemplate.query(queryStar, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("m_id", resultSet.getString("m_id"));
                jsonObject1.put("c_id", resultSet.getString("c_id"));
                jsonObject1.put("c_type", resultSet.getString("c_type"));
                jsonObject1.put("c_level", resultSet.getString("c_level"));
                jsonObject1.put("lists", resultSet.getString("lists"));
                return jsonObject1;
            }
        });

        int count_star = 0;
        for (JSONObject jsonObject: listStar){

            String m_id = jsonObject.getString("m_id");
            String c_id = jsonObject.getString("c_id");
            String c_type = jsonObject.getString("c_type");
            String c_level = jsonObject.getString("c_level");
            String lists = jsonObject.getString("lists");

            String[] words = lists.split(",");
            List<JSONObject> list = new ArrayList<>();
            for (String word: words){

                JSONObject jsonObject_star = new JSONObject();
                jsonObject_star.put("m_id", m_id);
                jsonObject_star.put("c_id", c_id);
                jsonObject_star.put("c_type", c_type);
                jsonObject_star.put("c_level", c_level);
                jsonObject_star.put("t_type", "2");

                jsonObject_star.put("c_tag", word);

                if("张艺谋".equals(word)){
                    jsonObject_star.put("t_level","1");
                } else {
                    jsonObject_star.put("t_level", "2");
                }
                jsonObject_star.put("t_degree","0");

                list.add(jsonObject_star);
            }

            storeListData(jdbcTemplate,updateTime,list);
            count_star++;
        }

        logger.info("STAR OK. NUM["+count_star+"]");

    }

    public static void storeListData(JdbcTemplate jdbcTemplate,String update_time, List<JSONObject> list){
        for (int j = 0; j < list.size(); j++){
            JSONObject jsonObject = list.get(j);
            String m_id = jsonObject.getString("m_id");
            String c_id = jsonObject.getString("c_id");
            String c_type = jsonObject.getString("c_type");
            String c_level = jsonObject.getString("c_level");
            String t_type = jsonObject.getString("t_type");
            String c_tag = jsonObject.getString("c_tag");
            String t_level = jsonObject.getString("t_level");
            int t_degree = jsonObject.getInt("t_degree");

            String queryInsertKeyWordsTable="insert into insight_movie_tag" +
                    "(update_time,m_id,c_level,c_id,c_type,c_tag,t_type,t_level,t_degree) values";
            boolean flagKeyWords = false;

            //update_time,wechat_id,type,key_word,score
            if (!flagKeyWords) {
                queryInsertKeyWordsTable = queryInsertKeyWordsTable + "(\"" + update_time + "\", \"" + m_id
                        + "\",\"" + c_level + "\", \"" + c_id + "\", \"" + c_type  + "\", \"" + t_type
                        + "\", \"" + c_tag + "\", \"" + t_level + "\", \"" + t_degree + "\")";
                flagKeyWords = true;
            } else {
                queryInsertKeyWordsTable = queryInsertKeyWordsTable + "," +
                        "(\"" + update_time + "\", \"" + m_id
                        + "\",\"" + c_level + "\", \"" + c_id + "\", \"" + c_type  + "\", \"" + t_type
                        + "\", \"" + c_tag + "\", \"" + t_level + "\", \"" + t_degree + "\")";
            }

            jdbcTemplate.update(queryInsertKeyWordsTable);
        }
    }

}
