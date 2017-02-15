package com.mite8.jx.gz.dn.service.hire.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import com.mite8.jx.gz.dn.utils.DefineDn;
import com.mite8.utils.DefineOut;
import com.mite8.utils.TransferTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Author: blogchong
 * Time:  2016/11/23.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  招聘舆情逻辑入口
 */
public class OptHire {

    private static final Logger logger = Logger.getLogger(OptHire.class.getName());

//    public static void main(String[] args) {
//        AnalysisNum();
//    }

    //操作遍历入口
    public static void optHire(JdbcTemplate jdbcTemplate){

        long beginTime = TransferTime.dateToLong(new Date())/1000;

        String updateTime = TransferTime.dateToString(new Date(), DefineOut.timeFormat);

        int num = AnalysisNum();

        int count = 0;

        if (num == 0) {
            logger.info("ERROR - optHire, Please check!");
        } else {

            boolean jumpFlag = false;
            int flagNum = 0;

            for (int i = 0; i < num; i++) {
                //获取请求链接
                String listUrl = "http://www.jxdn.ccoo.cn/post/zhaopin/pn" + (i+1) + "/";

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
                        String fromUrl = jsonObject.getString("from_url");
                        JSONObject jsonObjectStore = AnalysisDetail.analysisDetail(fromUrl, jsonObject);
                        String title = jsonObject.getString("title");
                        String pTime = jsonObject.getString("p_time");
                        String companyName = jsonObject.getString("company_name");
                        String position = jsonObject.getString("position");
                        //进行校验
                        int type = CheckAndStore.checkNameAndPTime(jdbcTemplate, title, pTime, companyName, position);
                        if ( type != 0) {
                            CheckAndStore.storeData(jdbcTemplate, jsonObjectStore, updateTime, type);
                            count++;
                            countIn++;
                            //连续标志置0
                            flagNum = 0;
                        } else {
                            flagNum++;
                            //连续50次以上重复 则停止
                            if (flagNum > 50) {
                                logger.info("INFO - optHire - jump out listUrl[" + listUrl+ "] goto[" + i + "] fromUrl[" + fromUrl + "]");
                                jumpFlag = true;
                                break;
                            } else {
                                logger.info("INFO - optHire - pass[" + flagNum + "] listUrl[" + listUrl+ "] goto[" + i + "] fromUrl[" + fromUrl + "]");
                            }
                        }

                    }catch (Exception e){
                        logger.info("ERROR - optHire - jsonArray(" + j + ") detail:" + e);
                    }
                }
                logger.info("INFO - optHire - countIn[" + countIn+ "] count[" + count + "] page[" + i + "]");

            }
        }

        long endTime = TransferTime.dateToLong(new Date())/1000;

        logger.info("TAKE - HIRE DETAILS  TIME: " + (endTime - beginTime) + "s  Notes: " + count);

    }

    //获取循环次数
    public static int AnalysisNum(){
        //初始链接http://www.jxdn.ccoo.cn/post/qiuzhi/pn1/

        int num = 46;

        try {
            CrawlDatum crawlDatum = new CrawlDatum("http://www.jxdn.ccoo.cn/post/zhaopin/pn1/").putMetaData("method", "GET");

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

            Elements elementsNum = page.select("div[id=page_x]").select("a");
            for (Element element: elementsNum) {
                String tmp = element.text().trim();
                if (tmp.equals("最后页")){
                    String tmp2 = element.toString();
                    Matcher matcher = DefineDn.getPatternResumeMaxNum.matcher(tmp2);
                    if (matcher.find()) {
                        num = Integer.parseInt(matcher.group(1));
                    }
                }
            }

        } catch (Exception e) {
            logger.info("[ERROR] - AnalysisNum. Detail: " + e);
        }
        return num;
    }

}
