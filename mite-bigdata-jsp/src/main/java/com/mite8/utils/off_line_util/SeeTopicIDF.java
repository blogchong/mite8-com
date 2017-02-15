package com.mite8.utils.off_line_util;

import com.mite8.utils.MapSort;
import com.mite8.utils.ansj_util.WordNatureFilter;
import org.ansj.domain.Result;
import org.ansj.recognition.impl.FilterRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;
import java.io.*;
import java.util.*;

/**
 * Author: blogchong
 * Time:  2016/10/9.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  See NLP 生成专题的IDF字典
 */
public class SeeTopicIDF {

//    public static void main(String[] args) throws Exception{
//        String srcPath = "see/topic_content.txt";
//        String outPath = "C:\\Data\\桌面空间\\工作文档\\分词\\实例\\see_idf.log";
//        SeeTopicIDF seeNlp = new SeeTopicIDF();
//        seeNlp.getReIDF(srcPath, outPath);
//    }
    //计算 词-包含该词的文档数
    public void getReIDF(String srcPath, String outPath) throws Exception{

        //停用词过滤器
        FilterRecognition fitler = new FilterRecognition();
        LoadStopWordDic loadStopWordDic = new LoadStopWordDic();
        List<String> list = loadStopWordDic.loadStopWordDic("library/stop_words.dic");
        fitler.insertStopWords(list);
        fitler = WordNatureFilter.wordNatureFilter(fitler);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(srcPath).getFile());

        int count = 0;

        Map<String, Integer> map = new HashMap<String, Integer>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] notes = line.split("\t");
                if (notes.length == 4) {
                    String title = notes[1];
                    String body = notes[3];
                    count++;

                    Result result = DicAnalysis.parse(title + "," + body).recognition(fitler);

                    Set<String> set = new HashSet<String>();

                    for (int i=0; i<result.size(); i++) {
                        String word = result.get(i).getName().trim();
                        //去除单字和数字，小数
//                        if (word.length() >= 2 && !word.matches("-?[0-9]+.*[0-9]*")) {
////                            set.add(word+"[" + result.get(i).getNatureStr() +  "]");
//                            set.add(word);
//                        }
                        if(result.get(i).getNatureStr().equals("userDefine")) {
                            set.add(word);
                        }
                    }

                    for (String str: set) {
                        if(map.containsKey(str)) {
                            map.put(str, map.get(str) + 1);
                        } else {
                            map.put(str, 1);
                        }
                    }

                } else {
                    System.out.println(notes[0]);
                }
            }
            scanner.close();
            System.out.println("The num of topic is : " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //对map进行排序
        map = MapSort.sortByValue(map);
        writeFile(map, outPath);

    }

    //存储到指定位置
    public static void writeFile(Map<String, Integer> map, String path) {
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

}
