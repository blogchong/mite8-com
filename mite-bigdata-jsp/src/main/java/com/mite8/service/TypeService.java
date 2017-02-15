package com.mite8.service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: blogchong
 * Time:  2016/12/14.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  列表服务
 */
@Service
public class TypeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //单个addr的舆情列表
    public JSONObject typeByAID(int aId) {
        JSONObject jsonObject = new JSONObject();

        String a_name = "未知";
        String a_url = "/";
        //类型分布
        String queryType = "SELECT b.a_id,b.a_name,b.a_url,a.t_title,a.t_desc,a.t_images,a.t_url FROM mite_gov_type a JOIN mite_gov_addr b ON a.a_id = b.a_id WHERE a.a_id="+aId+" ORDER BY t_rank;";
        List<JSONObject> listType = jdbcTemplate.query(queryType, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("a_name", resultSet.getString("a_name"));
                jsonObject1.put("a_url", resultSet.getString("a_url"));
                jsonObject1.put("t_title", resultSet.getString("t_title"));
                jsonObject1.put("t_desc", resultSet.getString("t_desc"));
                jsonObject1.put("t_images", resultSet.getString("t_images"));
                jsonObject1.put("t_url", resultSet.getString("t_url"));
                return jsonObject1;
            }
        });

        if (listType.size()!=0){
            a_name = listType.get(0).getString("a_name");
            a_url = listType.get(0).getString("a_url");
        }

        jsonObject.put("a_name", a_name);
        jsonObject.put("a_url", a_url);
        jsonObject.put("list_main", listType);

        return jsonObject;
    }
}
