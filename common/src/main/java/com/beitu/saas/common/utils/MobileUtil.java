package com.beitu.saas.common.utils;

import com.fqgj.common.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class MobileUtil {

    /**
     * 手机号码:
     * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[6, 7, 8], 18[0-9], 170[0-9]
     * 移动号段: 134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     * 联通号段: 130,131,132,155,156,185,186,145,176,1709
     * 电信号段: 133,153,180,181,189,177,1700
     */
    private static final Pattern MobilePattern = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70|79)\\d{8}$");
    /**
     * 中国移动：China Mobile
     * 134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     */
    private static final Pattern CMMobilePattern = Pattern.compile("(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)");
    /**
     * 中国联通：China Unicom
     * 130,131,132,155,156,185,186,145,176,1709
     */
    private static final Pattern CUMobilePattern = Pattern.compile("(^1(3[0-2]|4[5]|5[56]|7[56]|8[56])\\d{8}$)|(^1709\\d{7}$)");
    /**
     * 中国电信：China Telecom
     * 133,153,180,181,189,173,177,1700
     */
    private static final Pattern CTMobilePattern = Pattern.compile("(^1(33|53|73|77|8[019])\\d{8}$)|(^1700\\d{7}$)");

    private static final Pattern QQPattern = Pattern.compile("[1-9][0-9]{4,14}");

    private static final Integer DEFAULT_MOBILE_LENGTH = 11;

    /**
     * 手机号码验证
     * 考虑以后号码段会变化，所以15和18写的范围就比较大，可能有些号段现在还没有
     *
     * @param mobile 手机号码
     * @return true/false
     */
    public static boolean isMobile(String mobile) {

        return MobilePattern.matcher(mobile).matches() ||
                CMMobilePattern.matcher(mobile).matches() ||
                CUMobilePattern.matcher(mobile).matches() ||
                CTMobilePattern.matcher(mobile).matches();
    }

    public static boolean isQQ(String qq) {
        return QQPattern.matcher(qq).matches();
    }

    public static String hidden(String mobile) {
        if (StringUtils.isNotEmpty(mobile)) {
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
            return mobile;
        }
        return null;
    }

    public static String parseMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return null;
        }
        if (mobile.length() > DEFAULT_MOBILE_LENGTH) {
            if (isMobile(mobile.substring(0, DEFAULT_MOBILE_LENGTH))) {
                return mobile.substring(0, DEFAULT_MOBILE_LENGTH);
            }
            return PhoneUtil.parsePhone(mobile);
        } else {
            return mobile;
        }
    }

    /**
     * imei编号由15位数字组成，
     * 前6位(TAC)是型号核准号码，代表手机类型。
     * 接着2位(FAC)是最后装配号，代表产地。
     * 后6位(SNR)是串号，代表生产顺序号。
     * 最后1位 (SP)是检验码。
     * <p>
     * 检验码计算：
     * (1).将偶数位数字分别乘以2，分别计算个位数和十位数之和
     * (2).将奇数位数字相加，再加上上一步算得的值
     * (3).如果得出的数个位是0则校验位为0，否则为10减去个位数
     *
     * @author sonzer
     */
    public static String creatImeiCode() {
        //864710 02 131998 2
        String imeiString = "86471002" + StrUtils.randNum(6);//前14位
        char[] imeiChar = imeiString.toCharArray();
        int resultInt = 0;
        for (int i = 0; i < imeiChar.length; i++) {
            int a = Integer.parseInt(String.valueOf(imeiChar[i]));
            i++;
            final int temp = Integer.parseInt(String.valueOf(imeiChar[i])) * 2;
            final int b = temp < 10 ? temp : temp - 9;
            resultInt += a + b;
        }
        resultInt %= 10;
        resultInt = resultInt == 0 ? 0 : 10 - resultInt;
//        System.out.println("imei:"+imeiString+resultInt);
        return imeiString + String.valueOf(resultInt);
    }

}
