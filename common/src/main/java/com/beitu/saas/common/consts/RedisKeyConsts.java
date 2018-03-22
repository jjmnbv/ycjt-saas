package com.beitu.saas.common.consts;

/**
 * Created by linchengyu on 17/3/1.
 */
public class RedisKeyConsts {

    /**
     * 验证码Redis Key前缀
     */
    public static final String VERIFY_CODE_PREFIX = "YOUJIE_VERIFYCODE_";

    /**
     * 信用报告查询Redis Key前缀
     */
    public static final String CREDIT_REPORT_PREFIX = "YOUJIE_CREDIT_REPORT_";

    /**
     * 支付密码重置Redis Key前缀
     */
    public static final String RESET_TRANSACTION_PASSWORD_PREFIX = "YOUJIE_RESET_TRANSACTION_";

    /**
     * 连连支付Redis Key前缀
     */
    public static final String LIAN_LIAN_PAY_INFO_KEY = "YOUJIE_LIANLIAN_PAY_INFO_{orderNo}_{date}";

    /**
     * 连连支付处理Redis Key前缀
     */
    public static final String LIAN_LIAN_PAY_PROCESS_KEY = "YOUJIE_LIANLIAN_PAY_PROCESS_{userId}";

    /**
     * 连连处理提现Redis Key前缀
     */
    public static final String LIAN_LIAN_WITHDRAW_PROCESS_KEY = "YOUJIE_LIANLIAN_WITHDRAW_PROCESS_{userId}";

    /**
     * 推送处理Redis Key前缀
     */
    public static final String PUSH_KEY = "YOUJIE_PUSH_KEY_{num}_{userId}";

    /**
     * H5运营商爬取Redis Key前缀
     */
    public static final String H5_CARRIER_CRAWLING = "YOUJIE_H5_CARRIER_CRAWLING_{userId}";

    /**
     * 淘宝爬虫Redis Key前缀
     */
    public static final String TAOBAO_CRAWL_PREFIX = "YOUJIE_TAOBAO_CRAWL_";

    /**
     * 京东爬虫Redis Key前缀
     */
    public static final String JD_CRAWL_PREFIX = "YOUJIE_JD_CRAWL_";

    /**
     * 支付密码错误计数Redis Key前缀
     */
    public static final String WRONG_TRANSCTION_PASSWORD_COUNT_PREFIX = "YOUJIE_WTP_CNT_";

    /**
     * 借条支付锁定Redis Key前缀
     */
    public static final String BORROW_CONFIRM_LOCK_PREFIX = "YOUJIE_BORROW_CONFIRM_LOCK_";

    public static final String REPAYMENT_LOCK_PREFIX = "YOUJIE_REPAYMENT_";

    /**
     * 人脸识别缓存
     */
    public static final String TEMP_ALIPAY_FACE_PREFIX = "YOUJIE_ALIPAY_FACE_{userId}";

    /**
     * 运营商通话记录导出文件 缓存Key前缀
     */
    public static final String EXPORT_CARRIER_FILE_PREFIX = "EXPORT_CARRIER_CALLLOG_FILE_{id}";

    /**
     * 通讯录导出文件 缓存Key前缀
     */
    public static final String EXPORT_CONTACT_FILE_PREFIX = "EXPORT_CONTACT_FILE_{id}";

    /**
     * 重复提交缓存
     */
    public static final String REPEAT_PREFIX = "YOUJIE_REPEAT_{id}";

    /**
     * 省市信息
     */
    public static final String LOCATION_INFO_VO = "LOCATION_INFO_VO";

    /**
     * 洋葱号生成时的分布式锁
     */
    public static final String IDENTITY_REDIS_LOCK_KEY = "IDENTITY_REDIS_LOCK_KEY";
    /**
     * 洋葱号当前的值
     */
    public static final String IDENTITY_REDIS_CURRENT_VALUE = "IDENTITY_REDIS_CURRENT_VALUE";
    /**
     * 用户首页公告广告栏数据
     */
    public static final String USER_HOME_ADVERTISEMENT_RESPONSE = "USER_HOME_ADVERTISEMENT_RESPONSE_{location}";
    /**
     * 后台 保存用户运营商数据
     */
    public static final String ADMIN_REDIS_USER_CARRIER_KEY = "ADMIN_REDIS_USER_CARRIER_KEY_{userId}";
    /**
     * 后台 地区数据
     */
    public static final String ADMIN_REDIS_LOCATION_KEY = "ADMIN_REDIS_LOCATION_KEY";
    /**
     * 后台 通讯录解析数据
     */
    public static final String ADMIN_REDIS_ADDRESS_BOOK = "ADMIN_REDIS_ADDRESS_BOOK_{userId}";
    /**
     * 运营商缓存
     */
    public static final String CARRIER_CACHE_KEY = "youjie_carries_userId";
    /**
     * 后台用户token失效时间更新锁
     */
    public static final String ADMIN_TOKEN_REFRESH_LOCK = "ADMIN_TOKEN_REFRESH_LOCK_{token}";
    /**
     * 统计借款被查看次数的key
     */
    public static final String BORROW_LOOK_NUM_KEY = "YCJT_BORROW_LOOK_NUM_KEY_{id}";

    /**
     * 订单强推送key
     */
    public static final String ORDER_PUSH_KEY = "YCJT_ORDER_PUSH_KEY";

    /**
     * 订单接单上限key
     */
    public static final String ORDER_LOCK_MAX_KEY = "YCJT_ORDER_LOCK_MAX_KEY_{id}";

    /**
     * 运营商互通联系人数Redis Key前缀
     */
    public static final String YOUJIE_CARRIER_CONTACT_COUNT_KEY = "YOUJIE_CARRIER_CONTACT_COUNT_{userId}";

    /**
     * 订单接单上限key
     */
    public static final String ORDER_SCRAMBLED_KEY = "YCJT_ORDER_SCRAMBLED_KEY_{id}";

    /**
     * 查看订单记录 订单
     */
    public static final String ORDER_VIEWED_ORDER_KEY = "YCJT_VIEWED_ORDER_{orderId}";

    /**
     * 商户接单剩余单数Redis Key前缀
     */
    public static final String MERCHANT_RECEIVE_COUNT_KEY = "YCJT_MERCHANT_RECEIVE_COUNT_{merchantId}";

    /**
     * 商户抢单剩余单数Redis Key前缀
     */
    public static final String MERCHANT_SCRAMBLE_COUNT_KEY = "YCJT_MERCHANT_SCRAMBLE_COUNT_{merchantId}";

    /**
     * H5 登录 验证码 去重请求 Redis Key前缀
     */
    public static final String H5_LOGIN_VERIFYCODE_KEY = "BEITU_SAAS_H5_LOGIN_VERIFYCODE_{mobile}";

    /**
     * H5 登录 验证码 保存 Redis Key前缀
     */
    public static final String H5_SAVE_LOGIN_VERIFYCODE_KEY = "BEITU_SAAS_H5_SAVE_LOGIN_VERIFYCODE_{mobile}";

}
