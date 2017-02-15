package com.mite8.Insight.movie_great_wall;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.entity.EmotionEntity;
import com.mite8.jx.gz.dn.utils.DefineDn;
import com.mite8.jx.gz.dn.utils.LoadEmotionDictionary;
import com.mite8.utils.ansj_util.LoadDictionary;
import com.mite8.utils.ansj_util.ResultFilter;
import com.mite8.utils.ansj_util.SegBrandSpeOpt;
import net.sf.json.JSONObject;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.SeeDicAnalysis;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogchong
 * Time:  2016/12/17.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 解析电影《长城》影评
 */
public class AnalysisList {

    private static final Logger logger = Logger.getLogger(AnalysisList.class.getName());

//    public static void main(String[] args){
//        analysisList(null,null,null,null, null,"https://movie.douban.com/subject/6982558/reviews?start=0","","","","");
//    }

    //入参例子：https://movie.douban.com/subject/6982558/reviews
    public static void analysisList(Forest emotionForest, ResultFilter resultFilterEmotion,
                                    Forest starForest, ResultFilter resultFilterStar,
                                         JdbcTemplate jdbcTemplate,String url,String update_time,String m_id, String m_name, String m_url) {

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

            Elements elements = page.select("div[class=main review-item]").select("h3").select("a[class=title-link]");
            Set<String> set = new HashSet<>();
            for(Element element: elements) {

                //直接取链接
                String fromUrlTmp = element.toString();
                Matcher matcher = DefineDn.patternUrl.matcher(fromUrlTmp);
                String fromUrl = "";
                if (matcher.find()) {
                    fromUrl = matcher.group(1);
                }
                JSONObject jsonObject = analysisDetail(emotionForest, resultFilterEmotion,
                        starForest, resultFilterStar,
                        fromUrl,update_time,m_id,m_name,m_url);
                storeDataC(jdbcTemplate, jsonObject.getJSONObject("detail"));
                storeDataE(jdbcTemplate, jsonObject.getJSONObject("emotion"));
                storeDataS(jdbcTemplate, jsonObject.getJSONObject("star"));

            }

        } catch (Exception e) {
            logger.info("[ERROR] - analysisList: url["+ url +"] - Detail: " + e);
        }
    }

    //解析评论的详细数据
    public static JSONObject analysisDetail(Forest emotionForest, ResultFilter resultFilterEmotion,
                                            Forest starForest, ResultFilter resultFilterStar,
                                            String url,String update_time,String m_id, String m_name, String m_url) {

        JSONObject jsonObject_return = new JSONObject();

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("update_time", update_time);
            jsonObject.put("m_id", m_id);
            jsonObject.put("m_name", m_name);
            jsonObject.put("m_url", m_url);

            Matcher matcher_c_id = MovieUtils.patternCID.matcher(url);
            String c_id = "";
            if (matcher_c_id.find()) {
                c_id = matcher_c_id.group(1);
            }
            jsonObject.put("c_id",c_id);

            jsonObject.put("c_url", url);

            CrawlDatum crawlDatum = new CrawlDatum(url).putMetaData("method", "GET");

            HttpRequest request = new HttpRequest(crawlDatum.getUrl());

            request.setMethod(crawlDatum.getMetaData("method"));
            String outputData = crawlDatum.getMetaData("outputData");
            if (outputData != null) {
                request.setOutputData(outputData.getBytes("utf-8"));
            }

            request.setCookie(MovieUtils.cookie);
            request.setUserAgent(MovieUtils.agent);

                        /*
            //通过下面方式可以设置Cookie、User-Agent等http请求头信息
            request.setCookie("xxxxxxxxxxxxxx");
            request.setUserAgent("WebCollector");
            request.addHeader("xxx", "xxxxxxxxx");
            */

            HttpResponse httpResponse = request.getResponse();
            Page page = new Page(crawlDatum, httpResponse);

            String c_title = page.select("title").text();
            jsonObject.put("c_title", c_title);
            int c_level = Integer.parseInt(page.select("span[property=v:rating]").text());
            jsonObject.put("c_level", c_level);
            String c_time = page.select("p[class=main-meta]").text();
            jsonObject.put("c_time", c_time);

            String c_yy_str = page.select("button[class=btn useful_count "+c_id+"]").text();
            Matcher matcher_c_yy = MovieUtils.patternCY.matcher(c_yy_str);
            int c_yy = 0;
            if (matcher_c_yy.find()) {
                c_yy = Integer.parseInt(matcher_c_yy.group(1));
            }

            String c_my_str = page.select("button[class=btn useless_count "+c_id+"]").text();
            Matcher matcher_c_my = MovieUtils.patternCY.matcher(c_my_str);
            int c_my = 0;
            if (matcher_c_my.find()) {
                c_my = Integer.parseInt(matcher_c_my.group(1));
            }

            jsonObject.put("c_yy", c_yy);
            jsonObject.put("c_my", c_my);

            String c_contents = page.select("div[class=review-content clearfix]").text();
            jsonObject.put("c_contents", c_contents);
            jsonObject.put("c_type", "1");

            ///////////情感分析//////////////////////////
            JSONObject jsonObject_emotion = new JSONObject();
            int scorePraise = 0;
            int scoreAnger = 0;
            int scoreSad = 0;
            int scoreFear = 0;
            int scoreHate = 0;
            int scoreShock = 0;
            String wordsList = "";
            Result resultEmotion = SeeDicAnalysis.parse(emotionForest, c_title+","+c_contents);
            resultEmotion = resultFilterEmotion.resultFilterBySpecifyNature(resultEmotion);
            for (int i = 0; i < resultEmotion.size(); i++) {
                String word = resultEmotion.get(i).getName();
                if (LoadEmotionDictionary.dicEmotion.containsKey(word)) {

                    if (wordsList.equals("")) {
                        wordsList = word;
                    } else {
                        wordsList = wordsList + "," + word;
                    }

                    EmotionEntity emotionEntity = LoadEmotionDictionary.dicEmotion.get(word);
                    String type = emotionEntity.getType();
                    double strength = emotionEntity.getStrength();
                    if (type.equals("PD") || type.equals("PH") || type.equals("PG")
                            || type.equals("PB") || type.equals("PK")){
                        scorePraise += strength;
                    } else if (type.equals("NA")){
                        scoreAnger += strength;
                    } else if (type.equals("NB") || type.equals("NJ") || type.equals("NH")
                            || type.equals("PF")){
                        scoreSad += strength;
                    } else if (type.equals("NI") || type.equals("NC") || type.equals("NG")){
                        scoreFear += strength;
                    } else if (type.equals("NE") || type.equals("ND") || type.equals("NN")
                            || type.equals("NK") || type.equals("NL")){
                        scoreHate += strength;
                    } else if (type.equals("PC")){
                        scoreShock += strength;
                    }
                }
            }
            jsonObject_emotion.put("update_time", update_time);
            jsonObject_emotion.put("m_id",m_id);
            jsonObject_emotion.put("c_id", c_id);
            jsonObject_emotion.put("e_type","1");
            jsonObject_emotion.put("score_praise", scorePraise);
            jsonObject_emotion.put("score_anger", scoreAnger);
            jsonObject_emotion.put("score_sad", scoreSad);
            jsonObject_emotion.put("score_fear", scoreFear);
            jsonObject_emotion.put("score_hate", scoreHate);
            jsonObject_emotion.put("score_shock", scoreShock);
            jsonObject_emotion.put("words_list", wordsList);

            ////////////////明星提取//////////////////////
            JSONObject jsonObject_star = new JSONObject();
            Result resultStar = SeeDicAnalysis.parse(starForest, c_title+","+c_contents);
            resultStar = SegBrandSpeOpt.segBrandSpeOpt(resultStar, "movie");
            resultStar = resultFilterStar.resultFilterBySpecifyNature(resultStar);
            resultStar = ResultFilter.resultFilterBySynon(resultStar, LoadDictionary.dicSynonMap.get("movie"), "movie");
            String starsList = "";
            for (int i = 0; i < resultStar.size(); i++) {
                String word = resultStar.get(i).getName();
                if (starsList.equals("")) {
                    starsList = word;
                } else {
                    starsList = starsList + "," + word;
                }
            }
            jsonObject_star.put("update_time", update_time);
            jsonObject_star.put("m_id",m_id);
            jsonObject_star.put("c_id", c_id);
            jsonObject_star.put("c_type","1");
            jsonObject_star.put("stars_list", starsList);

            jsonObject_return.put("detail", jsonObject);
            jsonObject_return.put("emotion", jsonObject_emotion);
            jsonObject_return.put("star",jsonObject_star);

        } catch (Exception e) {
            logger.info("[ERROR] - analysisList: url["+ url +"] - Detail: " + e);
        }
        return jsonObject_return;
    }

    //评论数据入库
    public static boolean storeDataC(JdbcTemplate jdbcTemplate,JSONObject jsonObject) {
        int retNum = 0;
        try {

            jdbcTemplate.update("set names utf8mb4");

            String update_time = jsonObject.getString("update_time");
            String m_id  = jsonObject.getString("m_id");
            String m_name  = jsonObject.getString("m_name");
            String m_url  = jsonObject.getString("m_url");
            String c_id = jsonObject.getString("c_id");
            String c_url = jsonObject.getString("c_url");
            String c_title = jsonObject.getString("c_title").replaceAll("\"","").replaceAll("'","");
            String c_contents = jsonObject.getString("c_contents").replaceAll("\"","").replaceAll("'","");
            String c_time = jsonObject.getString("c_time");

            int c_level = jsonObject.getInt("c_level");
            int c_yy = jsonObject.getInt("c_yy");
            int c_my = jsonObject.getInt("c_my");

            String c_type = jsonObject.getString("c_type");

            String query = "insert into insight_movie_comments(update_time,m_id,m_name,m_url," +
                        "c_id,c_url,c_title,c_contents,c_time," +
                        "c_level,c_yy, c_my,c_type) " +
                        "values(\"" + update_time + "\",\"" + m_id + "\", \"" + m_name + "\",\"" + m_url + "\",\""
                        + c_id + "\",\"" + c_url + "\",\"" + c_title + "\",\"" + c_contents+ "\",\"" + c_time + "\",\""
                        + c_level + "\",\"" + c_yy + "\",\"" + c_my + "\",\"" + c_type+ "\")";


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

    //明星数据入库
    public static boolean storeDataS(JdbcTemplate jdbcTemplate,JSONObject jsonObject) {
        int retNum = 0;
        try {

            jdbcTemplate.update("set names utf8mb4");

            String update_time = jsonObject.getString("update_time");
            String m_id  = jsonObject.getString("m_id");
            String c_id = jsonObject.getString("c_id");
            String c_type = jsonObject.getString("c_type");
            String stars_list = jsonObject.getString("stars_list");

            String query = "insert into insight_movie_star(update_time,m_id,c_id,c_type," +
                    "stars_list) " +
                    "values(\"" + update_time + "\",\"" + m_id + "\", \"" + c_id + "\",\"" + c_type + "\",\""
                    + stars_list+ "\")";


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

    //数据入库
    public static boolean storeDataE(JdbcTemplate jdbcTemplate,JSONObject jsonObject) {
        int retNum = 0;
        try {

            jdbcTemplate.update("set names utf8mb4");

            String update_time = jsonObject.getString("update_time");
            String m_id  = jsonObject.getString("m_id");
            String c_id = jsonObject.getString("c_id");
            String e_type = jsonObject.getString("e_type");

            String words_list =  jsonObject.getString("words_list");
            int score_praise =  jsonObject.getInt("score_praise");
            int score_anger =  jsonObject.getInt("score_anger");
            int score_sad =  jsonObject.getInt("score_sad");
            int score_fear =  jsonObject.getInt("score_fear");
            int score_hate =  jsonObject.getInt("score_hate");
            int score_shock =  jsonObject.getInt("score_shock");

            String query = "insert into insight_movie_emotion(update_time,m_id,c_id,e_type," +
                    "words_list,score_praise,score_anger,score_sad, score_fear,score_hate,score_shock) " +
                    "values(\"" + update_time + "\",\"" + m_id + "\", \"" + c_id + "\",\"" + e_type + "\",\""
                    + words_list + "\",\"" + score_praise + "\",\"" + score_anger + "\",\"" + score_sad+ "\",\"" + score_fear + "\",\""
                    + score_hate + "\",\"" + score_shock+ "\")";


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
