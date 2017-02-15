package com.mite8.utils.off_line_util;

import com.mite8.utils.DefineOut;
import com.mite8.utils.MapSort;
import com.mite8.utils.ansj_util.WordNatureFilter;
import org.ansj.domain.Result;
import org.ansj.recognition.impl.FilterRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
* Author: blogchong
* Time:  2016/10/9.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
* Desc:  See NLP对于专题抽取关键词，使用TFIDF模型
*/
public class SeeTopicTFIDF {

//    public static void main(String[] args) throws Exception{
//        String srcPath = "see/topic_content.txt";
//        String idfPath = "see/see_idf.log";
//        String outPath = "C:\\Data\\桌面空间\\工作文档\\分词\\实例\\see_tf_idf.log";
//        SeeTopicTFIDF seeNlp = new SeeTopicTFIDF();
//        seeNlp.getTFIDF(srcPath, outPath, idfPath);
//    }
    //计算 词-包含该词的文档数
    public void getTFIDF(String srcPath, String outPath, String idfPath) throws Exception{

        //停用词过滤器
        FilterRecognition fitler = new FilterRecognition();
        LoadStopWordDic loadStopWordDic = new LoadStopWordDic();
        List<String> list = loadStopWordDic.loadStopWordDic("library/stop_words.dic");
        fitler.insertStopWords(list);
        fitler = WordNatureFilter.wordNatureFilter(fitler);

        Map<String, Integer> idfMap = loadStopWordDic.loadIdfFile(idfPath);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(srcPath).getFile());

        //最终返回的结果数据
        List<String> retList = new ArrayList<String>();

        //总文章数
        int countAll = 0;

        //计算一个总热词情况
        Map<String, Double> mapAllWords = new HashMap<String, Double>();
        Map<String, Integer> mapAllWords2 = new HashMap<String, Integer>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] notes = line.split("\t");
                if (notes.length == 4) {
                    String id = notes[0];
                    String title = notes[1];
                    String body = notes[3];

                    countAll++;

                    Result result = DicAnalysis.parse(title + "," + body).recognition(fitler);

                    int docWordNum = 0;
                    Map<String, Integer> tfMapTmp = new HashMap<String, Integer>();
                    Map<String, Double> tfIdfMap = new HashMap<String, Double>();
                    for (int i=0; i<result.size(); i++) {
                        String word = result.get(i).getName().trim();
                        //去除单字和数字，小数
                        if (word.length() >= 2 && !word.matches("-?[0-9]+.*[0-9]*")) {
                            docWordNum++;
                            if (tfMapTmp.containsKey(word)) {
                                tfMapTmp.put(word, tfMapTmp.get(word) + 1);
                            } else {
                                tfMapTmp.put(word, 1);
                            }
                        }
                    }

                    //计算TF值
                    for (String str: tfMapTmp.keySet()) {
                        int num = tfMapTmp.get(str);
                        double tf = (double)num / (double)docWordNum;
                        int idfTmp = 0;
                        if (idfMap.containsKey(str)) {
                            idfTmp = idfMap.get(str);
                        } else {
                            //idf表中没有，则认为文档频为1
                            idfTmp = 1;
                        }

                        double idf = Math.log((double) DefineOut.TopicNum/((double)idfTmp + 1));

                        //保留四位小数
                        DecimalFormat df = new DecimalFormat("#.0000");

                        tfIdfMap.put(str, Double.parseDouble(df.format(tf*idf)));
                    }

                    //排序
                    tfIdfMap = MapSort.sortByValue2(tfIdfMap);
                    int count = 0;
                    String wordLists = null;
                    for (String str: tfIdfMap.keySet()) {
                        if (count <= 20) {
                            if(wordLists == null) {
                                wordLists = str + "[" + tfIdfMap.get(str) + "]";
                            } else {
                                wordLists = wordLists + " | " + str + "[" + tfIdfMap.get(str) + "]";
                            }
                            count++;

                            //全局分值记录
                            if (mapAllWords.containsKey(str)) {
                                mapAllWords.put(str, mapAllWords.get(str) + tfIdfMap.get(str));
                            } else {
                                mapAllWords.put(str, tfIdfMap.get(str));
                            }
                            //全局个数记录
                            if(mapAllWords2.containsKey(str)) {
                                mapAllWords2.put(str, mapAllWords2.get(str) + 1);
                            } else {
                                mapAllWords2.put(str, 1);
                            }
                        } else {
                            break;
                        }
                    }

                    //组装最后的返回数据
                    retList.add(id + "\t" + wordLists);

                } else {
                    System.out.println(notes[0]);
                }
            }
            scanner.close();
            System.out.println("The num of topic is : " + countAll);

        } catch (IOException e) {
            e.printStackTrace();
        }

        writeFile(retList, outPath);

        //记录总体热度情况
        String allScorePath = "C:\\Data\\桌面空间\\工作文档\\分词\\实例\\global_score_words_20.log";
        String allNumPath = "C:\\Data\\桌面空间\\工作文档\\分词\\实例\\global_num_words_20.log";
        writeFile(MapSort.sortByValue2(mapAllWords), allScorePath);
        writeFile2(MapSort.sortByValue(mapAllWords2), allNumPath);

    }

    //存储到指定位置
    public static void writeFile(Map<String, Double> map, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));

            for (String name : map.keySet()) {
                bw.write(name + "\t" + map.get(name));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }

    //存储到指定位置
    public static void writeFile2(Map<String, Integer> map, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));

            for (String name : map.keySet()) {
                bw.write(name + "\t" + map.get(name));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }

    //存储到指定位置
    public static void writeFile(List<String> list, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));

            for (String note : list) {
                bw.write(note);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }

}
