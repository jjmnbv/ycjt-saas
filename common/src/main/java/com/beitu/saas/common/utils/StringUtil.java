package com.beitu.saas.common.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linchengyu on 17/4/12.
 */
public class StringUtil {

    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        int bytesLength = conbyte.length;
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                bytesLength -= 4;
                i += 3;
            }
        }

        byte[] resultbyte = new byte[bytesLength];
        int j = 0;
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                i += 3;
            } else {
                resultbyte[j] = conbyte[i];
                j++;
            }
        }
        return new String(resultbyte);
    }

    public static String getSubContent(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static String getString4Object(Object object) {
        return object == null ? "" : object.toString();
    }

    public static String getPhoneStrDeal(String phone) {
        return phone
                .replaceAll("\\+86 ", "")
                .replaceAll("\\+86", "")
                .replaceAll("[^\\d]", "");
    }

}
