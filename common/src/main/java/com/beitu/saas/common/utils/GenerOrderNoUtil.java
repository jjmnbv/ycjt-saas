/**
 * @Title: GenerOrderNoUtil.java
 * @Package com.fqgj.common.mapper
 * @Description: TODO(用一句话描述该文件做什么)
 * @author zhangxingyun
 * @date 2017年2月27日 下午8:29:16
 */

package com.beitu.saas.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: GenerOrderNoUtil
 * @Description: 订单号生成器
 * @author zhangxingyun
 * @date 2017年2月27日 下午8:29:16 
 */
public class GenerOrderNoUtil {
    
    private static final SimpleDateFormat DateFormatter = new SimpleDateFormat("yyyyMMddHH");
    
    private static final String word = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * @Description: 轻松有钱借款订单生成器
     * @param @param userId
     * @param @return  参数说明
     * @return String  返回类型
     * @throws
     */
    public static String generateOrderNo() {
        return DateUtil.getDate(new Date(), "yyyyMMddHHmmss") + getRandomWord(5);
    }
    
    public static String getRandomWord(int length) {
        String randomWord = "";
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * word.length());
            char c = word.charAt(rand);
            if (!randomWord.contains(c + "")) {
                randomWord += c;
            } else {
                i--;
            }
        }
        return randomWord;
    }
    
    public static void main(String[] args) {
        System.out.println(com.fqgj.common.utils.GenerOrderNoUtil.generateOrderNo());
    }
}
