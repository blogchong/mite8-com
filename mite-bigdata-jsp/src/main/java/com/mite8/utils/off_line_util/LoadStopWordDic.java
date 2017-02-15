package com.mite8.utils.off_line_util;

import org.ansj.recognition.impl.FilterRecognition;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Author: blogchong
 * Time:  2016/10/9.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc:  停用词加载
 */
public class LoadStopWordDic {

    //返回一个list进行加载
    public List<String> loadStopWordDic(String path) {

        List<String> list = new ArrayList<String>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String stopWord = scanner.nextLine();
                list.add(stopWord);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    //直接逐个加载
    public FilterRecognition insertStopWords(FilterRecognition filter, String path) throws Exception{

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String stopWord = scanner.nextLine();
                filter.insertStopWord(stopWord);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filter;
    }

    //加载IDF基础数据
    public  Map<String, Integer> loadIdfFile(String idfPath) {
        Map<String, Integer> idfMap = new HashMap<String, Integer>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(idfPath).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] notes = line.split("\t");
                if(notes.length == 2) {
                    String word = notes[0];
                    int num = Integer.parseInt(notes[1]);
                    idfMap.put(word, num);
                }
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return idfMap;
    }

}
