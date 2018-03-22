package com.beitu.saas.sms.util;

import org.apache.commons.lang3.StringUtils;

public class CacheKey {

    private static final String prefix = "MESSAGE_CENTER_";
    enum SMS{
        SMS_RECORD,
        APP_SERVIVER_REF,
        SYNC_SMS_STATUS
    }
    enum SYS{
        ALL_APP_INFOS
    }

    enum BUSINESS_KEY {
        ALL_SERVICE_INFO
    }

    public static String getAllServerKey() {
        return getKey(BUSINESS_KEY.ALL_SERVICE_INFO);
    }

    public static String getKeyOfSyncSmsStatus(String servicerCode) {
        return getKey(SMS.SYNC_SMS_STATUS,servicerCode);
    }

    /**
     * app 服务商关系表
     * @return
     */
    public static String getKeyOfAppServicerRef() {
        return getKey(SMS.APP_SERVIVER_REF);
    }

    /**
     * 短信消息记录key
     * @param phone
     * @param bizCode
     * @return
     */
    public static String getKeyOfSmsMessageSendRecord(String phone, String bizCode) {
        return getKey(SMS.SMS_RECORD,phone,bizCode);
    }
    /**
     * 消息配置key
     * @param bizCode
     * @return
     */
    public static String getKeyOfMessageConfig(String bizCode){
        return getKey(bizCode);
    }

    public static String getKey(Object ...obj){
        return prefix + StringUtils.join(obj,"_");
    }
    /**
     * 
     * 所有app信息
     *
     * @name 所有app信息
     * @return
     * @author liting
     * @date: 2017年9月15日 上午10:41:40
     */
    public static String getKeyOfAllAppInfos() {
        
        return getKey(SYS.ALL_APP_INFOS);
    }


}
