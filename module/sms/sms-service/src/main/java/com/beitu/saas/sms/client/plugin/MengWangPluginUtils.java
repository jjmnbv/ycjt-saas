package com.beitu.saas.sms.client.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.beitu.saas.sms.domain.SmsMsgBatchSendInfo;
import com.fqgj.common.utils.MD5;
import com.beitu.saas.sms.domain.SmsMsgLinkInfo;
import com.beitu.saas.sms.domain.SmsMsgReport;
import com.beitu.saas.sms.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.google.common.collect.Lists;

/**
 * 梦网短信接口测试
 */
public class MengWangPluginUtils {

    private static Log LOGGER = LogFactory.getLog(MengWangPluginUtils.class);

    private static final String DATE_FORMAT = "MMddHHmmss";
    private static final String CHAR_SET = "GBK";
    private static final String URL_ADDR_SINGLE = "http://61.130.7.220:8023/client/v2/std/single_send";
    private static final String URL_ADDR_BATCH = "http://61.130.7.220:8023/client/v2/std/batch_send";
    private static final String URL_ADDR_MULTI = "http://61.130.7.220:8023/client/v2/std/multi_send";
//    private static final String URL_ADDR_RPT = "/client/v2/std/get_rpt";
//    private static final String URL_ADDR_BALANCE = "/client/v2/std/get_balance";
    private static final String USER_NAME = "J52133";
    private static final String PWD = "321623";
    private static final String SIGN = "00000000";

    /**
     * 单条发送
     * @param phone
     * @param content
     * @return
     */
    public static String send(SmsMsgLinkInfo linkInfo, String phone, String content){
        String url = linkInfo.getUrl();
        String msgId = doSend(linkInfo,phone,content,url,null);
        return msgId;
    }

    /**
     * 批量发送(最多1000个手机号)
     * @param phoneList
     * @param content
     * @return
     */
    public static String batchSend(SmsMsgLinkInfo linkInfo, List<String> phoneList, String content){
        String url = linkInfo.getUrl();
        String phones = StringUtils.join(phoneList,",");
        String msgId = doSend(linkInfo,phones,content,url,null);
        return msgId;
    }

    /**
     * 发送短信
     * @param phone
     * @param content
     * @param url
     * @return
     */
    private static String doSend(SmsMsgLinkInfo linkInfo,String phone,String content,String url,String multiContent){
        long startTime = System.currentTimeMillis();
        String msgId = null;
        try {
            String timeStamp = getTimeStamp();
            String pwd = getPWD(linkInfo,timeStamp);
            Map<String, String> params = new HashMap<String, String>();
            params.put("userid", linkInfo.getUserName());
            params.put("pwd",pwd);
            if(StringUtils.isEmpty(multiContent)){
                params.put("mobile",phone);
                params.put("content", content);
            }else {
                params.put("multimt",multiContent);
            }
            params.put("timestamp", timeStamp);

            String result = HttpUtils.post(url,params,CHAR_SET);
            if(StringUtils.isNotEmpty(result)){
                LOGGER.info("MENGWANG send client message,phone:"+phone+",content:"+ content +","+ result);
                JSONObject obj = JSON.parseObject(result);
                msgId = obj.getString("msgid");
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("MENGWANG send client message cost:"+(endTime - startTime));
            return msgId;
        } catch (Exception e) {
            LOGGER.error("{}MENGWANG sendMsg error.", phone, e);
        }
        return msgId;
    }

    /**
     * 不同内容批量发送(最多100个手机号)
     * @param infos
     * @return
     */
    public static String multiSend(SmsMsgLinkInfo linkInfo,List<SmsMsgBatchSendInfo> infos){
        String url = URL_ADDR_MULTI;  // TODO: 2017/12/12 暂未配置
        String msgId = doSend(linkInfo,null,null,url,JSON.toJSONString(infos));
        return msgId;
    }

    /**
     * 获取状态报告(最多500)
     * @param retSize
     * @return
     */
    public static List<SmsMsgReport> getReport(SmsMsgLinkInfo linkInfo, Integer retSize){
        long startTime = System.currentTimeMillis();
        String url = linkInfo.getUrl() + linkInfo.getQueryMsgReportUrlInterfaceUrl();
        List<SmsMsgReport> reports = Lists.newArrayList();
        try {
            String timestamp = getTimeStamp();
            String pwd = getPWD(linkInfo,timestamp);
            Map<String, String> params = new HashMap<String, String>();
            params.put("userid", linkInfo.getUserName());
            params.put("pwd",pwd);
            params.put("timestamp", timestamp);
            params.put("retsize",retSize.toString());

            String data = HttpUtils.post(url,params,CHAR_SET);
            if(StringUtils.isNotEmpty(data)){
                LOGGER.info("MENGWANG send client message:"+ data);
                JSONObject json = JSON.parseObject(data);
                if(json.getInteger("result") == 0){
                    //成功
                    JSONArray jsonArray = json.getJSONArray("rpts");
                    Iterator iterator = jsonArray.iterator();
                    while(iterator.hasNext()){
                        JSONObject obj = (JSONObject) iterator.next();

                        String msgId = obj.getLong("msgid").toString();
                        Integer status = obj.getInteger("status");
                        SmsMsgReport report = new SmsMsgReport();
                        report.setThirdMsgId(msgId);
                        report.setStatus(status==0?true:false);
                        reports.add(report);
                    }

                }
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("MENGWANG getReport cost:"+(endTime - startTime));

            return reports;
        } catch (Exception e) {
            LOGGER.error("{}MENGWANG getReport error.", retSize, e);
        }
        return reports;
    }

    /**
     * 获取账号余额
     * @return
     */
    public static String getBalance(SmsMsgLinkInfo linkInfo){
        long startTime = System.currentTimeMillis();
        String url = linkInfo.getUrl() + linkInfo.getQueryBalanceInterfaceUrl();
        String result = null;
        try {
            String timeStamp = getTimeStamp();
            String pwd = getPWD(linkInfo,timeStamp);
            Map<String, String> params = new HashMap<>();
            params.put("userid", linkInfo.getUserName());
            params.put("pwd",pwd);
            params.put("timestamp", timeStamp);

            result = HttpUtils.post(url,params,CHAR_SET);
            if(StringUtils.isNotEmpty(result)){
                LOGGER.info("MENGWANG send client message:"+ result);
                JSONObject obj = JSON.parseObject(result);
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("MENGWANG getBalance cost:"+(endTime - startTime));
            return result;
        } catch (Exception e) {
            LOGGER.error("{}MENGWANG getBalance error.", e);
        }
        return result;

    }
    /**
     * 获取时间戳
     * @return
     */
    private static String getTimeStamp(){
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(now);
    }

    /**
     * 获取密码加密串
     */
    private static String getPWD(SmsMsgLinkInfo linkInfo,String timestamp){
        StringBuffer pwd = new StringBuffer();
        pwd.append(linkInfo.getUserName().toUpperCase())
                .append(linkInfo.getSign())
                .append(linkInfo.getPassword())
                .append(timestamp);
        return MD5.md5(pwd.toString());
    }

    public static void main(String[] args){
//        SmsMsgLinkInfo linkInfo = new SmsMsgLinkInfo();
//        linkInfo.setUrl(URL_ADDR_SINGLE);
//        linkInfo.setUserName(USER_NAME);
//        linkInfo.setPassword(PWD);
//        linkInfo.setSign(SIGN);
//        send(linkInfo,"18551631462","恭喜您已还款成功！ 由于您信用良好，您的额度已获得提升，请登录闪电周转APP查看");
                                     
    }
}
