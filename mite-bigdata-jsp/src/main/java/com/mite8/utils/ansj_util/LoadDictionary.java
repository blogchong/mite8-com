package com.mite8.utils.ansj_util;

import com.mite8.utils.DefineOut;
import org.ansj.library.DATDictionary;
import org.ansj.util.MyStaticValue;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;
import org.nlpcn.commons.lang.util.StringUtil;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import static org.ansj.util.MyStaticValue.LIBRARYLOG;

/**
 * Author: blogchong
 * Time:  2016/10/14.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  字典的自动加载。
 */

@Service
public class LoadDictionary {

    private static final Logger logger = Logger.getLogger(LoadDictionary.class.getName());

    private static String DEFAULT_FREQ_STR = "1000";

    public static Map<String, Forest> dicSegMap = new HashMap<>();
    public static Map<String, Forest> dicAmbigMap = new HashMap<>();
    public static Map<String, List<String>> dicStopMap = new HashMap<>();
    public static Map<String, Map<String, String>> dicSynonMap = new HashMap<>();
    public static Map<String, String> dicConfigMap = new HashMap<>();
    public static Map<String, Set<String>> dicSpeMap = new HashMap<>();

    //文档频文件,存储文档频
    public static Map<String, Integer> dicDfMap = new HashMap<>();
    public static int DF_NUM = 0;

    public LoadDictionary() throws Exception{
        initDicConfig();
        init();
    }

