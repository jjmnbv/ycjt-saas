package com.beitu.saas.risk.domain.platform.rongScore.utils;

import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.risk.domain.platform.rongScore.client.RongClient;
import com.beitu.saas.risk.domain.platform.rongScore.client.ClientManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guocong
 *         Created by Administrator on 2016/3/31.
 */
public class RequestUtil {
    public static String request(String method, Map<String, Object> bizData) throws Exception {
        Map<String, String> params = new HashMap<String,String>();
        params.put("method", method);
        params.put("biz_data", JSONObject.toJSONString(bizData).toString());

        RongClient client = ClientManager.createClient();
        return client.execute(params);
    }
}
