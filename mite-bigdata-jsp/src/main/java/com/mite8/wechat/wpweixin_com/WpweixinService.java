package com.mite8.wechat.wpweixin_com;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.Insight.movie_great_wall.MovieUtils;
import com.mite8.utils.DefineOut;
import com.mite8.utils.TransferTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Author: blogchong
 * Time:  2016/11/23.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
// * Desc:  http://data.wpweixin.com/微果酱
 */
@Service
public class WpweixinService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String cookie = "_ga=GA1.2.1523211651.1483093510; _gat=1; Hm_lvt_ecf82f2f1ced060ee9a095a6f21792e9=1483459256,1483495005,1483529353,1483600935; Hm_lpvt_ecf82f2f1ced060ee9a095a6f21792e9=1483600935; SID=wxw9iboy6ttof4rjs9nwg6sm; Hm_lvt_753c2243909f3929f565db606d75dae6=1483459257,1483495006,1483529354,1483600938; Hm_lpvt_753c2243909f3929f565db606d75dae6=1483601009";

    private static final Logger logger = Logger.getLogger(WpweixinService.class.getName());

    private static Pattern patternID = Pattern.compile("\" data-sku=\"(\\d+)\" href=");

    private static int sleepTime = 2000;

    public void getWechatData(){
        optWechatData(jdbcTemplate);
    }

