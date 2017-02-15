package com.mite8.Insight.movie_great_wall;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.utils.DefineDn;
import com.mite8.utils.CutDoubleValue;
import com.mite8.utils.DefineOut;
import com.mite8.utils.TransferTime;
import com.mite8.utils.ansj_util.LoadDictionary;
import com.mite8.utils.ansj_util.ResultFilter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogc
 * Time:  2016/11/23.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
// * Desc:  豆瓣影评入口,解析全局数据，包括翻页数量，电影名字，导演，主演，类型，地区，上映时间，记录时间，影评总数，1/2/3/4/5各星的数量，
 */
public class OptMovie {

    private static final Logger logger = Logger.getLogger(OptMovie.class.getName());
    public static String id = "6982558";
//    public static void main(String[] args) {
//        AnalysisNum(id,"2016-12-17");
//    }

    //操作遍历入口
    public static void optMovie(String id,JdbcTemplate jdbcTemplate){

        long beginTime = TransferTime.dateToLong(new Date())/1000;

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        //分词相关
        Forest emotionForest = LoadDictionary.dicSegMap.get("emotion");
        Forest starForest = LoadDictionary.dicSegMap.get("movie");
        //词性过滤
        ResultFilter resultFilterEmotion = new ResultFilter();
        resultFilterEmotion.addNatureFilterByNature("emotion");

        ResultFilter resultFilterStar = new ResultFilter();
        resultFilterStar.addNatureFilterByNature("movie");

        JSONObject jsonObject_global = AnalysisNum(id,updateTime);

        int type = CheckAndStore.checkNameAndPTime(jdbcTemplate, id);
        CheckAndStore.storeData(jdbcTemplate,jsonObject_global, type);

        int num = jsonObject_global.getInt("m_page");
        String m_name = jsonObject_global.getString("m_name");
        String m_url = jsonObject_global.getString("m_url");

        int count = 0;

        if (num == 0) {
            logger.info("ERROR - optMovie, Please check!");
        } else {

            for (int i = 0; i < num; i++) {
                //获取请求链接
                String listUrl = "https://movie.douban.com/subject/"+id+"/reviews?start=" + i*20;

                AnalysisList.analysisList(emotionForest, resultFilterEmotion,
                        starForest, resultFilterStar,
                        jdbcTemplate,listUrl,updateTime,id,m_name,m_url);
                count++;
                    logger.info("INFO - COMMENTS, count["+count+"] list_url:"+listUrl);
            }
        }

        long endTime = TransferTime.dateToLong(new Date())/1000;

        logger.info("TAKE - COMMENTS  TIME: " + (endTime - beginTime) + "s  Notes: " + count);

//        ////////////短评分隔线//////////////////////
//        int num_s = AnalysisNum2(id);
//        int count_c = 0;
//        if (num_s == 0) {
//            logger.info("ERROR - optMovie, Please check!");
//        } else {
//
//            for (int i = 0; i < num_s; ) {
//
//                //获取请求链接
//                String listUrl = "https://movie.douban.com/subject/6982558/comments?start="+i+"&limit=20&sort=new_score&status=P";
//
//                if (i==0){
//                    i=22;
//                } else {
//                    i+=20;
//                }
//
//                AnalysisListShort.analysisList(emotionForest, resultFilterEmotion,
//                        starForest, resultFilterStar,
//                        jdbcTemplate,listUrl,updateTime,id,m_name,m_url);
//                count_c++;
//
//                logger.info("INFO - COMMENTS-S, count_c["+count_c+"] list_url:"+listUrl);
//            }
//        }
//
//        long endTime2 = TransferTime.dateToLong(new Date())/1000;
//
//        logger.info("TAKE - COMMENTS-S  TIME: " + (endTime2 - endTime) + "s  Notes: " + count_c);

    }

