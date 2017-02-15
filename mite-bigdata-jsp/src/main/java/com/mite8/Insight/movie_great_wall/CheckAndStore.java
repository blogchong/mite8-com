package com.mite8.Insight.movie_great_wall;

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
 * Desc:  进行数据校验存储
 */
public class CheckAndStore {

    private static final Logger logger = Logger.getLogger(CheckAndStore.class.getName());

    //数据校验
    public static int checkNameAndPTime(JdbcTemplate jdbcTemplate, String id) {

        String query = "SELECT m_id FROM insight_movie_global WHERE m_id = \"" + id + "\";";
        List<Integer> list = jdbcTemplate.query(query, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("m_id");
            }
        });

        if (list.size() == 0) {
            //新增
            return 1;
        } else{
            return 0;
        }
    }

    //数据入库
    public static boolean storeData(JdbcTemplate jdbcTemplate,JSONObject jsonObject, int type) {
        int retNum = 0;
        try {

            jdbcTemplate.update("set names utf8mb4");

            String update_time = jsonObject.getString("update_time");
            String m_id  = jsonObject.getString("m_id");
            String m_name  = jsonObject.getString("m_name");
            String m_year  = jsonObject.getString("m_year");
            String m_dy  = jsonObject.getString("m_dy");
            String m_bj  = jsonObject.getString("m_bj");
            String m_zy  = jsonObject.getString("m_zy");
            String m_type  = jsonObject.getString("m_type");
            String m_area  = jsonObject.getString("m_area");
            String m_time  = jsonObject.getString("m_time");
            String m_url  = jsonObject.getString("m_url");

            double m_score  = jsonObject.getDouble("m_score");
            double m_c_score  = jsonObject.getDouble("m_c_score");

            int m_num  = jsonObject.getInt("m_num");
            int m_1_num  = jsonObject.getInt("m_1_num");
            int m_2_num  = jsonObject.getInt("m_2_num");
            int m_3_num  = jsonObject.getInt("m_3_num");
            int m_4_num  = jsonObject.getInt("m_4_num");
            int m_5_num  = jsonObject.getInt("m_5_num");

            int m_c_num = jsonObject.getInt("m_c_num");
            int m_c_1_num  = jsonObject.getInt("m_c_1_num");
            int m_c_2_num  = jsonObject.getInt("m_c_2_num");
            int m_c_3_num  = jsonObject.getInt("m_c_3_num");
            int m_c_4_num  = jsonObject.getInt("m_c_4_num");
            int m_c_5_num  = jsonObject.getInt("m_c_5_num");

            int m_page  = jsonObject.getInt("m_page");

            String query = "";
            if (type == 1) {
                query = "insert into insight_movie_global(update_time,m_id,m_name,m_year," +
                        "m_dy,m_bj,m_zy,m_type,m_area,m_time," +
                        "m_score,m_num,m_1_num,m_2_num,m_3_num,m_4_num,m_5_num," +
                        "m_c_score,m_c_num,m_c_1_num,m_c_2_num,m_c_3_num,m_c_4_num,m_c_5_num," +
                        "m_url, m_page) " +
                        "values(\"" + update_time + "\",\"" + m_id + "\", \"" + m_name + "\",\"" + m_year + "\",\""
                        + m_dy + "\",\"" + m_bj + "\",\"" + m_zy + "\",\"" + m_type+ "\",\"" + m_area + "\",\"" + m_time+ "\",\""
                        + m_score + "\",\"" + m_num + "\",\"" + m_1_num + "\",\""+ m_2_num + "\",\""+ m_3_num + "\",\""+ m_4_num + "\",\""+ m_5_num + "\",\""
                        + m_c_score + "\",\"" + m_c_num + "\",\"" + m_c_1_num + "\",\"" + m_c_2_num+ "\",\""+ m_c_3_num+ "\",\""+ m_c_4_num+ "\",\""+ m_c_5_num+ "\",\""
                        + m_url + "\",\"" + m_page+ "\")";

            } else {
                query = "update insight_movie_global set update_time = \"" + update_time + "\"" +
                        ",m_id=\""+m_id+"\",m_name=\""+m_name+"\",m_year=\""+m_year+"\"," +
                        "m_dy=\""+m_dy+"\",m_bj=\""+m_bj+"\",m_zy=\""+m_zy+"\",m_type=\""+m_type+"\",m_area=\""+m_area+"\",m_time=\""+m_time+"\"," +
                        "m_score=\""+m_score+"\",m_num=\""+m_num+"\",m_1_num=\""+m_1_num+"\",m_2_num=\""+m_2_num+"\",m_3_num=\""+m_3_num+"\",m_4_num=\""+m_4_num+"\",m_5_num=\""+m_5_num+"\","+
                        "m_c_score=\""+m_c_score+"\",m_c_num=\""+m_c_num+"\",m_c_1_num=\""+m_c_1_num+"\",m_c_2_num=\""+m_c_2_num+"\",m_c_3_num=\""+m_c_3_num+"\",m_c_4_num=\""+m_c_4_num+"\",m_c_5_num=\""+m_c_5_num+"\","+
                        "m_url=\""+m_url+"\",m_page=\""+m_page+"\"" +
                        "where m_id=\""+m_id+"\";";
            }

            retNum = jdbcTemplate.update(query);

        } catch (Exception e) {
            logger.info("ERROR - storeData1 :" + e);
        }

        if (retNum == 1) {
            return true;
        } else {
            return false;
        }
    }

}
