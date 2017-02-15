package com.mite8.jx.gz.dn.utils;

import com.mite8.jx.gz.dn.entity.EmotionEntity;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.util.StringUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Author: blogchong
 * Time:  2016/10/14.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  情感字典的自动加载。
 */

@Service
public class LoadEmotionDictionary {

    private static final Logger logger = Logger.getLogger(LoadEmotionDictionary.class.getName());

    public static Map<String, EmotionEntity> dicEmotion = new HashMap<>();

    public LoadEmotionDictionary() throws Exception{
        loadEmotionDic("/dic/emotion/emotion.dic", "emotion");
    }

    //加载分词字典
    private static Forest loadEmotionDic(String path, String nature) throws Exception{

        Forest forest = new Forest();

        InputStream  input = LoadEmotionDictionary.class.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String temp = null;
        String[] strs;
        Value value;
        int count = 0;
        int errorCount = 0;
        int errorCountOut = 0;
        try {
            while ((temp = br.readLine()) != null) {
                //获取到resource中的每行
                if (StringUtil.isNotBlank(temp)) {
                    temp = StringUtil.trim(temp);
                    strs = temp.split("\t");
                    if(strs.length >= 7) {
                        try {
                            EmotionEntity emotionEntity = new EmotionEntity();
                            emotionEntity.setWord(strs[0]);
                            emotionEntity.setType(strs[4]);
                            emotionEntity.setStrength(Integer.parseInt(strs[5]));
                            emotionEntity.setPolar(Integer.parseInt(strs[6]));
                            dicEmotion.put(strs[0], emotionEntity);
                            count++;
                        } catch (Exception e){
                            errorCount++;
                        }
                    } else {
//                        logger.info("ERROR - DIC-EMOTION {"+ temp +"} : + " + strs.toString());
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ DIC-EMOTION ERROR: " + e);
            e.printStackTrace();
        }

        logger.info("###[DIC-EMOTION]###The dic of Emotion[" + path + "] is loaded, errorOut["+errorCountOut+"],error["+ errorCount +"],the num of dic is: " + count);

        return forest;
    }

}
