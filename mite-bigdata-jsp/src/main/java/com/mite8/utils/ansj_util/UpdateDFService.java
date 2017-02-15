package com.mite8.utils.ansj_util;

import com.mite8.utils.DefineOut;
import com.mite8.utils.MapSort;
import org.ansj.domain.Result;
import org.ansj.recognition.impl.FilterRecognition;
import org.ansj.splitWord.analysis.SeeDicAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/10/31.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  更新文档频服务
 */

@Service
public class UpdateDFService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(UpdateDFService.class.getName());

    public void updateDFService() {
        String query = "SELECT title,contents,comments FROM wechat_kol_content_all";

        List<String> listData = jdbcTemplate.query(query, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("title").trim() + " " + resultSet.getString("contents").trim() + " " + resultSet.getString("comments").trim();
            }
        });

        //分词相关
        Forest segForest = null;
        Map<String, String> synonForest = null;
        List<String> stopForest = null;
        if (LoadDynamicDictionary.dicSegMap.get(DefineOut.DEFAULT_DIC) != null) {
            segForest = LoadDynamicDictionary.dicSegMap.get(DefineOut.DEFAULT_DIC);
        } else {
            segForest = LoadDictionary.dicSegMap.get(DefineOut.DEFAULT_DIC);
        }
        if (LoadDynamicDictionary.dicSynonMap.get(DefineOut.DEFAULT_DIC) != null) {
            synonForest = LoadDynamicDictionary.dicSynonMap.get(DefineOut.DEFAULT_DIC);
        } else {
            synonForest = LoadDictionary.dicSynonMap.get(DefineOut.DEFAULT_DIC);
        }
        if (LoadDynamicDictionary.dicStopMap.get(DefineOut.DEFAULT_DIC) != null) {
            stopForest = LoadDynamicDictionary.dicStopMap.get(DefineOut.DEFAULT_DIC);
        } else {
            stopForest = LoadDictionary.dicStopMap.get(DefineOut.DEFAULT_DIC);
        }

        FilterRecognition filter = new FilterRecognition();
        filter.insertStopWords(stopForest);
        filter = WordNatureFilter.wordNatureFilter(filter);

        int countAll = 0;
        Map<String, Integer> mapDF = new HashMap<>();
        Map<String, String> mapNature = new HashMap<>();
        for (String topic: listData){

            Set<String> setWord = new HashSet<>();
            Result result = ResultFilter.resultFilterByRule(SeeDicAnalysis.parse(segForest, topic).recognition(filter));
            result = ResultFilter.resultFilterBySynon(result, synonForest, DefineOut.DEFAULT_DIC);

            //遍历词汇
            for (int i = 0; i < result.size(); i++) {
                String word = result.get(i).getName();
                String nature = result.get(i).getNatureStr();
                mapNature.put(word, nature);
                setWord.add(word);
            }

            for (String word: setWord){
                if (mapDF.containsKey(word)){
                    mapDF.put(word, mapDF.get(word) + 1);
                } else {
                    mapDF.put(word, 1);
                }
            }

            countAll++;
        }

        //进行df文件落地
        boolean result = storeDF(MapSort.sortByValue(mapDF), mapNature, countAll);

        if (result) {
            logger.info("TASK-UPDATE DYNAMIC DF END!");
        } else {
            logger.info("TASK-UPDATE DYNAMIC DF ERROR!");
        }

    }

    //落地操作
    public static boolean storeDF(Map<String, Integer> mapDF, Map<String, String> mapNature, int num){
        //判断父目录是否存在，不存在则进行
        boolean fileFlag = mkDirDF();

        BufferedWriter  bwDF = null;
        BufferedWriter  bwNum = null;

        if (fileFlag) {
            try {
                bwDF = new BufferedWriter(new BufferedWriter(new OutputStreamWriter
                        (new FileOutputStream(new File("./dic/df/df.dic")), "UTF-8")));
                bwNum = new BufferedWriter(new BufferedWriter(new OutputStreamWriter
                        (new FileOutputStream(new File("./dic/df/num.dic")), "UTF-8")));

                for (String word: mapDF.keySet()){
                    bwDF.write(word + "\t" + mapDF.get(word) + "\t" + mapNature.get(word) + "\n");
                }

                bwNum.write(num + "\n");

                bwDF.close();
                bwNum.close();
            } catch (Exception e) {
                logger.info("DF ERROR: can not find file! " + e);
            }
        }

        return fileFlag;
    }

    //对于父目录进行判断
    public static boolean mkDirDF(){
        File file =new File("./dic/df");
        File file2 =new File("./dic");

        boolean flagDic = false;
        boolean flagDic2 = false;
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory()){
            flagDic = file2 .mkdir();
            flagDic2 = file .mkdir();
        } else{
            flagDic = true;
            flagDic2 = true;
        }

        return (flagDic && flagDic2);

    }

}