//    public static void main(String[] args) {
//        optWechatData(null);
//    }

    //操作遍历入口
    public static void optWechatData4(JdbcTemplate jdbcTemplate, String rankingDate){

        long beginTime = TransferTime.dateToLong(new Date())/1000;
     //12-22号，刚跑完财富，暂停
//        String[] typeListName = {"科技","创业","汽车","楼市","职场","教育","学术","政务","企业","文化","百科","幽默"};
        String[] typeListName = {"时尚","健康","乐活","情感","美食","旅行","体娱","美体","文摘","时事","民生","财富","科技","创业","汽车","楼市","职场","教育","学术","政务","企业","文化","百科","幽默"};

//        String rankingDate = "2016-12-22";

        Random random = new Random();

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        int count_all = 0;

//        String accountType = "时尚";

        for (String accountType: typeListName) {
            //初始总数，默认是
            int total = 100;
            int page_flag = 0;
            int count_type = 0;

            for (int i = 0; i < total; i += 50) {
                String url = "http://data.wpweixin.com/v1/mp/account/rank/daily?" +
                        "rankingDate=" + rankingDate + "&accountType=" + accountType + "&offset=" + i + "&size=50";

                try {

                    CrawlDatum crawlDatum_in = new CrawlDatum(url).putMetaData("method", "GET");

                    HttpRequest request_in = new HttpRequest(crawlDatum_in.getUrl());

                    request_in.setMethod(crawlDatum_in.getMetaData("method"));
                    String outputData_in = crawlDatum_in.getMetaData("outputData");
                    if (outputData_in != null) {
                        request_in.setOutputData(outputData_in.getBytes("utf-8"));
                    }

                    request_in.setCookie(cookie);
                    request_in.setUserAgent(MovieUtils.agent);

                    HttpResponse httpResponse_in = request_in.getResponse();
                    Page page_in = new Page(crawlDatum_in, httpResponse_in);

                    String json_str = page_in.getHtml();

                    if (count_all!=0) {
                        int sleep_time_in = random.nextInt(2000) + 3000;
                        logger.info("[INFO-SLEEP] rankingDate[" + rankingDate + "] accountType[" + accountType + "] total[" + total + "] count_all[" + count_all + "] count_type[" + count_type + "] offset[" + i + "] It's time to sleep[" + sleep_time_in + "].");
                        Thread.sleep(sleep_time_in);
                    }

                    try {
                        JSONObject jsonObject = JSONObject.fromObject(json_str);
                        //解析数据，并且入库

                        JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                        int totalTmp = jsonObjectData.getInt("total");
                        if (total == 100 && totalTmp != 0) {
                            total = totalTmp;
                        }

                        JSONArray jsonArray = jsonObjectData.getJSONArray("list");
                        if (jsonArray != null && jsonArray.size() == 0) {
                            logger.info("[ERROR-JAR] rankingDate[" + rankingDate + "] accountType[" + accountType + "]" +
                                    " total[" + total + "] count_all[" + count_all + "] " +
                                    "count_type[" + count_type + "] offset[" + i + "] url[" + url + "] error[jsonArray=null or jsonArray.size()==0]");
                        } else {
                            //进行正常解析并且入库
                            List<JSONObject> list = new ArrayList<>();
                            for (int j = 0; j < jsonArray.size(); j++) {
                                try {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);

                                    if (jsonObject1.size() >= 24) {
                                        list.add(jsonObject1);
                                        count_all++;
                                        count_type++;
                                    }
                                } catch (Exception e) {
                                    logger.info("[ERROR-IN_JAR] rankingDate[" + rankingDate + "] accountType[" + accountType + "]" +
                                            " total[" + total + "] count_all[" + count_all + "] " +
                                            "count_type[" + count_type + "] offset[" + i + "] note[" + j + "] url[" + url + "] error[" + e + "]");
                                }
                            }

                            //进行入库操作
                            storeListData2(jdbcTemplate, updateTime, rankingDate, list);

                            page_flag++;

//                            logger.info("[INFO-TYPE_PAGE] accountType[" + accountType + "]" +
//                                    " total[" + total + "] count_all[" + count_all + "] " +
//                                    "count_type[" + count_type + "] offset[" + i + "] page_flag[" + page_flag + "] url[" + url + "]");

                        }
                    } catch (Exception e) {
                        logger.info("[ERROR-ANA_HTML] rankingDate[" + rankingDate + "] accountType[" + accountType + "]" +
                                " total[" + total + "] count_all[" + count_all + "] " +
                                "count_type[" + count_type + "] offset[" + i + "] url[" + url + "] error[" + e + "]");
                    }
                } catch (Exception e) {
                    logger.info("[ERROR-ANA_URL] rankingDate[" + rankingDate + "] accountType[" + accountType + "]" +
                            " total[" + total + "] count_all[" + count_all + "] " +
                            "count_type[" + count_type + "] offset[" + i + "] url[" + url + "] error[" + e + "]");

                }
            }
            logger.info("[INFO-TYPE_OVER] rankingDate[" + rankingDate + "] accountType[" + accountType + "]" +
                    " total[" + total + "] count_all[" + count_all + "] " +
                    "count_type[" + count_type + "]");

            try {
                int sleep_time_out = random.nextInt(10000) + 20000;
                logger.info("[INFO-SLEEP_OUT] rankingDate[" + rankingDate + "] accountType[" + accountType + "] total[" + total + "] count_all[" + count_all + "] count_type[" + count_type + "] It's time to sleep[" + sleep_time_out + "].");
                Thread.sleep(sleep_time_out);
            }catch (Exception e){
                logger.info("[ERROR-SLEEP_OUT]");
            }
        }


        long endTime = TransferTime.dateToLong(new Date())/1000;

        logger.info("[INFO-END] rankingDate["+rankingDate+"] times[" + (endTime - beginTime) + "s] count_all:["+count_all+"]");

    }

    public static void optWechatData(JdbcTemplate jdbcTemplate){

        String rankingDate = "";
        for (int j = 12; j > 0; j--) {

            int max = 30;
            if (j==12){
                max = 16;
            } else if (j==2){
                max = 28;
            }else if (j==1 || j==3 || j==5 || j==7 || j==8 || j==10){
                max = 31;
            }

            for (int i = max; i > 0; i--) {
                String month = "12";
                String day = "01";
                if (j<10){
                    month = "0"+j;
                } else {
                    month = j+"";
                }
                if (i<10){
                    day = "0"+i;
                }else {
                    day = i+"";
                }

                rankingDate = "2016-"+month+"-"+day;
                optWechatData4(jdbcTemplate, rankingDate);
            }
        }

    }

    //操作遍历入口
    public static void optWechatData2(JdbcTemplate jdbcTemplate,String rankingDate){

        long beginTime = TransferTime.dateToLong(new Date())/1000;

        String[] typeListName = {"时尚","健康","乐活","情感","美食","旅行","体娱","美体","文摘","时事","民生","财富","科技","创业","汽车","楼市","职场","教育","学术","政务","企业","文化","百科","幽默"};

        Random random = new Random();

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        int count_all = 0;

        //遍历类别
        for (String accountType: typeListName){
            //初始总数，默认是
            int total = 100;
            int page_flag = 0;
            int count_type = 0;

            for (int i = 0; i < total; i+=50) {
                String url = "http://data.wpweixin.com/v1/mp/account/rank/daily?" +
                        "rankingDate="+rankingDate+"&accountType=" + accountType + "&offset="+i+"&size=50";

                    try {

                        CrawlDatum crawlDatum_in = new CrawlDatum(url).putMetaData("method", "GET");

                        HttpRequest request_in = new HttpRequest(crawlDatum_in.getUrl());

                        request_in.setMethod(crawlDatum_in.getMetaData("method"));
                        String outputData_in = crawlDatum_in.getMetaData("outputData");
                        if (outputData_in != null) {
                            request_in.setOutputData(outputData_in.getBytes("utf-8"));
                        }

                        request_in.setCookie(cookie);
                        request_in.setUserAgent(MovieUtils.agent);

                        HttpResponse httpResponse_in = request_in.getResponse();
                        Page page_in = new Page(crawlDatum_in, httpResponse_in);

                        String json_str = page_in.getHtml();
                        try {
                            JSONObject jsonObject = JSONObject.fromObject(json_str);
                            //解析数据，并且入库

                            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                            int totalTmp = jsonObjectData.getInt("total");
                            if (total == 100 && totalTmp != 0){
                                total = totalTmp;
                            }

                            JSONArray jsonArray = jsonObjectData.getJSONArray("list");
                            if (jsonArray!= null && jsonArray.size() == 0) {
                                logger.info("[ERROR-JAR] rankingDate["+rankingDate+"] accountType["+accountType+"]" +
                                        " total["+total+"] count_all["+count_all+"] " +
                                        "count_type["+count_type+"] offset["+i+"] url["+url+"] error[jsonArray=null or jsonArray.size()==0]");
                            } else {
                                //进行正常解析并且入库
                                List<JSONObject> list = new ArrayList<>();
                                for (int j = 0; j < jsonArray.size(); j++) {
                                    try {
                                        JSONObject jsonObject1 = jsonArray.getJSONObject(j);

                                        if (jsonObject1.size() == 24) {
                                            list.add(jsonObject1);
                                            count_all++;
                                            count_type++;
//                                            if (count_all % 3000 == 0 && count_all!=0) {
//                                                int sleep_time_in = random.nextInt(1500) + 500;
//                                                logger.info("[INFO-SLEEP] rankingDate["+rankingDate+"] accountType["+accountType+"] total["+total+"] count_all["+count_all+"] count_type["+count_type+"] offset["+i+"] It's time to sleep[" + sleep_time_in + "].");
//                                                Thread.sleep(sleep_time_in);
//                                            }
                                        }
                                    } catch (Exception e) {
                                        logger.info("[ERROR-IN_JAR] rankingDate["+rankingDate+"] accountType["+accountType+"]" +
                                                " total["+total+"] count_all["+count_all+"] " +
                                                "count_type["+count_type+"] offset["+i+"] note["+j+"] url["+url+"] error["+e+"]");                                    }
                                }

                                //进行入库操作
                                storeListData(jdbcTemplate, updateTime, rankingDate, list);

                                page_flag++;

//                                logger.info("[INFO-TYPE_PAGE] accountType["+accountType+"]" +
//                                        " total["+total+"] count_all["+count_all+"] " +
//                                        "count_type["+count_type+"] offset["+i+"] page_flag["+page_flag+"] url["+url+"]");

                            }
                        } catch (Exception e) {
                            logger.info("[ERROR-ANA_HTML] rankingDate["+rankingDate+"] accountType["+accountType+"]" +
                                    " total["+total+"] count_all["+count_all+"] " +
                                    "count_type["+count_type+"] offset["+i+"] url["+url+"] error["+e+"]");
                        }
                }catch (Exception e){
                        logger.info("[ERROR-ANA_URL] rankingDate["+rankingDate+"] accountType["+accountType+"]" +
                                " total["+total+"] count_all["+count_all+"] " +
                                "count_type["+count_type+"] offset["+i+"] url["+url+"] error["+e+"]");

                }
            }
            logger.info("[INFO-TYPE_OVER] rankingDate["+rankingDate+"] accountType["+accountType+"]" +
                    " total["+total+"] count_all["+count_all+"] " +
                    "count_type["+count_type+"]");
        }

        long endTime = TransferTime.dateToLong(new Date())/1000;

        logger.info("[INFO-END] rankingDate["+rankingDate+"] times[" + (endTime - beginTime) + "s] count_all:["+count_all+"]");

        try {
            int sleep_time_in = random.nextInt(5*1000) + 10*1000;
            logger.info("[INFO-SLEEP_OUT] rankingDate[" + rankingDate + "] It's time to sleep[" + sleep_time_in + "].");
            Thread.sleep(sleep_time_in);
        } catch (Exception e){
            logger.info("[ERROR-SLEEP] I need sleep! error["+e+"]");
        }

    }

    public static void storeListData(JdbcTemplate jdbcTemplate,String update_time, String rankingDate, List<JSONObject> list){
        jdbcTemplate.update("set names utf8mb4");

        String queryInsertKeyWordsTable="insert into wechat_flow_list" +
                "(update_time,rankingDate,stat_id,account_id," +
                "weixin_id,nick_name,account_type,idx1_article_num," +
                "idx2_article_num,orig_article_num,total_article_num,idx1_read_num," +
                "idx2_read_num,orig_read_num,total_read_num,idx1_like_num," +
                "idx2_like_num,orig_like_num,total_like_num,max_read_num," +
                "max_like_num,ave_like_rate,score,main_ranking," +
                "type_ranking,stat_time) values";
        String queryInsertKeyWordsTableTmp = queryInsertKeyWordsTable;
        boolean flagKeyWords = false;

        for (int j = 0; j < list.size(); j++){
            try {
                JSONObject jsonObject = list.get(j);
                String stat_id = jsonObject.getString("stat_id");
                String account_id = jsonObject.getString("account_id");
                String weixin_id = jsonObject.getString("weixin_id");
                String nick_name = jsonObject.getString("nick_name").replaceAll("\"|'", "");
                String account_type = jsonObject.getString("account_type").replaceAll("\"|'", "");
                String idx1_article_num = jsonObject.getString("idx1_article_num");
                String idx2_article_num = jsonObject.getString("idx2_article_num");
                String orig_article_num = jsonObject.getString("orig_article_num");
                String total_article_num = jsonObject.getString("total_article_num");
                String idx1_read_num = jsonObject.getString("idx1_read_num");
                String idx2_read_num = jsonObject.getString("idx2_read_num");
                String orig_read_num = jsonObject.getString("orig_read_num");
                String total_read_num = jsonObject.getString("total_read_num");
                String idx1_like_num = jsonObject.getString("idx1_like_num");
                String idx2_like_num = jsonObject.getString("idx2_like_num");
                String orig_like_num = jsonObject.getString("orig_like_num");
                String total_like_num = jsonObject.getString("total_like_num");
                String max_read_num = jsonObject.getString("max_read_num");
                String max_like_num = jsonObject.getString("max_like_num");
                String ave_like_rate = jsonObject.getString("ave_like_rate");
                String score = jsonObject.getString("score");
                String main_ranking = jsonObject.getString("main_ranking");
                String type_ranking = jsonObject.getString("type_ranking");
                String stat_time = jsonObject.getString("stat_time");

                if (!flagKeyWords) {
                    queryInsertKeyWordsTable = queryInsertKeyWordsTable + "(\"" + update_time + "\", \"" + rankingDate + "\", \"" + stat_id + "\", \"" + account_id
                            + "\",\"" + weixin_id + "\", \"" + nick_name + "\", \"" + account_type + "\", \"" + idx1_article_num
                            + "\", \"" + idx2_article_num + "\", \"" + orig_article_num + "\", \"" + total_article_num + "\", \"" + idx1_read_num
                            + "\", \"" + idx2_read_num + "\", \"" + orig_read_num + "\", \"" + total_read_num + "\", \"" + idx1_like_num
                            + "\", \"" + idx2_like_num + "\", \"" + orig_like_num + "\", \"" + total_like_num + "\", \"" + max_read_num
                            + "\", \"" + max_like_num + "\", \"" + ave_like_rate + "\", \"" + score + "\", \"" + main_ranking
                            + "\", \"" + type_ranking + "\", \"" + stat_time + "\")";
                    flagKeyWords = true;
                } else {
                    queryInsertKeyWordsTable = queryInsertKeyWordsTable + "," +
                            "(\"" + update_time + "\", \"" + rankingDate + "\", \"" + stat_id + "\", \"" + account_id
                            + "\",\"" + weixin_id + "\", \"" + nick_name + "\", \"" + account_type + "\", \"" + idx1_article_num
                            + "\", \"" + idx2_article_num + "\", \"" + orig_article_num + "\", \"" + total_article_num + "\", \"" + idx1_read_num
                            + "\", \"" + idx2_read_num + "\", \"" + orig_read_num + "\", \"" + total_read_num + "\", \"" + idx1_like_num
                            + "\", \"" + idx2_like_num + "\", \"" + orig_like_num + "\", \"" + total_like_num + "\", \"" + max_read_num
                            + "\", \"" + max_like_num + "\", \"" + ave_like_rate + "\", \"" + score + "\", \"" + main_ranking
                            + "\", \"" + type_ranking + "\", \"" + stat_time + "\")";
                }
            }catch (Exception e){
                logger.info("[ERROR-IN_STORE] error["+e+"]");
            }

        }

        if (!queryInsertKeyWordsTableTmp.equals(queryInsertKeyWordsTable)) {
            jdbcTemplate.update(queryInsertKeyWordsTable);
        }else {
            logger.info("[ERROR-OUT_STORE] error[no one good!]");
        }

    }

    public static void storeListData2(JdbcTemplate jdbcTemplate,String update_time, String rankingDate, List<JSONObject> list){
        jdbcTemplate.update("set names utf8mb4");

        String queryInsertKeyWordsTable="insert into wechat_flow_list2" +
                "(update_time,rankingDate,stat_id,account_id," +
                "weixin_id,nick_name,account_type,idx1_article_num," +
                "idx2_article_num,orig_article_num,total_article_num,idx1_read_num," +
                "idx2_read_num,orig_read_num,total_read_num,idx1_like_num," +
                "idx2_like_num,orig_like_num,total_like_num,max_read_num," +
                "max_like_num,ave_like_rate,score,main_ranking," +
                "type_ranking,stat_time) values";
        String queryInsertKeyWordsTableTmp = queryInsertKeyWordsTable;
        boolean flagKeyWords = false;

        for (int j = 0; j < list.size(); j++){
            try {
                JSONObject jsonObject = list.get(j);
                String stat_id = jsonObject.getString("stat_id");
                String account_id = jsonObject.getString("account_id");
                String weixin_id = jsonObject.getString("weixin_id");
                String nick_name = jsonObject.getString("nick_name").replaceAll("\"|'", "");
                String account_type = jsonObject.getString("account_type").replaceAll("\"|'", "");
                String idx1_article_num = jsonObject.getString("idx1_article_num");
                String idx2_article_num = jsonObject.getString("idx2_article_num");
                String orig_article_num = jsonObject.getString("orig_article_num");
                String total_article_num = jsonObject.getString("total_article_num");
                String idx1_read_num = jsonObject.getString("idx1_read_num");
                String idx2_read_num = jsonObject.getString("idx2_read_num");
                String orig_read_num = jsonObject.getString("orig_read_num");
                String total_read_num = jsonObject.getString("total_read_num");
                String idx1_like_num = jsonObject.getString("idx1_like_num");
                String idx2_like_num = jsonObject.getString("idx2_like_num");
                String orig_like_num = jsonObject.getString("orig_like_num");
                String total_like_num = jsonObject.getString("total_like_num");
                String max_read_num = jsonObject.getString("max_read_num");
                String max_like_num = jsonObject.getString("max_like_num");
                String ave_like_rate = jsonObject.getString("ave_like_rate");
                String score = jsonObject.getString("score");
                String main_ranking = jsonObject.getString("main_ranking");
                String type_ranking = jsonObject.getString("type_ranking");
                String stat_time = jsonObject.getString("stat_time");

                if (!flagKeyWords) {
                    queryInsertKeyWordsTable = queryInsertKeyWordsTable + "(\"" + update_time + "\", \"" + rankingDate + "\", \"" + stat_id + "\", \"" + account_id
                            + "\",\"" + weixin_id + "\", \"" + nick_name + "\", \"" + account_type + "\", \"" + idx1_article_num
                            + "\", \"" + idx2_article_num + "\", \"" + orig_article_num + "\", \"" + total_article_num + "\", \"" + idx1_read_num
                            + "\", \"" + idx2_read_num + "\", \"" + orig_read_num + "\", \"" + total_read_num + "\", \"" + idx1_like_num
                            + "\", \"" + idx2_like_num + "\", \"" + orig_like_num + "\", \"" + total_like_num + "\", \"" + max_read_num
                            + "\", \"" + max_like_num + "\", \"" + ave_like_rate + "\", \"" + score + "\", \"" + main_ranking
                            + "\", \"" + type_ranking + "\", \"" + stat_time + "\")";
                    flagKeyWords = true;
                } else {
                    queryInsertKeyWordsTable = queryInsertKeyWordsTable + "," +
                            "(\"" + update_time + "\", \"" + rankingDate + "\", \"" + stat_id + "\", \"" + account_id
                            + "\",\"" + weixin_id + "\", \"" + nick_name + "\", \"" + account_type + "\", \"" + idx1_article_num
                            + "\", \"" + idx2_article_num + "\", \"" + orig_article_num + "\", \"" + total_article_num + "\", \"" + idx1_read_num
                            + "\", \"" + idx2_read_num + "\", \"" + orig_read_num + "\", \"" + total_read_num + "\", \"" + idx1_like_num
                            + "\", \"" + idx2_like_num + "\", \"" + orig_like_num + "\", \"" + total_like_num + "\", \"" + max_read_num
                            + "\", \"" + max_like_num + "\", \"" + ave_like_rate + "\", \"" + score + "\", \"" + main_ranking
                            + "\", \"" + type_ranking + "\", \"" + stat_time + "\")";
                }
            }catch (Exception e){
                logger.info("[ERROR-IN_STORE] error["+e+"]");
            }

        }

        if (!queryInsertKeyWordsTableTmp.equals(queryInsertKeyWordsTable)) {
            jdbcTemplate.update(queryInsertKeyWordsTable);
        }else {
            logger.info("[ERROR-OUT_STORE] rankingDate["+rankingDate+"] error[no one good!]");
        }

    }

}
