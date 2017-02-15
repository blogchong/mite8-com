package com.mite8.jx.gz.dn.service.ask_politics.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.utils.DefineDn;
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
 * Author: blogchong
 * Time:  2016/11/23.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  问政舆情逻辑入口
 */
public class OptPolitics {

    private static final Logger logger = Logger.getLogger(OptPolitics.class.getName());

//    public static void main(String[] args) {
//        optPolitics(new JdbcTemplate());
//    }

    //操作遍历入口
    public static void optPolitics(JdbcTemplate jdbcTemplate){

        long beginTime = TransferTime.dateToLong(new Date())/1000;

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        int num = AnalysisNum();

        //分词相关
        Forest emotionForest = LoadDictionary.dicSegMap.get("emotion");
        Forest houseForest = LoadDictionary.dicSegMap.get("dn_house");
        //词性过滤
        ResultFilter resultFilterEmotion = new ResultFilter();
        resultFilterEmotion.addNatureFilterByNature("emotion");
        ResultFilter resultFilterHouse = new ResultFilter();
        resultFilterHouse.addNatureFilterByNature("dn_house");

        int count = 0;

        if (num == 0) {
            logger.info("ERROR - optPolitics, Please check!");
        } else {

            boolean jumpFlag = false;
            int flagNum = 0;

            for (int i = 0; i < num; i++) {
                //获取请求链接
                String listUrl = "";
                if (i == 0) {
                    listUrl = "http://www.dingnan.gov.cn/wlwz/lxxd/index.html";
                } else {
                    listUrl = "http://www.dingnan.gov.cn/wlwz/lxxd/index_" + i + ".html";
                }

                //是否跳出
                if (jumpFlag) {
                    break;
                }

                //列表信息获取
                JSONArray jsonArray = AnalysisList.analysisList(listUrl);

                int countIn = 0;

                //对列表数据进行处理
                for(int j = 0; j < jsonArray.size(); j++) {
                    try {
                        //解析数据
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        String id = jsonObject.getString("id");
                        String fromUrl = jsonObject.getString("from_url");
                        jsonObject.put("list_url", listUrl);
                        //进行校验
                        if (!CheckAndStore.checkId(jdbcTemplate, id)) {
//                        if (true) {
                            JSONObject jsonObjectStore = AnalysisDetail.analysisDetail(
                                    houseForest, resultFilterHouse,
                                    emotionForest, resultFilterEmotion,
                                    fromUrl, jsonObject);
                            if (jsonObjectStore.containsKey("o_contents")) {
                                CheckAndStore.storeData(jdbcTemplate, jsonObjectStore, updateTime);
                                count++;
                                countIn++;
                                //连续计数归零
                                flagNum = 0;
                            }
                        } else {
                            flagNum++;
                            if (flagNum > 50) {
                                logger.info("INFO - optPolitics - jump out listUrl[" + listUrl+ "] goto[" + i + "] fromUrl[" + fromUrl + "]");
                                jumpFlag = true;
                                break;
                            } else {
                                logger.info("INFO - optPolitics - pass[" + flagNum + "] listUrl[" + listUrl+ "] goto[" + i + "] fromUrl[" + fromUrl + "]");
                            }
                        }

                    }catch (Exception e){
                        logger.info("ERROR - optPolitics - jsonArray(" + j + ") detail:" + e);
                    }
                }
                logger.info("INFO - optPolitics - countIn[" + countIn+ "] count[" + count + "] page[" + i + "]");

            }
        }

        long endTime = TransferTime.dateToLong(new Date())/1000;

        logger.info("TAKE - POLITICS DETAILS  TIME: " + (endTime - beginTime) + "s  Notes: " + count);

    }

    //获取循环次数
    public static int AnalysisNum(){
        //初始链接http://www.dingnan.gov.cn/wlwz/lxxd/index.html

        int num = 0;

        try {
            CrawlDatum crawlDatum = new CrawlDatum("http://www.dingnan.gov.cn/wlwz/lxxd/index.html").putMetaData("method", "POST");

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

            String numTmp = page.select("td[style=font-size:12px; text-align:center;]").toString().replace("\t","").replace("\n","");

            Matcher matcher = DefineDn.patternPoliticsNum.matcher(numTmp);
            String numTmp2 = "";
            if(matcher.find()) {
                numTmp2 = matcher.group(1);
            }

            num = Integer.parseInt(numTmp2);

        } catch (Exception e) {
            logger.info("[ERROR] - AnalysisNum. Detail: " + e);
        }
        return num;
    }

}
