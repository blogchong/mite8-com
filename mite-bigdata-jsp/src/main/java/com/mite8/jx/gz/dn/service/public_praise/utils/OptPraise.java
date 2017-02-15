package com.mite8.jx.gz.dn.service.public_praise.utils;

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
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogchong
 * Time:  2016/11/23.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  问政舆情逻辑入口
 */
public class OptPraise {

    private static final Logger logger = Logger.getLogger(OptPraise.class.getName());

//    public static void main(String[] args) {
//        optPraise(new JdbcTemplate());
//    }

    //操作遍历入口
    public static void optPraise(JdbcTemplate jdbcTemplate){

        long beginTime = TransferTime.dateToLong(new Date())/1000;

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        int num = AnalysisNum();

        //分词相关
        Forest emotionForest = LoadDictionary.dicSegMap.get("emotion");
        //词性过滤
        ResultFilter resultFilterEmotion = new ResultFilter();
        resultFilterEmotion.addNatureFilterByNature("emotion");

        int count = 0;

        if (num == 0) {
            logger.info("ERROR - optPraise, Please check!");
        } else {

            boolean jumpFlag = false;
            int flagNum = 0;

            for (int i = 0; i < (num/20 + 1); i++) {
                //获取请求链接
                String listUrl = "http://news.so.com/ns?q=%E5%AE%9A%E5%8D%97&tn=newstitle&rank=pdate&j=0&pn=" + (i+1);

                //是否跳出
                if (jumpFlag) {
                    break;
                }

                //列表信息获取
                JSONArray jsonArray = AnalysisList.analysisList(emotionForest, resultFilterEmotion,listUrl);

                int countIn = 0;

                //对列表数据进行处理
                for(int j = 0; j < jsonArray.size(); j++) {
                    try {
                        //解析数据
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        String title = jsonObject.getString("title").replace("\"","\\\"").replace("'","\\'");
                        String fromUrl = jsonObject.getString("from_url");
                        //进行校验
                        if (!CheckAndStore.checkTitle(jdbcTemplate, title, fromUrl)) {
                            CheckAndStore.storeDataPraise(jdbcTemplate, jsonObject, updateTime);
                            count++;
                            countIn++;
                            //连续计数归零
                            flagNum = 0;
                        } else {
                            flagNum++;
                            if (flagNum > 50) {
                                logger.info("INFO - optPraise - jump out listUrl[" + listUrl+ "] goto[" + i + "] fromUrl[" + fromUrl + "]");
                                jumpFlag = true;
                                break;
                            } else {
                                logger.info("INFO - optPraise - pass[" + flagNum + "] listUrl[" + listUrl+ "] goto[" + i + "] fromUrl[" + fromUrl + "]");
                            }
                        }

                    }catch (Exception e){
                        logger.info("ERROR - optPraise - num[" + i + "] jsonArray(" + j + ") detail:" + e);
                    }
                }
                logger.info("INFO - optPraise - countIn[" + countIn+ "] count[" + count + "] page[" + i + "]");

            }
        }

        long endTime = TransferTime.dateToLong(new Date())/1000;

        logger.info("TAKE - PRAISE DETAILS  TIME: " + (endTime - beginTime) + "s  Notes: " + count);

    }

    //获取循环次数
    public static int AnalysisNum(){
        //初始链接http://news.so.com/ns?q=%E5%AE%9A%E5%8D%97&tn=newstitle&rank=pdate&j=0&pn=1

        int num = 0;

        try {
            CrawlDatum crawlDatum = new CrawlDatum("http://news.so.com/ns?q=%E5%AE%9A%E5%8D%97&tn=newstitle&rank=pdate&j=0&pn=1").putMetaData("method", "POST");

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

            String numTmp = page.select("span[class=nums]").text();

            Matcher matcher = DefineDn.patternPraiseNum.matcher(numTmp);
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
