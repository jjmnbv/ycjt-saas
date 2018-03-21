package com.beitu.saas.common.utils;

import java.util.regex.Pattern;

/**
 * Created by linchengyu on 17/4/12.
 */
public class PhoneUtil {

    /**
     * 三位区号正则
     */
    private static final Pattern AreaCode3Pattern = Pattern.compile("(010|020|021|022|023|024|025|026|027|028|029|852)");

    /**
     * 四位区号正则
     */
    private static final Pattern AreaCode4Pattern = Pattern.compile("(0[3-9][1-9][0-9])");

    private static final Pattern PhoneNumberPattern = Pattern.compile("\\d{7,8}$");

    private static final Integer DEFAULT_MAX_PHONE_LENGTH = 12;

    /**
     * 区号正则校验
     *
     * @param areaCode
     * @return
     */
    public static boolean isAreaCode(String areaCode) {

        return AreaCode3Pattern.matcher(areaCode).matches() ||
                AreaCode4Pattern.matcher(areaCode).matches();
    }

    public static boolean isPhoneNumber(String phone) {

        return PhoneNumberPattern.matcher(phone).matches();
    }

    public static String parsePhone(String phone) {
        if (phone.length() > DEFAULT_MAX_PHONE_LENGTH) {
            if (AreaCode3Pattern.matcher(phone.substring(0, 3)).matches()) {
                return phone.substring(0, 11);
            } else if (AreaCode4Pattern.matcher(phone.substring(0, 4)).matches()) {
                return phone.substring(0, DEFAULT_MAX_PHONE_LENGTH);
            } else if (phone.startsWith("9")) {
                return phone.substring(0, 5);
            } else if (phone.startsWith("4")) {
                return phone.substring(0, 10);
            }
            return phone.substring(0, DEFAULT_MAX_PHONE_LENGTH);
        }
        return phone;
    }

//    public static void main(String[] args) {
//        String[] sa = {"010", "020", "021", "0577", "0111", "0571", "0611", "061", "0510", "0511", "0512", "0513", "0519"};
//
//        for (String s : sa) {
//            System.out.printf("%s:%s\n", s, PhoneUtil.isAreaCode(s));
//        }
//    }

//    public static void main(String[] args) {
//        String[] sa = {"88332233", "8833223", "883322"};
//
//        for (String s : sa) {
//            System.out.printf("%s:%s\n", s, PhoneUtil.isPhoneNumber(s));
//        }
//    }
}