    //获取循环次数
    public static int AnalysisNum2(String id) {
        //初始链接https://movie.douban.com/subject/6982558/comments?start=0&limit=20&sort=new_score&status=P

        String url = "https://movie.douban.com/subject/"+id+"/comments?start=0&limit=20&sort=new_score&status=P";

        int num = 24054;

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

        //获取循环次数
    public static JSONObject AnalysisNum(String id, String update_time){
        //初始链接https://movie.douban.com/subject/6982558/reviews?start=0
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("update_time", update_time);
            jsonObject.put("m_id", id);

            //////////////////////直接解析全局信息////////////////////////
            String url_global = "https://movie.douban.com/subject/"+id+"/";
            String url_comments_1 = "https://movie.douban.com/subject/"+id+"/reviews";

            CrawlDatum crawlDatum_g = new CrawlDatum(url_global).putMetaData("method", "GET");
            CrawlDatum crawlDatum_c = new CrawlDatum(url_comments_1).putMetaData("method", "GET");

            HttpRequest request_global = new HttpRequest(crawlDatum_g.getUrl());
            HttpRequest request_comments = new HttpRequest(crawlDatum_c.getUrl());

            request_global.setMethod(crawlDatum_g.getMetaData("method"));
            String outputData_global = crawlDatum_g.getMetaData("outputData");
            if (outputData_global != null) {
                request_global.setOutputData(outputData_global.getBytes("utf-8"));
            }
            request_comments.setMethod(crawlDatum_c.getMetaData("method"));
            String outputData_comments = crawlDatum_c.getMetaData("outputData");
            if (outputData_comments != null) {
                request_comments.setOutputData(outputData_comments.getBytes("utf-8"));
            }

            request_global.setCookie(MovieUtils.cookie);
            request_global.setUserAgent(MovieUtils.agent);

            request_comments.setCookie(MovieUtils.cookie);
            request_comments.setUserAgent(MovieUtils.agent);

            HttpResponse httpResponse_g = request_global.getResponse();
            HttpResponse httpResponse_c = request_comments.getResponse();

            Page page_g = new Page(crawlDatum_g, httpResponse_g);
            Page page_c = new Page(crawlDatum_c, httpResponse_c);

            //名称
            String m_name = page_g.select("span[property=v:itemreviewed]").text();
            jsonObject.put("m_name", m_name);

            //年份
            String m_year = page_g.select("span[class=year]").text();
            Matcher matcher = MovieUtils.patternKH.matcher(m_year);
            if (matcher.find()) {
                m_year = matcher.group(1);
            }
            jsonObject.put("m_year", m_year);

            //导演
            String m_dy = page_g.select("a[rel=v:directedBy]").text();
            jsonObject.put("m_dy", m_dy);

            //编剧
            jsonObject.put("m_bj", "");

            //演员
            String m_zy = page_g.select("span[class=actor]").select("span[class=attrs]").text();
            jsonObject.put("m_zy", m_zy);

            //类型
            String m_type = page_g.select("span[property=v:genre]").text();
            jsonObject.put("m_type", m_type);

            //地区
            jsonObject.put("m_area", "");

            //上映时间
            String m_time = page_g.select("span[property=v:initialReleaseDate]").text();
            jsonObject.put("m_time", m_time);

            //评分
            double m_score = Double.parseDouble(page_g.select("strong[class=ll rating_num]").text());
            jsonObject.put("m_score", m_score);

            //人数
            int m_num = Integer.parseInt(page_g.select("span[property=v:votes]").text());
            jsonObject.put("m_num", m_num);

            //具体星级人数
            Elements elements_score = page_g.select("span[class=rating_per]");
            int m_1_num = 0;int m_2_num = 0;int m_3_num = 0;int m_4_num = 0;int m_5_num = 0;
            if(elements_score.size() == 5){
                int count_score = 1;
                for(Element element: elements_score) {
                    double num_tmp = 0;
                    Matcher matcher_score = MovieUtils.patternScore.matcher(element.text());
                    if (matcher_score.find()) {
                        num_tmp = Double.parseDouble(matcher_score.group(1)) / 100 * m_num;
                    }
                    if (count_score == 1) {
                        m_1_num = (int)num_tmp;
                    }else if (count_score == 2) {
                        m_2_num = (int)num_tmp;
                    }else if (count_score == 3) {
                        m_3_num = (int)num_tmp;
                    }else if (count_score == 4) {
                        m_4_num = (int)num_tmp;
                    }else if (count_score == 5) {
                        m_5_num = (int)num_tmp;
                    }
                    count_score++;
                }
            }
            jsonObject.put("m_1_num", m_1_num);
            jsonObject.put("m_2_num", m_2_num);
            jsonObject.put("m_3_num", m_3_num);
            jsonObject.put("m_4_num", m_4_num);
            jsonObject.put("m_5_num", m_num-(m_1_num+m_2_num+m_3_num+m_4_num));

            //评论星级
            int m_c_num = 0;int m_c_1_num = 0;int m_c_2_num = 0;int m_c_3_num = 0;int m_c_4_num = 0;int m_c_5_num = 0;
            String m_c_num_str = page_c.select("a[href=?rating=]").text();
            Matcher matcher_m_c_num = MovieUtils.patternScoreCommentsAll.matcher(m_c_num_str);
            if (matcher_m_c_num.find()) {
                m_c_num = Integer.parseInt(matcher_m_c_num.group(1));
            }
            String m_c_1_num_str = page_c.select("a[href=?rating=1]").text();
            Matcher matcher_m_c_1_num = MovieUtils.patternScoreCommentsEach.matcher(m_c_1_num_str);
            if (matcher_m_c_1_num.find()) {
                m_c_1_num = Integer.parseInt(matcher_m_c_1_num.group(1));
            }
            String m_c_2_num_str = page_c.select("a[href=?rating=2]").text();
            Matcher matcher_m_c_2_num = MovieUtils.patternScoreCommentsEach.matcher(m_c_2_num_str);
            if (matcher_m_c_2_num.find()) {
                m_c_2_num = Integer.parseInt(matcher_m_c_2_num.group(1));
            }
            String m_c_3_num_str = page_c.select("a[href=?rating=3]").text();
            Matcher matcher_m_c_3_num = MovieUtils.patternScoreCommentsEach.matcher(m_c_3_num_str);
            if (matcher_m_c_3_num.find()) {
                m_c_3_num = Integer.parseInt(matcher_m_c_3_num.group(1));
            }
            String m_c_4_num_str = page_c.select("a[href=?rating=4]").text();
            Matcher matcher_m_c_4_num = MovieUtils.patternScoreCommentsEach.matcher(m_c_4_num_str);
            if (matcher_m_c_4_num.find()) {
                m_c_4_num = Integer.parseInt(matcher_m_c_4_num.group(1));
            }
            String m_c_5_num_str = page_c.select("a[href=?rating=5]").text();
            Matcher matcher_m_c_5_num = MovieUtils.patternScoreCommentsEach.matcher(m_c_5_num_str);
            if (matcher_m_c_5_num.find()) {
                m_c_5_num = Integer.parseInt(matcher_m_c_5_num.group(1));
            }
            double m_c_score = CutDoubleValue.cutDoubleValue((double)(m_5_num*5+m_4_num*4+m_3_num*3+m_2_num*2+m_1_num*1)*2/(double)(m_5_num+m_4_num+m_3_num+m_2_num+m_1_num), 2);

            jsonObject.put("m_c_score", m_c_score);
            jsonObject.put("m_c_num", m_c_num);
            jsonObject.put("m_c_1_num", m_c_1_num);
            jsonObject.put("m_c_2_num", m_c_2_num);
            jsonObject.put("m_c_3_num", m_c_3_num);
            jsonObject.put("m_c_4_num", m_c_4_num);
            jsonObject.put("m_c_5_num", m_c_5_num);

            jsonObject.put("m_url", url_global);

            //评论页码
            String m_page_str = page_c.select("span[class=thispage]").toString();
            int m_page = 87;
            Matcher matcher_m_page = MovieUtils.patternPage.matcher(m_page_str);
            if (matcher_m_page.find()) {
                m_page = Integer.parseInt(matcher_m_page.group(1));
            }
            jsonObject.put("m_page", m_page);

        } catch (Exception e) {
            logger.info("[ERROR] - AnalysisNum. Detail: " + e);
        }
        return jsonObject;
    }

}
