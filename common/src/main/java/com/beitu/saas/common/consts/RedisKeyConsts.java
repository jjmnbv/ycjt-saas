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

}
