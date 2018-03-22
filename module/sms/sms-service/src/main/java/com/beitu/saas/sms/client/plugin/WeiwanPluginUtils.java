package com.beitu.saas.sms.client.plugin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.sms.domain.SmsMsgLinkInfo;
import com.beitu.saas.sms.domain.SmsMsgReport;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.sms.util.HttpUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * 微网短信发送
 */
public class WeiwanPluginUtils {

    private static Log LOGGER = LogFactory.getLog(WeiwanPluginUtils.class);

    private static final String CHAR_SET = "UTF-8";

    /**
     * 发送调用地址
     */
    private static final String URL_ADDR_RPT = "/modules/interface/http/iservicesgetrpt.aspx";

    /**
     * 单发短信
     *
     * @param linkInfo
     * @param phone
     * @param content
     * @return
     */
    public static String send(SmsMsgLinkInfo linkInfo, String phone, String content) {
        return doSend(linkInfo, phone, content, linkInfo.getUrl());
    }

    private static String doSend(SmsMsgLinkInfo linkInfo, String phone, String content, String url) {
        String result = null;
        try {
            //组成url字符串
            String smsParam = "?flag=sendsms&loginname=" + linkInfo.getUserName() + "&p=" + phone + "&password=" + linkInfo.getPassword() + "&c=" + URLEncoder.encode(content, CHAR_SET);
            result = sendPost(linkInfo.getUrl(), smsParam);
            LOGGER.info(".=====================SendMesg,mobile:" + phone + ",content:" + content + ",statusCode:" + result);
        } catch (Exception e) {
            LOGGER.info("use WEIWANG send client msg phone:" + phone + ",content:" + content, e);
        }
        // TODO: 2017/12/5 暂时还不知道返回值
        return result;
    }

    /**
     * @param linkInfo
     * @return
     * @name 获取状态报告
     * @author wujiapeng
     */
    public static List<SmsMsgReport> getReport(SmsMsgLinkInfo linkInfo) {
        long startTime = System.currentTimeMillis();
        List<SmsMsgReport> reports = Lists.newArrayList();
        String url = linkInfo.getUrl() + URL_ADDR_RPT;
        String account = linkInfo.getUserName();
        String password = linkInfo.getPassword();
        try {
            Map<String, String> params = Maps.newHashMap();
            params.put("flag", "getreport");
            params.put("loginname", account);
            params.put("password", password);
            String result = HttpUtils.post(url, JSON.toJSONString(params), CHAR_SET);
            LOGGER.info("use WEIWANG getReport client msg ,result:" + result);
            if (null != result) {
                JSONObject obj = JSON.parseObject(result);
                String code = obj.getString("result");
                if (!"0".equals(code)) {
                    return null;
                }
                JSONArray rpts = obj.getJSONArray("reports");
                Iterator it = rpts.iterator();
                while (it.hasNext()) {
                    JSONObject json = (JSONObject) it.next();
                    SmsMsgReport report = new SmsMsgReport();
                    report.setThirdMsgId(json.getString("msgid"));
                    report.setStatus("0".equals(json.getString("status")) ? true : false);
                    reports.add(report);
                }
                return reports;
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("MENGWANG getReport cost:" + (endTime - startTime));
        } catch (Exception e) {
            LOGGER.info("DH3T getReport client msg error,linkInfo:" + linkInfo.toString(), e);
        }
        return null;
    }

    // HTTP POST request
    private static String sendPost(String url, String urlParameters) throws Exception {
        URL obj = new URL(url + urlParameters);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        StringBuffer response = new StringBuffer();
        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            LOGGER.info("Http request error code :" + responseCode);
        }
        return response.toString();

    }
}
