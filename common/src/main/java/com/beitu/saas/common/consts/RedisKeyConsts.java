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

    /**
     * H5聚信立借贷平台爬取Redis Key前缀
     */
    public static final String H5_LOAN_PLATFORM_CRAWLING = "SAAS_H5_LOAN_PLATFORM_CRAWLING_{userCode}";

    /**
     * 同盾 防止 用户短时间内多次重复提交 Redis Key前缀
     */
    public static final String SAAS_TONGDUN_FORBID_REPEAT_SUBMIT = "SAAS_TONGDUN_FORBID_REPEAT_SUBMIT_{userCode}";

    /**
     * 每日推荐总量
     */
    public static final String SAAS_RECOMMEND_NUM_DAY = "SAAS_RECOMMEND_NUM_DAY";
    /**
     * 每个机构每天接受的量
     */
    public static final String SAAS_MERCHANT_RECOMMEND_NUM_DAY = "SAAS_MERCHANT_RECOMMEND_NUM_DAY";
    /**
     * 推荐流量设置每天保存一次
     */
    public static final String SAAS_MERCHANT_FLOW_CONFIG = "SAAS_MERCHANT_FLOW_CONFIG_{merchantCode}";
    /**
     * 打开 聚信力url 浏览器类型
     */
    public static final String SAAS_OPEN_JXL_H5_BROWSER_TYPE = "SAAS_OPEN_JXL_H5_BROWSER_{taskId}";
    /**
     * 打开 运营商url 浏览器类型
     */
    public static final String SAAS_OPEN_CARRIER_H5_BROWSER_TYPE = "SAAS_OPEN_CARRIER_H5_BROWSER_{borrowerCode}";

}
