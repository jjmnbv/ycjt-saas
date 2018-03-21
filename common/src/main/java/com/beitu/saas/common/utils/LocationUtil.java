package com.beitu.saas.common.utils;

public class LocationUtil {
    
    /**
     * 匹配"省"、"市"、非中文字符的正则表达式
     */
    public static final String FORMAT_REGEX = "[^\\u4e00-\\u9fa5]+|[省]+|[市]+";
    
    /**
     * 判断两个区域是否匹配
     *
     * @param location1
     * @param location2
     * @return
     */
    public static boolean isMatch(String location1, String location2) {
        if (formatLocation(location1).equals(formatLocation(location2))) {
            return true;
        }
        return false;
    }
    
    /**
     * 格式化区域，将"省"、"市"去掉，非中文字符都去掉
     *
     * @param location
     * @return
     */
    public static String formatLocation(String location) {
        return location.replaceAll(FORMAT_REGEX, "");
    }
    
}
