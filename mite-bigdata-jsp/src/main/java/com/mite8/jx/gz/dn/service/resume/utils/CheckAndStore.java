package com.mite8.jx.gz.dn.service.resume.utils;

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
    public static int checkNameAndPTime(JdbcTemplate jdbcTemplate, String name, String pTime) {

        String query = "SELECT name,p_time FROM jx_gz_dn_resume WHERE name = \"" + name + "\";";
        List<JSONObject> list = jdbcTemplate.query(query, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", resultSet.getString("name"));
                jsonObject.put("p_time", resultSet.getString("p_time"));
                return jsonObject;
            }
        });

        if (list.size() == 0) {
            //新增
            return 1;
        } else{
            JSONObject jsonObject = list.get(0);
            String pTimeGet = jsonObject.getString("p_time");
            if (pTimeGet.equals(pTime)) {
                //跳过
                return 0;
            } else {
                try{
                    if (TransferTime.stringToLong(pTime, DefineOut.TIME_FORMAT) > TransferTime.stringToLong(pTimeGet, DefineOut.TIME_FORMAT)){
                        return 2;
                    }
                } catch (Exception e) {
                    //进行更新操作
                    return 0;
                }
                return 0;
            }
        }
    }

    //数据入库
    public static boolean storeData(JdbcTemplate jdbcTemplate,JSONObject jsonObject, String updateTime, int type) {
        int retNum = 0;
        try {
            String name = jsonObject.getString("name").replace("\"","\\\"").replace("'","\\'");
            int age = jsonObject.getInt("age");
            String gender = jsonObject.getString("gender");
            String fromUrl = jsonObject.getString("from_url");
            String listUrl = jsonObject.getString("list_url");
            String pTime = jsonObject.getString("p_time");
            String intention = jsonObject.getString("intention").replace("\"","\\\"").replace("'","\\'");
            String origin = jsonObject.getString("origin");
            String edu = jsonObject.getString("edu");
            String school = jsonObject.getString("school").replace("\"","\\\"").replace("'","\\'");
            String major = jsonObject.getString("major").replace("\"","\\\"").replace("'","\\'");
            String wishType = jsonObject.getString("wish_type").replace("\"","\\\"").replace("'","\\'");
            String address = jsonObject.getString("address").replace("\"","\\\"").replace("'","\\'");
            String exp = jsonObject.getString("exp");
            String pay = jsonObject.getString("pay");
            String point = jsonObject.getString("point").replace("\"","\\\"").replace("'","\\'");
            String introduction = jsonObject.getString("introduction").replace("\"","\\\"").replace("'","\\'");
            String eduCont = jsonObject.getString("edu_cont").replace("\"","\\\"").replace("'","\\'");
            String photo = jsonObject.getString("photo").replace("\"","\\\"").replace("'","\\'");
            String phone = jsonObject.getString("phone");
            String mail = jsonObject.getString("mail");
            int payCount = jsonObject.getInt("pay_count");

            String query = "";
            if (type == 1) {
                query = "insert into jx_gz_dn_resume(update_time,name,age,gender," +
                        "from_url,list_url,p_time,intention,origin,edu,school,major," +
                        "wish_type, address, exp, pay, point, introduction, edu_cont," +
                        "photo,phone,mail) " +
                        "values(\"" + updateTime + "\",\"" + name + "\", \"" + age + "\",\"" + gender + "\",\""
                        + fromUrl + "\",\"" + listUrl + "\",\"" + pTime + "\",\"" + intention
                        + "\",\"" + origin + "\",\"" + edu + "\",\"" + school + "\",\""
                        + major + "\",\"" + wishType + "\",\"" + address + "\",\"" + exp+ "\",\""
                        + pay + "\",\""+ point  + "\",\""+ introduction + "\",\""
                        + eduCont + "\",\"" + photo + "\",\"" + phone+ "\",\"" + mail+ "\")";

            } else {
                query = "update jx_gz_dn_resume set update_time = \"" + updateTime + "\"" +
                        ",name=\""+name+"\",age="+age+",gender=\""+gender+"\"," +
                        "from_url=\""+fromUrl+"\",list_url=\""+listUrl+"\"," +
                        "p_time=\""+pTime+"\",intention=\""+intention+"\"," +
                        "origin=\""+origin+"\",edu=\""+edu+"\",school=\""+school+"\"," +
                        "major=\""+major+"\",wish_type=\""+wishType+"\", " +
                        "address=\""+address+"\",exp=\""+exp+"\",pay=\""+pay+"\"," +
                        "point=\""+point+"\",introduction=\""+introduction+"\"," +
                        "edu_cont=\""+eduCont+"\",photo=\""+photo+"\",phone=\""+phone+"\",mail=\""+mail+"\" " +
                        "where name=\""+name+"\";";
            }

            retNum = jdbcTemplate.update(query);

            //更新高级属性
            String queryJudge = "SELECT p_time FROM jx_gz_dn_resume_count WHERE name = \"" + name + "\" and gender = \"" +
                    gender + "\" and age = \"" + age+ "\";";
            List<JSONObject> list = jdbcTemplate.query(queryJudge, new RowMapper<JSONObject>() {
                @Override
                public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("p_time", resultSet.getString("p_time"));
                    return jsonObject;
                }
            });

            if (list.size() == 0) {
                //新增
                String queryResumeCount = "insert into jx_gz_dn_resume_count(update_time,name,age,gender,p_time,pay_count) " +
                        "values(\"" + updateTime + "\",\"" + name + "\", \"" + age + "\",\"" + gender + "\",\""
                        +  pTime + "\",\"" + payCount + "\")";
                jdbcTemplate.update(queryResumeCount);

            } else{
                JSONObject jsonObject1 = list.get(0);
                String pTimeGet = jsonObject1.getString("p_time");
                try{
                    if (TransferTime.stringToLong(pTime, DefineOut.TIME_FORMAT) > TransferTime.stringToLong(pTimeGet, DefineOut.TIME_FORMAT)){
                        //只有时间大于记录才更新
                        String queryResumeCount = "update jx_gz_dn_resume_count set update_time = \"" + updateTime + "\"" +
                                ",p_time=\""+pTime+"\",pay_count=\""+payCount+"\" " +
                                "where name=\""+name+"\" and age = \"" + age + "\" and gender = \"" + gender + "\";";
                        jdbcTemplate.update(queryResumeCount);
                    }
                } catch (Exception e) {
                    logger.info("ERROR - storeData0 :" + e);
                }
            }

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
