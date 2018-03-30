package com.beitu.saas.sms.client.plugin;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.sms.domain.SmsMsgLinkInfo;
import com.beitu.saas.sms.util.HttpUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaochong
 * @create 2018/3/30 下午3:17
 * @description
 */
public class ChuangLanPluginUtils {

    private static Log LOGGER = LogFactory.getLog(ChuangLanPluginUtils.class);
    private static String CHAR_SET = "UTF-8";
    private static String DATE_FORMAT = "yyyyMMddHHmm";


    public static String send(SmsMsgLinkInfo linkInfo, String phone, String content) {
        String account = linkInfo.getUserName();
        String password = linkInfo.getPassword();
        String url = linkInfo.getUrl();
        String timeStamp = getTimeStamp();
        String result = null;
        try {
            Map params = new HashMap(8);
            params.put("account", account);
            params.put("password", password);
            params.put("phone", phone);
            params.put("msg", content);
            params.put("sendtime", timeStamp);
            params.put("report", false);
            result = HttpUtils.post(url, JSON.toJSONString(params), CHAR_SET);
            LOGGER.info("ChuangLan send client phone:" + phone + ",content" + content + ",result:" + result);
        } catch (Exception e) {
            LOGGER.error("{}ChuangLan sendMsg error.", phone, content, e);
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
        String phones = StringUtils.join(phoneList, ",");
        String result = send(linkInfo, phones, content);
        return result;
    }

    private static String getTimeStamp() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(now);
    }


    public static void main(String[] args) {
        SmsMsgLinkInfo linkInfo = new SmsMsgLinkInfo();
        linkInfo.setUrl("https://sapi.253.com/msg/HttpBatchSendSM");
        linkInfo.setUserName("18667872695");
        linkInfo.setPassword("a123456");
        String send = send(linkInfo, "17665287788", "您本次登入的短信验证码为{2345}，请及时进行登入");
        System.out.print(send);
    }
}
