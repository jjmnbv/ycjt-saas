package com.beitu.saas.common.consts;

/**
 * Created by linchengyu on 17/3/1.
 */
public class RedisKeyConsts {

    /**
     * 验证码Redis Key前缀
     */
    public static final String VERIFY_CODE_PREFIX = "SAAS_VERIFYCODE_";

    /**
     * H5运营商爬取Redis Key前缀
     */
    public static final String H5_CARRIER_CRAWLING = "SAAS_H5_CARRIER_CRAWLING_{userId}";

    /**
     * 重复提交缓存
     */
    public static final String REPEAT_PREFIX = "SAAS_REPEAT_{id}";

    /**
     * <<<<<<< HEAD
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
     * 重复提交缓存
     */
    public static final String SAAS_TOKEN_KEY = "SAAS_TOKEN_{id}";

    /**
     * H5 登录 验证码 去重请求 Redis Key前缀
     */
    public static final String H5_LOGIN_VERIFYCODE_KEY = "BEITU_SAAS_H5_LOGIN_VERIFYCODE_{mobile}";

    /**
     * H5 登录 验证码 保存 Redis Key前缀
     */
    public static final String H5_SAVE_LOGIN_VERIFYCODE_KEY = "BEITU_SAAS_H5_SAVE_LOGIN_VERIFYCODE_{mobile}";

}
