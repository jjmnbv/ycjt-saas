package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/4/12
 * Time: 下午8:00
 */
public class TongdunUtils {

    public static Map<String, Map<String, String>> itemExplainer(String key, String content) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(content)) {
            return null;
        }

        Map<String, Map<String, String>> res = new HashMap<>();

        String[] items = content.split("<br>");
        for (String item : items) {
            if (!item.contains(key)) {
                continue;
            }

            item = item.replace("：", ":");

            Map<String, String> childMap = new HashMap<>();
            if (!item.contains("[")) {
                res.put(item, null);
                continue;
            }

            String[] parent = item.split("\\[");
            String parentKey = parent[0].substring(0, parent[0].length() - 1);

            String[] childrens = parent[1].substring(0, parent[1].length() - 1).split(",");

            for (String child : childrens) {
                String[] c = child.split(":");
                childMap.put(c[0].substring(1), c[1].substring(0,c[1].length()-1));
            }

            res.put(parentKey, childMap);
        }

        return res;
    }

    public static void main(String[] args) {
        Map<String, Map<String, String>> res = TongdunUtils.itemExplainer("3个月", "3个月内身份证关联多个申请信息:[\"3个月身份证关联邮箱数：0\",\"3个月身份证关联手机号数：2\"]<br>3个月内申请信息关联多个身份证:[\"3个月手机号关联身份证数：3\"]<br>3个月内申请人主要借贷信息在多个网贷平台进行借款申请<br>3个月内身份证在多个网贷平台进行借款申请:[\"一般消费分期平台:3\",\"P2P网贷:1\"]<br>3个月内手机号在多个网贷平台进行借款申请:[\"一般消费分期平台:3\"]<br>1个月内设备或身份证或手机号申请次数过多:[\"1个月内手机号申请次数>=5：60\"]<br>");
        System.err.println("111");
    }
}
