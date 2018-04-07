package com.beitu.saas.credit.consts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linchengyu on 17/7/19.
 */
public class CreditConsts {
    
    //黑名单命中条目
    public static final List<String> BLACKED_HIT_ITEMS = Arrays.asList("命中网贷黑名单", "身份证命中失联名单", "手机号命中网贷黑名单", "手机号命中失联名单", "手机号命中同盾欺诈灰名单", "第一联系人手机号命中网贷黑名单", "第一联系人手机号命中失联名单", "身份证不是二代身份证", "身份证命中法院执行名单", "手机号命中信贷逾期名单", "身份证命中信贷逾期名单", "手机号命中高风险关注名单");
    //借款命中条目
    public static final List<String> BORROW_HIT_ITEMS = Arrays.asList("7天内申请人在多个平台申请借款", "1个月内申请人在多个平台申请借款", "3个月内申请人在多个平台申请借款", "6个月内申请人在多个平台申请借款", "12个月内申请人在多个平台申请借款", "3个月内手机号在多个网贷平台进行借款申请", "3个月内身份证在多个网贷平台进行借款申请");
    //关联信息命中条目
    public static final List<String> RELATION_HIT_ITEMS = Arrays.asList("3个月内身份证关联多个申请信息");

    public final static Map<Integer, Integer> JY_CREDIT_OVERDUO_DAYS_MAPPING = new HashMap<Integer, Integer>() {
        {//2~8为逾期状态，2：M1，7：M6,8:M6+，M1对应30天，M6对应180天，M6+对应181天
            put(2, 30);
            put(3, 60);
            put(4, 90);
            put(5, 120);
            put(6, 150);
            put(7, 180);
            put(8, 181);
        }
    };

    public static final String DEFAULT_EXPORT_FILE_TYPE = ".xlsx";

    public static final Integer UNKNOW_CARRIER = 0;
    public static final Integer CMCC = 1;
    public static final Integer UNICOM = 2;
    public static final Integer TELECOM = 3;

    public static final String SEARCH_CARRIER_URL = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=";

    public static String[] tipsCMCC = {"• 移动手机号提交认证需要两次手机验证码，请注意查收", "• 忘记密码可以拨打10086人工客服重置密码",
            "• 认证需要2-3分钟，请耐心等待", "• 若认证失败，提示“系统繁忙”，建议您在不同时间段进行多次尝试"};
    public static String[] tipsUNICOM = {"• 联通手机号提交认证仅需要服务密码即可", "• 忘记密码可以拨打10010人工客服重置密码",
            "• 认证需要2-3分钟，请耐心等待", "• 若认证失败，提示“系统繁忙”，建议您在不同时间段进行多次尝试"};
    public static String[] tipsTELECOM = {"• 电信手机号提交认证需要一次手机验证码，请注意查收", "• 忘记密码可以拨打10000人工客服重置密码",
            "• 认证需要2-3分钟，请耐心等待", "• 若认证失败，提示“系统繁忙”，建议您在不同时间段进行多次尝试"};

    public static final String CRAWLER_PLATFORM = "ycjt";

    public static final String STATUS_CRAWLING = "crawling";

}