    //进行字典配置读取
    public static void initDicConfig() throws Exception{

        InputStream  input = LoadDictionary.class.getResourceAsStream("/dic.properties");
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String temp = null;
        try {
            while ((temp = br.readLine()) != null) {
                String[] notes = temp.split("=");
                if (notes.length == 2) {
                    dicConfigMap.put(notes[0], notes[1]);
                }
                System.out.println("###config: " + temp);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ CONFIG ERROR: " + e);
            e.printStackTrace();
        }

    }

    //初始化各个自定义字典
    private static void init() throws Exception{

        //初始化分词字典
        initLoad(dicConfigMap.get(DefineOut.dic_seg), DefineOut.DIC_FLAG_SEG);
        //初始化歧义字典
        initLoad(dicConfigMap.get(DefineOut.dic_ambig), DefineOut.DIC_FLAG_AMBIG);
        //初始化停用词字典
        initLoad(dicConfigMap.get(DefineOut.dic_stop), DefineOut.DIC_FLAG_STOP);
        //初始化同义词字典
        initLoad(dicConfigMap.get(DefineOut.dic_synon), DefineOut.DIC_FLAG_SYNON);
        //初始化特俗字典
        initLoad(dicConfigMap.get(DefineOut.dic_spe), DefineOut.DIC_FLAG_SPE);
        //初始化DF字典
        loadDFDic();

    }

    //读取文档频相关的文件
    public static void loadDFDic(){
        String dfFile = "/dic/df/df.dic";
        String numFile = "/dic/df/num.dic";

        InputStream  inputNum = LoadDictionary.class.getResourceAsStream(numFile);
        BufferedReader brNum = new BufferedReader(new InputStreamReader(inputNum));
        String temp = null;
        try {
            while ((temp = brNum.readLine()) != null) {
                //获取到resource中的每行
                DF_NUM = Integer.parseInt(temp);
            }
            brNum.close();
        } catch (IOException e) {
            System.err.println("READ DF-NUM ERROR: " + e);
            e.printStackTrace();
        }

        InputStream  input = LoadDictionary.class.getResourceAsStream(dfFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        int count = 0;
        try {
            while ((temp = br.readLine()) != null) {
                //获取到resource中的每行
                String[] notes = temp.split("\\t");
                if(notes.length == 3) {

                    try {
                        String word = notes[0];
                        int df = Integer.parseInt(notes[1]);
                        dicDfMap.put(word, df);
                        count++;
                    } catch (Exception e) {
                        logger.info("DF bad word: [" + notes[0] + "][" + notes[1] + "][" + notes[2] + "] ERROR:" + e);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ DF ERROR: " + e);
            e.printStackTrace();
        }

    }

    //初始化公共函数
    private static void initLoad(String typePaths, String type) throws Exception{

        if (typePaths != null && typePaths.length() > 1) {
            String[] paths = typePaths.split(";");
            for (String path: paths) {
                String[] pathTmp = path.split("/");
                String natureTmp = pathTmp[pathTmp.length - 1];
                String[] natureTmp2 = natureTmp.split("\\.");
                if (natureTmp2.length == 2) {
                    String nature = natureTmp2[0];
                    if (type.equals(DefineOut.DIC_FLAG_SEG)) {
                        dicSegMap.put(nature, loadSegDic(path, nature));
                    } else if (type.equals(DefineOut.DIC_FLAG_AMBIG)) {
                        dicAmbigMap.put(nature, loadAmbigDic(path));
                    } else if (type.equals(DefineOut.DIC_FLAG_STOP)) {
                        dicStopMap.put(nature, loadStopDic(path));
                    } else if (type.equals(DefineOut.DIC_FLAG_SYNON)) {
                        dicSynonMap.put(nature, loadSynonDic(path));
                    } else if (type.equals(DefineOut.DIC_FLAG_SPE)) {
                        dicSpeMap.put(nature, loadSpeDic(path));
                    }

                }
            }
        }
    }

    //加载分词字典
    private static Forest loadSegDic(String path, String nature) throws Exception{

        Forest forest = new Forest();

        InputStream  input = LoadDictionary.class.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String temp = null;
        String[] strs;
        Value value;
        int count = 0;
        try {
            while ((temp = br.readLine()) != null) {
                //获取到resource中的每行
                if (StringUtil.isNotBlank(temp)) {
                    temp = StringUtil.trim(temp);
                    strs = temp.split("\t");
                    strs[0] = strs[0].toLowerCase();
                    // 如何核心辞典存在那么就放弃
                    if (MyStaticValue.isSkipUserDefine && DATDictionary.getId(strs[0]) > 0) {
                        continue;
                    }
                    if (strs.length != 3) {
                        value = new Value(strs[0], nature, DEFAULT_FREQ_STR);
                    } else {
                        value = new Value(strs[0], strs[1], strs[2]);
                    }
                    Library.insertWord(forest, value);
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ DIC-SEG ERROR: " + e);
            e.printStackTrace();
        }

        logger.info("###[DIC-SEG]###The dic of Seg[" + path + "] is loaded, the num of dic is: " + count);

        return forest;
    }

    //加歧义词字典
    private static Forest loadAmbigDic(String path) throws Exception{

        Forest forest = new Forest();

        InputStream  input = LoadDictionary.class.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String temp = null;
        int count = 0;
        try {
            while ((temp = br.readLine()) != null) {
                if (StringUtil.isNotBlank(temp)) {
                    temp = StringUtil.trim(temp);
                    String[] split = temp.split("\t");
                    StringBuilder sb = new StringBuilder();
                    if (split.length % 2 != 0) {
                        LIBRARYLOG.error("init ambiguity  error in line :" + temp + " format err !");
                    }
                    for (int i = 0; i < split.length; i += 2) {
                        sb.append(split[i]);
                    }
                    forest.addBranch(sb.toString(), split);
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ DIC-AMBIG ERROR: " + e);
            e.printStackTrace();
        }

        logger.info("###[DIC-AMBIG]###The dic of Ambig[" + path + "] is loaded, the num of dic is: " + count);
        return forest;
    }

    //加载停用词字典
    private static List<String> loadStopDic(String path) throws Exception{

        List<String> list = new ArrayList<String>();

        InputStream  input = LoadDictionary.class.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String temp = null;
        int count = 0;
        try {
            while ((temp = br.readLine()) != null) {
                list.add(temp);
                count++;
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ DIC-STOP ERROR: " + e);
            e.printStackTrace();
        }

        logger.info("###[DIC-STOP]###The dic of Stop[" + path + "] is loaded, the num of dic is: " + count);
        return list;
    }

    //加载同义词字典
    private static Map<String, String> loadSynonDic(String path) throws Exception{

        Map<String, String> map = new HashMap<String, String>();

        InputStream  input = LoadDictionary.class.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String temp = null;
        int count = 0;
        try {
            while ((temp = br.readLine()) != null) {
                String[] words = temp.split("\\t");
                if (words.length >= 2) {
                    for (int i = 1; i < words.length; i++) {
                        map.put(words[i].toLowerCase(), words[0].toLowerCase());
                    }
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ DIC-SYNON ERROR: " + e);
            e.printStackTrace();
        }

        logger.info("###[DIC-SYNON]###The dic of Synon[" + path + "] is loaded, the num of dic is: " + count);
        return map;
    }

    //加载特俗字典
    private static Set<String> loadSpeDic(String path) throws Exception{

        Set<String> set = new HashSet<>();

        InputStream  input = LoadDictionary.class.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String temp = null;
        int count = 0;
        try {
            while ((temp = br.readLine()) != null) {
                if (temp.length() != 0) {
                    set.add(temp);
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("READ DIC-SPE ERROR: " + e);
            e.printStackTrace();
        }

        logger.info("###[DIC-SPE]###The dic of Spe[" + path + "] is loaded, the num of dic is: " + count);
        return set;
    }
}
