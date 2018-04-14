package com.beitu.saas.common.utils;

import java.text.ParseException;
import java.util.*;

/**
 * @author xiaochong
 * @create 2018/4/8 下午11:23
 * @description
 */
public class SortUtil {

    public static Map sortMapByValueDESC(Map unsortMap) {
        return sortMapByComparator(unsortMap, new MapValueComparatorDESC());
    }

    public static Map sortMapByValueASC(Map unsortMap) {
        return sortMapByComparator(unsortMap, new MapValueComparatorASC());
    }

    public static Map sortMapByComparator(Map unsortMap, Comparator comparator) {
        List list = new LinkedList(unsortMap.entrySet());
        Collections.sort(list, comparator);
        Map sortedMap = new LinkedHashMap();

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;

    }


    public static void main(String[] args) throws ParseException {
        HashMap<String, Object> unsortMap = new HashMap<>();
        unsortMap.put("10001A", 2);
        unsortMap.put("10001B", 10);
        unsortMap.put("10001C", 4);
        unsortMap.put("10001D", 55);
        Map sortMapByValueASC = SortUtil.sortMapByValueASC(unsortMap);
        Map sortMapByValueDESC = SortUtil.sortMapByValueDESC(unsortMap);
        System.out.print(sortMapByValueASC);
    }
}

class MapValueComparatorDESC implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Comparable) ((Map.Entry) (o2)).getValue())
                .compareTo(((Map.Entry) (o1)).getValue());
    }

}

class MapValueComparatorASC implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Comparable) ((Map.Entry) (o1)).getValue())
                .compareTo(((Map.Entry) (o2)).getValue());
    }

}
