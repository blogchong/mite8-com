package com.mite8.Insight.jd_wumai;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.Insight.movie_great_wall.MovieUtils;
import com.mite8.utils.DefineOut;
import com.mite8.utils.TransferTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: blogchong
 * Time:  2016/11/23.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
// * Desc:  抓取京东评论
 */
@Service
public class OptJDcomments {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = Logger.getLogger(OptJDcomments.class.getName());

    private static Pattern patternID = Pattern.compile("\" data-sku=\"(\\d+)\" href=");
    private static int S_NUM = 5;
    private static int sleepTime = 2000;

    public void getJDComments(){
        optJDComments(jdbcTemplate);
    }

//    public static void main(String[] args) {
//        optJDComments(null);
//    }

    //https://search.jd.com/Search?keyword=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&psort=4&page=1&s=1&click=0

    //操作遍历入口
    public static void optJDComments(JdbcTemplate jdbcTemplate){

        long beginTime = TransferTime.dateToLong(new Date())/1000;

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        String url = "https://search.jd.com/Search?keyword=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&psort=4&page=1&click=0";

//        int num = AnalysisNum(url);

        Random random = new Random();

        int num = 63;

        int count = 0;
        int count_all = 0;

        if (num == 0) {
            logger.info("ERROR - optMovie, Please check!");
        } else {

            for (int i = 0; i < num; i++) {
//                String listUrl = "https://search.jd.com/Search?keyword=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&psort=4&page=" + ((i + 1) * 2 - 1) + "&click=0";
            String listUrl = "https://search.jd.com/Search?keyword=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E9%9B%BE%E9%9C%BE%E5%8F%A3%E7%BD%A9&psort=4&page=" + ((i + 1) * 2 - 1) + "&s=61&click=0";
                try {

                    CrawlDatum crawlDatum = new CrawlDatum(listUrl).putMetaData("method", "GET");

                    HttpRequest request = new HttpRequest(crawlDatum.getUrl());

                    request.setMethod(crawlDatum.getMetaData("method"));
                    String outputData = crawlDatum.getMetaData("outputData");
                    if (outputData != null) {
                        request.setOutputData(outputData.getBytes("utf-8"));
                    }

                    request.setCookie(MovieUtils.jd_cookie);
                    request.setUserAgent(MovieUtils.agent);

                    HttpResponse httpResponse = request.getResponse();
                    Page page = new Page(crawlDatum, httpResponse);

                    Elements elements_ids = page.select("div[class=p-operate]");
                    for (Element element: elements_ids){
                        Elements elements_ids2 = element.select("a");
                        for (Element element1: elements_ids2){
                            String str = element1.toString();
                            Matcher matcher = patternID.matcher(str);
                            String id = "";
                            if (matcher.find()) {
                                id = matcher.group(1);
                            }
                            int count_id = 0;
                            if (!id.equals("")){
                                boolean b_flag = true;
                                int c_flag = 0;
                                int page_flag = 0;
                                while (b_flag) {
                                    try {

                                        if (count_all%1000 == 0){
                                            int sleep_time_in = random.nextInt(3000) + 1000;
                                            logger.info("[INFO] It's time to sleep["+sleep_time_in+"].");
                                            Thread.sleep(sleep_time_in);
                                        }

                                        String c_url = "https://sclub.jd.com/comment/productPageComments.action?productId=" + id + "&score=0&sortType=3&page=" + page_flag + "&pageSize=10&isShadowSku=0";
                                        CrawlDatum crawlDatum_in = new CrawlDatum(c_url).putMetaData("method", "GET");

                                        HttpRequest request_in = new HttpRequest(crawlDatum_in.getUrl());

                                        request_in.setMethod(crawlDatum_in.getMetaData("method"));
                                        String outputData_in = crawlDatum_in.getMetaData("outputData");
                                        if (outputData_in != null) {
                                            request_in.setOutputData(outputData_in.getBytes("utf-8"));
                                        }

                                        request_in.setCookie(MovieUtils.jd_cookie);
                                        request_in.setUserAgent(MovieUtils.agent);

                                        HttpResponse httpResponse_in = request_in.getResponse();
                                        Page page_in = new Page(crawlDatum_in, httpResponse_in);

                                        String json_str = page_in.getHtml();
                                        try {
                                            JSONObject jsonObject = JSONObject.fromObject(json_str);
                                            //解析数据，并且入库
                                            JSONArray jsonArray = jsonObject.getJSONArray("comments");
                                            if (c_flag < S_NUM && jsonArray.size() == 0) {
                                                //试错累计
                                                c_flag++;
                                            } else if (c_flag < S_NUM && jsonArray.size() != 0) {
                                                //进行正常解析并且入库
                                                List<JSONObject> list = new ArrayList<>();
                                                for (int j = 0; j < jsonArray.size(); j++) {
                                                    try {
                                                        JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                                                        JSONObject jsonObject2 = new JSONObject();
                                                        jsonObject2.put("p_id",id);
                                                        jsonObject2.put("id", jsonObject1.getString("id"));
                                                        jsonObject2.put("content", jsonObject1.getString("content"));
                                                        jsonObject2.put("creationTime", jsonObject1.getString("creationTime"));
                                                        String referenceTime = jsonObject1.getString("referenceTime");
                                                        jsonObject2.put("referenceTime", referenceTime);
                                                        jsonObject2.put("referenceName", jsonObject1.getString("referenceName"));
                                                        jsonObject2.put("userProvince", jsonObject1.getString("userProvince"));
                                                        jsonObject2.put("productColor", jsonObject1.getString("productColor"));

                                                        String[] reference_tmp = referenceTime.split(" ");
                                                        jsonObject2.put("referenceMonth", reference_tmp[0].split("-")[1]);
                                                        jsonObject2.put("referenceDay", reference_tmp[0]);
                                                        jsonObject2.put("referenceHours", reference_tmp[1].split(":")[0]);
                                                        list.add(jsonObject2);
                                                        count_id++;
                                                        count_all++;
                                                    } catch (Exception e) {
                                                        logger.info("[ERROR] c_id[" + id + "] c_url[" + c_url + "] comments get error: " + e);
                                                    }
                                                }

                                                //进行入库操作
                                                storeListData(jdbcTemplate, updateTime, list);

                                                logger.info("[INFO] count page["+i+"] page_flag["+page_flag+"] productId[" + id + "] count_all["+count_all+"] count_id[" + count_id + "]");

                                                page_flag++;
                                                c_flag=0;

                                            } else {
                                                b_flag = false;
                                            }
                                        } catch (Exception e) {
                                            logger.info("[ERROR] c_url[" + c_url + "] comments get error: " + e);
                                            c_flag++;
                                            page_flag++;
                                            if (c_flag >= S_NUM) {
                                                b_flag = false;
                                            }
                                        }
                                    }catch (Exception e){
                                        int sleep = sleepTime + random.nextInt(3000);
                                        logger.info("[ERROR] id[" + id + "] sleep["+sleep+"] comments get error: " + e);
                                        c_flag++;
                                        if (c_flag >= S_NUM) {
                                            b_flag = false;
                                        }
                                        Thread.sleep(sleep);
                                    }
                                }
                            }
                        }
                    }

                } catch (Exception e){
                    logger.info("[E]");
                }
                count++;
                logger.info("INFO - COMMENTS, count["+count+"] list_url:"+listUrl);
            }
        }

        long endTime = TransferTime.dateToLong(new Date())/1000;

        logger.info("TAKE - COMMENTS  TIME: " + (endTime - beginTime) + "s  Notes: " + count);
    }

