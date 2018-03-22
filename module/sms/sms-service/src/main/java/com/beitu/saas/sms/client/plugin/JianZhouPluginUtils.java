package com.beitu.saas.sms.client.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beitu.saas.sms.domain.SmsMsgLinkInfo;
import org.apache.commons.lang3.StringUtils;

import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.sms.util.HttpUtils;

public class JianZhouPluginUtils {

    private static Log LOGGER = LogFactory.getLog(JianZhouPluginUtils.class);

    private static String DATE_FORMAT = "yyyyMMddHHmmss";

    private static String CHAR_SET = "UTF-8";

    /**
     * 单发短信
     *
     * @param linkInfo
     * @param phone
     * @param content
     * @return
     */
    public static String send(SmsMsgLinkInfo linkInfo, String phone, String content) {
        String account = linkInfo.getUserName();
        String password = linkInfo.getPassword();
        Date time = new Date();
        String url = linkInfo.getUrl();
        String timeStamp = getTimeStamp();
        String result = null;
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("account", account);
            params.put("password", password);
            params.put("destmobile", phone);
            params.put("sendDateTime", timeStamp);
            params.put("msgText", content);
            result = HttpUtils.post(url, params, CHAR_SET);
            LOGGER.info("JANZHOU send client phone:" + phone + ",content" + content + ",result:" + result);
        } catch (Exception e) {
            LOGGER.error("{}JIANZHOU sendMsg error.", phone, content, e);
        }
        return result;
    }

    /**
     * 群发短信
     *
     * @param linkInfo
     * @param phoneList
     * @param content
     * @return
     */
    public static String batchSend(SmsMsgLinkInfo linkInfo, List<String> phoneList, String content) {
        String phones = StringUtils.join(phoneList, ";");
        String result = send(linkInfo, phones, content);
        return result;
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    private static String getTimeStamp() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(now);
    }

    public static void main(String[] args) {
        SmsMsgLinkInfo linkInfo = new SmsMsgLinkInfo();
        linkInfo.setUrl("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/http/sendBatchMessage");
        linkInfo.setUserName("sdk_yuntu6");
        linkInfo.setPassword("irtu34rt");
        send(linkInfo, "18551631462", "主人，张旭强已经还款了，快去看看~【洋葱借条】");

    }

}
