package com.mite8.utils;

import java.util.*;

/**
 * Author: blogchong
 * Time:  2016/10/9.
 * Email: blogchong#qq.com
 * 公众号：数据虫巢 ID:blogchong
 * Desc: 排序器
 */
public class MapSort {
    //对map进行排序，并且进行长度截取
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {

        if (map == null) {
            return null;
        }

        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Comparable sort1 = (Comparable) ((Map.Entry) o1).getValue();
                Comparable sort2 = (Comparable) ((Map.Entry) o2).getValue();
                return sort2.compareTo(sort1);
            }

        });

        Map result = new LinkedHashMap();

        for (Iterator it = list.iterator(); it.hasNext(); ) {

            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());

        }

        return result;
    }

    public static Map<String, Double> sortByValue2(Map<String, Double> map) {

        if (map == null) {
            return null;
        }

        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Comparable sort1 = (Comparable) ((Map.Entry) o1).getValue();
                Comparable sort2 = (Comparable) ((Map.Entry) o2).getValue();
                return sort2.compareTo(sort1);
            }

        });

        Map result = new LinkedHashMap();

        for (Iterator it = list.iterator(); it.hasNext(); ) {

            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());

        }

        return result;
    }

    //排序裁剪
    public static Map<String, Integer> sortByValueAndCut(Map<String, Integer> map, int num) {

        if (map == null) {
            return null;
        }

        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Comparable sort1 = (Comparable) ((Map.Entry) o1).getValue();
                Comparable sort2 = (Comparable) ((Map.Entry) o2).getValue();
                return sort2.compareTo(sort1);
            }

        });

        Map result = new LinkedHashMap();

        int forNum = list.size();
        if (num < list.size()) {
            forNum = num;
        }

        for (int i = 0; i < forNum; i++) {
            Map.Entry entry = (Map.Entry) list.get(i);
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    //排序裁剪
    public static Map<String, Double> sortByValue2AndCut(Map<String, Double> map, int num) {

        if (map == null) {
            return null;
        }

        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Comparable sort1 = (Comparable) ((Map.Entry) o1).getValue();
                Comparable sort2 = (Comparable) ((Map.Entry) o2).getValue();
                return sort2.compareTo(sort1);
            }

        });

        Map result = new LinkedHashMap();

        int forNum = list.size();
        if (num < list.size()) {
            forNum = num;
        }

        for (int i = 0; i < forNum; i++) {
            Map.Entry entry = (Map.Entry) list.get(i);
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    //排序分页
    public static Map<String, Double> sortByValue2AndPage(Map<String, Double> map, int page, int size) {

        if (map == null) {
            return null;
        }

        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                Comparable sort1 = (Comparable) ((Map.Entry) o1).getValue();
                Comparable sort2 = (Comparable) ((Map.Entry) o2).getValue();
                return sort2.compareTo(sort1);
            }

        });

        Map result = new LinkedHashMap();

        if(page >= 1){
            for(int i= (page -1) * size;i< page * size && i< list.size();i++){
                Map.Entry entry = (Map.Entry) list.get(i);
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

//    public static void main(String[] args) {
//
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("test", 3);
//        map.put("hcy", 1);
//        map.put("put", 2);
//
//        map = sortByValue(map);
//
//        for (String key : map.keySet()) {
//            System.out.println(key + " ==> " + map.get(key));
//        }
//    }
}