    public static void storeListData(JdbcTemplate jdbcTemplate,String update_time, List<JSONObject> list){
        jdbcTemplate.update("set names utf8mb4");

        String queryInsertKeyWordsTable="insert into insight_jd_comments" +
                "(update_time,p_id,id,content,creationTime,referenceTime," +
                "referenceName,userProvince,productColor," +
                "referenceMonth,referenceDay,referenceHours) values";
        boolean flagKeyWords = false;


        for (int j = 0; j < list.size(); j++){
            JSONObject jsonObject = list.get(j);
            String p_id = jsonObject.getString("p_id");
            String id = jsonObject.getString("id");
            String content = jsonObject.getString("content");
            String creationTime = jsonObject.getString("creationTime");
            String referenceTime = jsonObject.getString("referenceTime");
            String referenceName = jsonObject.getString("referenceName");
            String userProvince = jsonObject.getString("userProvince");
            String productColor = jsonObject.getString("productColor");
            String referenceMonth = jsonObject.getString("referenceMonth");
            String referenceDay = jsonObject.getString("referenceDay");
            String referenceHours = jsonObject.getString("referenceHours");

            //update_time,wechat_id,type,key_word,score
            if (!flagKeyWords) {
                queryInsertKeyWordsTable = queryInsertKeyWordsTable + "(\"" + update_time + "\", \"" + p_id + "\", \"" + id
                        + "\",\"" + content + "\", \"" + creationTime + "\", \"" + referenceTime  + "\", \"" + referenceName
                        + "\", \"" + userProvince + "\", \"" + productColor
                        + "\", \"" + referenceMonth + "\", \"" + referenceDay + "\", \"" + referenceHours + "\")";
                flagKeyWords = true;
            } else {
                queryInsertKeyWordsTable = queryInsertKeyWordsTable + "," +
                        "(\"" + update_time + "\", \"" + p_id + "\", \"" + id
                        + "\",\"" + content + "\", \"" + creationTime + "\", \"" + referenceTime  + "\", \"" + referenceName
                        + "\", \"" + userProvince + "\", \"" + productColor
                        + "\", \"" + referenceMonth + "\", \"" + referenceDay + "\", \"" + referenceHours + "\")";
            }

        }

        jdbcTemplate.update(queryInsertKeyWordsTable);

    }

    //获取循环次数
    public static int AnalysisNum(String url) {
        //初始链接https://movie.douban.com/subject/6982558/comments?start=0&limit=20&sort=new_score&status=P

        int num = 63;

        try {
            CrawlDatum crawlDatum = new CrawlDatum(url).putMetaData("method", "GET");

            HttpRequest request = new HttpRequest(crawlDatum.getUrl());

            request.setMethod(crawlDatum.getMetaData("method"));
            String outputData = crawlDatum.getMetaData("outputData");
            if (outputData != null) {
                request.setOutputData(outputData.getBytes("utf-8"));
            }

            request.setCookie(MovieUtils.cookie);
            request.setUserAgent(MovieUtils.agent);

            HttpResponse httpResponse = request.getResponse();
            Page page = new Page(crawlDatum, httpResponse);

            String num_str = page.select("li[class=is-active]").text();
            Matcher matcher = MovieUtils.patternDP.matcher(num_str);
            if (matcher.find()) {
                num = Integer.parseInt(matcher.group(1));
            }

        } catch (Exception e) {
            logger.info("[ERROR] - analysisList: url["+ url +"] - Detail: " + e);
        }
        return num;
    }


}
