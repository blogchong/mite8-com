package com.mite8.Insight.movie_great_wall;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.entity.EmotionEntity;
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
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogchong
 * Time:  2016/12/17.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 解析电影《长城》短评
 */
public class AnalysisListShort {

    private static final Logger logger = Logger.getLogger(AnalysisListShort.class.getName());

//    public static void main(String[] args){
//        analysisList(null, null, null,null,null,"https://movie.douban.com/subject/6982558/comments?start=11022&limit=20&sort=new_score&status=P","","","","");
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

            Elements elements = page.select("div[class=comment-item]");
            for (Element element: elements){

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("update_time", update_time);
                    jsonObject.put("m_id", m_id);
                    jsonObject.put("m_name", m_name);
                    jsonObject.put("m_url", m_url);

                    String c_id_str = element.select("input[type=hidden]").toString();
                    Matcher matcher_c_id = MovieUtils.patternDPCID.matcher(c_id_str);
                    String c_id = "";
                    if (matcher_c_id.find()) {
                        c_id = matcher_c_id.group(1);
                    }
                    jsonObject.put("c_id", c_id);

                    jsonObject.put("c_url", url);

                    jsonObject.put("c_title", "");

                    String c_level_str = element.select("span").toString().replaceAll("\n|\t", ",");
                    Matcher matcher_c_level = MovieUtils.patternDPL.matcher(c_level_str);
                    int c_level = 1;
                    if (matcher_c_level.find()) {
                        c_level = Integer.parseInt(matcher_c_level.group(1)) / 10;
                    }
                    jsonObject.put("c_level", c_level);

                    String c_time = element.select("span[class=comment-time]").text();
                    jsonObject.put("c_time", c_time);

                    int c_yy = Integer.parseInt(element.select("span[class=votes pr5]").text());
                    jsonObject.put("c_yy", c_yy);
                    jsonObject.put("c_my", 0);

                    String c_contents = element.select("p").text();
                    jsonObject.put("c_contents", c_contents);

                    jsonObject.put("c_type", "2");

                    AnalysisList.storeDataC(jdbcTemplate, jsonObject);

                    /////////////////////////////////
                    //情感分析
                    JSONObject jsonObject_emotion = new JSONObject();
                    int scorePraise = 0;
                    int scoreAnger = 0;
                    int scoreSad = 0;
                    int scoreFear = 0;
                    int scoreHate = 0;
                    int scoreShock = 0;
                    String wordsList = "";
                    Result resultEmotion = SeeDicAnalysis.parse(emotionForest, c_contents);
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
                                    || type.equals("PB") || type.equals("PK")) {
                                scorePraise += strength;
                            } else if (type.equals("NA")) {
                                scoreAnger += strength;
                            } else if (type.equals("NB") || type.equals("NJ") || type.equals("NH")
                                    || type.equals("PF")) {
                                scoreSad += strength;
                            } else if (type.equals("NI") || type.equals("NC") || type.equals("NG")) {
                                scoreFear += strength;
                            } else if (type.equals("NE") || type.equals("ND") || type.equals("NN")
                                    || type.equals("NK") || type.equals("NL")) {
                                scoreHate += strength;
                            } else if (type.equals("PC")) {
                                scoreShock += strength;
                            }
                        }
                    }
                    jsonObject_emotion.put("update_time", update_time);
                    jsonObject_emotion.put("m_id", m_id);
                    jsonObject_emotion.put("c_id", c_id);
                    jsonObject_emotion.put("e_type", "2");
                    jsonObject_emotion.put("score_praise", scorePraise);
                    jsonObject_emotion.put("score_anger", scoreAnger);
                    jsonObject_emotion.put("score_sad", scoreSad);
                    jsonObject_emotion.put("score_fear", scoreFear);
                    jsonObject_emotion.put("score_hate", scoreHate);
                    jsonObject_emotion.put("score_shock", scoreShock);
                    jsonObject_emotion.put("words_list", wordsList);

                    AnalysisList.storeDataE(jdbcTemplate, jsonObject_emotion);

                    ////////////////明星提取//////////////////////
                    JSONObject jsonObject_star = new JSONObject();
                    Result resultStar = SeeDicAnalysis.parse(starForest, c_contents);
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
                    jsonObject_star.put("c_type","2");
                    jsonObject_star.put("stars_list", starsList);

                    AnalysisList.storeDataS(jdbcTemplate, jsonObject_star);

                }catch (Exception e){
                    logger.info("[ERROR] e:" + e);
                }

            }


        } catch (Exception e) {
            logger.info("[ERROR] - analysisList: url["+ url +"] - Detail: " + e);
        }
    }

}
