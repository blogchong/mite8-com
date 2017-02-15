package com.mite8.jx.gz.dn.service.hire.utils;

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
 * Desc:  进行数据校验存储
 */
public class CheckAndStore {

    private static final Logger logger = Logger.getLogger(CheckAndStore.class.getName());

    //数据校验
    public static int checkNameAndPTime(JdbcTemplate jdbcTemplate, String title, String pTime, String companyName, String position) {

        String query = "SELECT p_time,company_name,position FROM jx_gz_dn_hire WHERE title = \"" + title + "\";";
        List<JSONObject> list = jdbcTemplate.query(query, new RowMapper<JSONObject>() {
            @Override
            public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("company_name", resultSet.getString("company_name"));
                jsonObject.put("position", resultSet.getString("position"));
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
            String companyNameGet = jsonObject.getString("company_name");
            String positionGet = jsonObject.getString("position");
            if (pTimeGet.equals(pTime) && companyNameGet.equals(companyName) && positionGet.equals(position)) {
                //跳过
                return 0;
            } else {
                try{
                    if (TransferTime.stringToLong(pTime, DefineOut.TIME_FORMAT) > TransferTime.stringToLong(pTimeGet, DefineOut.TIME_FORMAT)){
                        //只有时间大于记录才更新
                        return 2;
                    }
                } catch (Exception e) {
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
            String title = jsonObject.getString("title").replace("\"","\\\"").replace("'", "\\'");
            String companyName = jsonObject.getString("company_name");
            String pTime = jsonObject.getString("p_time");
            String fromUrl = jsonObject.getString("from_url");
            String listUrl = jsonObject.getString("list_url");
            int careNum = jsonObject.getInt("care_num");
            String companyScale = jsonObject.getString("company_scale");
            String companyNature = jsonObject.getString("company_nature");
            String companyIndustry = jsonObject.getString("company_industry");
            String position = jsonObject.getString("position").replace("\"","\\\"").replace("'", "\\'");
            String num = jsonObject.getString("num");
            String address = jsonObject.getString("address").replace("\"","\\\"").replace("'", "\\'");
            String linkman = jsonObject.getString("linkman");
            String salary = jsonObject.getString("salary");
            String exp = jsonObject.getString("exp");
            String edu = jsonObject.getString("edu");
            String mail = jsonObject.getString("mail");
            String positionDesc = jsonObject.getString("position_desc").replace("\"","\\\"").replace("'","\\'");
            String companyDesc = jsonObject.getString("company_desc").replace("\"","\\\"").replace("'","\\'");
            int salaryCount = jsonObject.getInt("salary_count");
            int numCount = jsonObject.getInt("num_count");

            String query = "";
            if (type == 1) {
                query = "insert into jx_gz_dn_hire(update_time,title,company_name,p_time," +
                        "from_url,list_url,care_num,company_scale,company_nature,company_industry," +
                        "position,num,address," +
                        "linkman, salary, exp, edu, mail,position_desc,company_desc) " +
                        "values(\"" + updateTime + "\",\"" + title + "\", \"" + companyName + "\",\"" + pTime + "\",\""
                        + fromUrl + "\",\"" + listUrl + "\",\"" + careNum + "\",\"" + companyScale+ "\",\"" + companyNature
                        + "\",\"" + companyIndustry + "\",\"" + position + "\",\"" + num + "\",\""
                        + address + "\",\"" + linkman + "\",\"" + salary + "\",\"" + exp+ "\",\""
                        + edu + "\",\""+ mail  + "\",\""+ positionDesc + "\",\"" + companyDesc+ "\")";

            } else {
                query = "update jx_gz_dn_hire set update_time = \"" + updateTime + "\"" +
                        ",title=\""+title+"\",company_name=\""+companyName+"\",p_time=\""+pTime+"\"," +
                        "from_url=\""+fromUrl+"\",list_url=\""+listUrl+"\"," +
                        "care_num=\""+careNum+"\",company_scale=\""+companyScale+"\","+
                        "company_nature=\""+companyNature+"\",company_industry=\""+companyIndustry+"\",position=\""+position+"\",num=\""+num+"\"," +
                        "address=\""+address+"\",linkman=\""+linkman+"\", " +
                        "salary=\""+salary+"\",exp=\""+exp+"\",edu=\""+edu+"\"," +
                        "mail=\""+mail+"\",position_desc=\""+positionDesc+"\"," +
                        "position_desc=\""+positionDesc+"\" " +
                        "where title=\""+title+"\";";
            }

            retNum = jdbcTemplate.update(query);

            //更新高级属性
            String queryJudge = "SELECT p_time,company_name,position FROM jx_gz_dn_hire_count WHERE title = \"" + title + "\";";
            List<JSONObject> list = jdbcTemplate.query(queryJudge, new RowMapper<JSONObject>() {
                @Override
                public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("company_name", resultSet.getString("company_name"));
                    jsonObject.put("position", resultSet.getString("position"));
                    jsonObject.put("p_time", resultSet.getString("p_time"));
                    return jsonObject;
                }
            });

            if (list.size() == 0) {
                //新增
                String queryHireCount = "insert into jx_gz_dn_hire_count(update_time,title,company_name,p_time," +
                        "position,salary_count,num_count) " +
                        "values(\"" + updateTime + "\",\"" + title + "\", \"" + companyName + "\",\"" + pTime + "\",\""
                        +  position + "\",\"" + salaryCount + "\",\"" + numCount+ "\")";
                jdbcTemplate.update(queryHireCount);

            } else{
                JSONObject jsonObject1 = list.get(0);
                String pTimeGet = jsonObject1.getString("p_time");
                    try{
                        if (TransferTime.stringToLong(pTime, DefineOut.TIME_FORMAT) > TransferTime.stringToLong(pTimeGet, DefineOut.TIME_FORMAT)){
                            //只有时间大于记录才更新
                            String queryHireCount = "update jx_gz_dn_hire_count set update_time = \"" + updateTime + "\"" +
                                    ",title=\""+title+"\",company_name=\""+companyName+"\",p_time=\""+pTime+"\"," +
                                    "position=\""+position+"\",salary_count=\""+salaryCount+"\"," +
                                    "num_count=\""+numCount+"\" " +
                                    "where title=\""+title+"\";";
                            jdbcTemplate.update(queryHireCount);
                        }
                    } catch (Exception e) {
                        logger.info("ERROR - storeData0 :" + e);
                    }
             }


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
