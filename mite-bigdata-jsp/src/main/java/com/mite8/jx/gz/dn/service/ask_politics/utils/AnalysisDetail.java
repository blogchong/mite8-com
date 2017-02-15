package com.mite8.jx.gz.dn.service.ask_politics.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.entity.EmotionEntity;
import com.mite8.jx.gz.dn.utils.LoadEmotionDictionary;
import com.mite8.utils.ansj_util.LoadDictionary;
import com.mite8.utils.ansj_util.ResultFilter;
import net.sf.json.JSONObject;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.SeeDicAnalysis;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nlpcn.commons.lang.tire.domain.Forest;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/11/22.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 解析问政详情页
 */
public class AnalysisDetail {

    private static final Logger logger = Logger.getLogger(AnalysisDetail.class.getName());


//    public static void main(String[] args){
//        analysisDetail("http://www.dingnan.gov.cn/wlwz/bmwz/gtj/201611/t20161107_430218.html", new JSONObject());
//    }

    //入参例子：http://www.dingnan.gov.cn/wlwz/bmwz/gtj/201611/t20161107_430218.html
    public static JSONObject analysisDetail(Forest houseForest, ResultFilter resultFilterHouse,
                                            Forest emotionForest, ResultFilter resultFilterEmotion,
                                            String url, JSONObject jsonObject) {

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

            String contents = page.select("div[class=hmwz_xx_titborr2]").text();

            Elements elementsAsk = page.select("div[class=hmw_xx_botr_r]");
            String asker = "";
            for (Element element: elementsAsk){
                if (element.select("div[class=hmw_xx_botr_rlef]").text().equals("咨 询 人")){
                    asker = element.select("div[class=hmw_xx_botr_rrig]").text().replace("   ", "");
                }
            }

            Elements elementsOpt = page.select("div[class=hmwz_bor]");
            String oSection = "";
            String oContents = page.select("div[style=border:0px; width:800px; padding-left:8px; _padding-left:4px; height:92px; LINE-HEIGHT: 20px; OVERFLOW: auto; ]").text().trim();
            String oTime = "";
            for(Element element: elementsOpt){
                if (element.select("div[class=hmwz_xx_titborl]").text().equals("处理部门")){
                    oSection = element.select("div[class=hmw_xx_botr_l]").text().replace("   ", "");
                    oTime = element.select("div[class=hmw_xx_botr_rrig]").text().replace("   ", "");
                }
            }

            String title = jsonObject.getString("title");
            jsonObject.put("contents", contents);
            jsonObject.put("asker", asker);
            jsonObject.put("o_section",oSection);
            jsonObject.put("o_time", oTime);
            jsonObject.put("o_contents", oContents);

            //进行楼盘词汇提取
            Result resultHouse = SeeDicAnalysis.parse(houseForest, title + " " + contents);
            resultHouse = resultFilterHouse.resultFilterBySpecifyNature(resultHouse);
            resultHouse = ResultFilter.resultFilterBySynon(resultHouse, LoadDictionary.dicSynonMap.get("dn_house"), "dn_house");
            resultHouse = ResultFilter.resultSetMeger(resultHouse);
            String houseWords = "";
            for (int i = 0; i < resultHouse.size(); i++) {
                String words = resultHouse.get(i).getName();
                if (houseWords.equals("")) {
                    houseWords = words;
                } else {
                    houseWords = houseWords + "," + words;
                }
            }
            jsonObject.put("house_words", houseWords);

            //情感分析
            int scorePraise = 0;
            int scoreAnger = 0;
            int scoreSad = 0;
            int scoreFear = 0;
            int scoreHate = 0;
            int scoreShock = 0;
            String wordsList = "";
            Result resultEmotion = SeeDicAnalysis.parse(emotionForest, title + " " + contents);
            resultEmotion = resultFilterEmotion.resultFilterBySpecifyNature(resultEmotion);
//            resultEmotion = ResultFilter.resultSetMeger(resultEmotion);
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


        } catch (Exception e) {
            logger.info("[ERROR] - analysisDetail: url["+ url +"] - Detail: " + e);
        }
        return jsonObject;
    }

}
