package com.mite8.jx.gz.dn.service.public_praise.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.entity.EmotionEntity;
import com.mite8.jx.gz.dn.utils.DefineDn;
import com.mite8.jx.gz.dn.utils.LoadEmotionDictionary;
import com.mite8.utils.DefineOut;
import com.mite8.utils.TransferTime;
import com.mite8.utils.ansj_util.ResultFilter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.SeeDicAnalysis;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nlpcn.commons.lang.tire.domain.Forest;

import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogchong
 * Time:  2016/11/22.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 解析360的新闻列表
 */
public class AnalysisList {

    private static final Logger logger = Logger.getLogger(AnalysisList.class.getName());


//    public static void main(String[] args){
//        analysisList("http://news.so.com/ns?q=定南&tn=newstitle&rank=pdate&j=0&pn=1");
//    }

    //入参例子：http://news.so.com/ns?q=定南&tn=newstitle&rank=pdate&j=0&pn=1
    public static JSONArray analysisList(Forest emotionForest, ResultFilter resultFilterEmotion,String url) {

        JSONArray jsonArray = new JSONArray();

        try {
            CrawlDatum crawlDatum = new CrawlDatum(url).putMetaData("method", "POST");

            HttpRequest request = new HttpRequest(crawlDatum.getUrl());

            request.setMethod(crawlDatum.getMetaData("method"));
            String outputData = crawlDatum.getMetaData("outputData");
            if (outputData != null) {
                request.setOutputData(outputData.getBytes("utf-8"));
            }

            /*
            //通过下面方式可以设置Cookie、User-Agent等http请求头信息
            request.setCookie("xxxxxxxxxxxxxx");
            request.setUserAgent("WebCollector");
            request.addHeader("xxx", "xxxxxxxxx");
            */

            HttpResponse httpResponse = request.getResponse();
            Page page = new Page(crawlDatum, httpResponse);

            int count = 0;
            Elements elements = page.select("li[class=res-list]");
            for(Element element: elements) {
                try {
                    JSONObject jsonObject = new JSONObject();

                    //获取标题
                    String title = element.select("a[class=news_title]").text();
                    //链接
                    String fromUrlTmp = element.select("a[class=news_title]").toString();
                    Matcher matcher = DefineDn.patternUrl.matcher(fromUrlTmp);
                    String fromUrl = "";
                    if (matcher.find()) {
                        fromUrl = matcher.group(1);
                    }
                    //新闻来源网站
                    String newsFrom = element.select("span[class=stname]").text();
                    //获取时间
                    String pTimeTmp = element.select("span[class=pdate]").text();
                    String pTime = "";
                    if (pTimeTmp.contains("今天")) {
                        String today = TransferTime.dateToString(new Date(), DefineOut.TIME_FORMAT);
                        pTime = today + " " + pTimeTmp.split("今天")[1] + ":00";
                    } else if (pTimeTmp.contains("昨天")) {
                        String today = TransferTime.dateToString(new Date(), DefineOut.TIME_FORMAT);
                        pTime = today + " " + pTimeTmp.split("昨天")[1] + ":00";
                    } else {
                        pTime = pTimeTmp + ":00";
                    }

                    jsonObject.put("title", title);
                    jsonObject.put("news_from", newsFrom);
                    jsonObject.put("p_time", pTime);
                    jsonObject.put("from_url", fromUrl);

                    //情感分析
                    int scorePraise = 0;
                    int scoreAnger = 0;
                    int scoreSad = 0;
                    int scoreFear = 0;
                    int scoreHate = 0;
                    int scoreShock = 0;
                    String wordsList = "";
                    Result resultEmotion = SeeDicAnalysis.parse(emotionForest, title);
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
                    jsonObject.put("score_praise", scorePraise);
                    jsonObject.put("score_anger", scoreAnger);
                    jsonObject.put("score_sad", scoreSad);
                    jsonObject.put("score_fear", scoreFear);
                    jsonObject.put("score_hate", scoreHate);
                    jsonObject.put("score_shock", scoreShock);
                    jsonObject.put("words_list", wordsList);

                    jsonArray.add(jsonObject);
                    count++;
                } catch (Exception e) {
                    logger.info("[ERROR] - analysisList - element pass[" + count + "]: url["+ url +"] - Detail: " + e);
                }

            }

        } catch (Exception e) {
            logger.info("[ERROR] - analysisList: url["+ url +"] - Detail: " + e);
        }
        return jsonArray;
    }

}
