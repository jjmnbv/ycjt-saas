package com.beitu.saas.sms.client.plugin;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.beitu.saas.sms.domain.SmsMsgLinkInfo;
import com.beitu.saas.sms.util.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;

/**
 * 野狗短信服务
 */
public class YeGouPluginUtils {
    private static Log LOGGER = LogFactory.getLog(YeGouPluginUtils.class);

    private final static String CHAR_SET = "UTF-8";
    private final static String CONTENT = "[\"%s\"]";
    private final static String FORMAT = "%s=%s&";

    /**
     * 发送验证码
     * @param mobile
     * @param code
     * @param linkInfo
     * @param thirdMsgId
     * @return
     */
    public static String sendVerifyCode(String mobile, String code, SmsMsgLinkInfo linkInfo, String thirdMsgId){
        long startTime = System.currentTimeMillis();
        String msgId = null;
        try {
            String url = linkInfo.getUrl();
            Map<String, String> params = new HashMap<String, String>();
            params.put("templateId", thirdMsgId);
            params.put("timestamp", String.valueOf(startTime));
            params.put("mobile", mobile);
            params.put("params", String.format(CONTENT, code));

            params = fillParam(params,linkInfo.getSign());
            String result = HttpUtils.post(url,params,CHAR_SET);
            if(StringUtils.isNotEmpty(result)){
                LOGGER.info("YEGOU send verify code message,phone:"+mobile+",code:"+code+","+ result);
                JSONObject json = JSON.parseObject(result);
                if("ok".equals(json.getString("status"))){
                    msgId = JSON.parseObject(json.getString("data")).getString("rrid");
                }
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("YEGOU send verify code message cost:"+(endTime - startTime));
            return msgId;
        } catch (Exception e) {
            LOGGER.error("{} sendMsg error.", mobile, e);
        }
        return msgId;
    }

    /**
     * 发送内容
     * @param mobile
     * @param linkInfo
     * @return
     */
    public static String sendContent(String mobile, SmsMsgLinkInfo linkInfo, String content){
        long startTime = System.currentTimeMillis();
        String result = null;
        String msgId = null;
        try {
            String url = linkInfo.getUrl();
            Map<String, String> params = new HashMap<String, String>();
            params.put("timestamp", String.valueOf(startTime));
            params.put("content", content);
            params.put("extno", "0");

            params = fillParam(params,linkInfo.getSign());

            result = HttpUtils.post(url, params,CHAR_SET);

            if(StringUtils.isNotEmpty(result)){
                LOGGER.info("YEGOU send content message,phone:"+mobile+",content:"+ content +","+ result);
                JSONObject json = JSON.parseObject(result);
                if("ok".equals(json.getString("status"))){
                    msgId = JSON.parseObject(json.getString("data")).getString("rrid");
                }
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("YEGOU send send content message cost:"+(endTime - startTime));
            return msgId;
        } catch (Exception e) {
            LOGGER.error("{} sendMsg error.", mobile, e);
        }
        return msgId;
    }

    /**
     * 填参数
     * @param params
     * @param sign
     * @return
     */
    private static Map<String, String> fillParam(Map<String, String> params,String sign){
        Map<String, Object> sortedMap = new TreeMap<String, Object>(new Comparator<String>() {
            public int compare(String arg0, String arg1) {
                return arg0.compareToIgnoreCase(arg1);
            }
        });
        sortedMap.putAll(params);

        StringBuilder sb = new StringBuilder();
        for (String s : sortedMap.keySet()) {
            sb.append(String.format(FORMAT, s, sortedMap.get(s)));
        }
        sb.append(sign);
        String signature = DigestUtils.sha256Hex(sb.toString());
        params.put("signature", signature);
        return params;
    }
    public static void main(String[] args){
        SmsMsgLinkInfo linkInfo = new SmsMsgLinkInfo();
        linkInfo.setUrl("https://api.wilddog.com/client/v1/wd4566135336fnwuik/code/send");
        linkInfo.setSign("VTFTTJFHPCRx5Y8TVIVpbB0jhgLISGvBrnyZppkx");
        sendVerifyCode("18551631462","67892",linkInfo,"100542");
                                     
    }
}
