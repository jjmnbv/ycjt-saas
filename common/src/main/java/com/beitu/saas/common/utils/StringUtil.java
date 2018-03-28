package com.beitu.saas.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.ParseException;
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

    public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }

            b += 256; // 去掉符号位

            if (((b >> 5) ^ 0x6) == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            } else if (((b >> 4) ^ 0xE) == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            } else if (((b >> 3) ^ 0x1E) == 0) {
                i += 4;
            } else if (((b >> 2) ^ 0x3E) == 0) {
                i += 5;
            } else if (((b >> 1) ^ 0x7E) == 0) {
                i += 6;
            } else {
                buffer.put(bytes[i++]);
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }

    public static String filterOffUtf8Mb4_2(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }

            b += 256; //去掉符号位

            if (((b >> 5) ^ 0x06) == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
                System.out.println("2");
            } else if (((b >> 4) ^ 0x0E) == 0) {
                System.out.println("3");
                buffer.put(bytes, i, 3);
                i += 3;
            } else if (((b >> 3) ^ 0x1E) == 0) {
                i += 4;
                System.out.println("4");
            } else if (((b >> 2) ^ 0xBE) == 0) {
                i += 5;
                System.out.println("5");
            } else {
                i += 6;
                System.out.println("6");
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }

}
