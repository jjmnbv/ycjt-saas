package com.beitu.saas.risk.handler.carrier;

import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;
import com.fqgj.common.utils.MD5;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.google.gson.Gson;
import com.beitu.saas.risk.helpers.SortCollection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcg on 15/4/9.
 */
public class RestCallbackUtils {
    private static final Log LOGGER = LogFactory.getLog("tianji_log");

    public static final String APPSECRET = "c1abe9d4f56b4fb1864148a4dbf5af00";

    public static String timestamp() {
        return (System.currentTimeMillis() / 1000) + "";
    }


    public static Boolean getCallbackResult(String callBackUrl, Map<String, Object> boby) {
        try {
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setConnectionRequestTimeout(30000);
            httpRequestFactory.setConnectTimeout(30000);
            RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
            Gson gson = new Gson();

            HashMap<String, Object> signBody = new HashMap<String, Object>();
            String timestamp = RestCallbackUtils.timestamp();
            signBody.put("timestamp", timestamp);
            signBody.put("status", boby.get("status"));
            signBody.put("taskId", boby.get("taskId"));
            signBody.put("userCode", boby.get("userCode"));

            String sourceStr = SortCollection.sort(RestCallbackUtils.APPSECRET, signBody);


            String sign = MD5.md5(sourceStr);
            boby.put("sign", sign);
            boby.put("timestamp", timestamp);

            String url = UriComponentsBuilder
                    .fromHttpUrl(callBackUrl)
                    .build().toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json;charset=utf-8");

            HttpEntity request = new HttpEntity(gson.toJson(boby), headers);
            ResponseEntity responseEntity = restTemplate.postForEntity(url, request, String.class);
            Map map = (Map) gson.fromJson(responseEntity.getBody().toString(), Map.class);
            LOGGER.info("h5运营商回调url:{},map：{}", callBackUrl, gson.toJson(map));
            if (map.get("success") != null && Boolean.valueOf(map.get("success").toString())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.info("h5运营商回调url:{}异常,e：", callBackUrl, e);
            return false;
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("taskId", "123");
        map.put("status", "123");
        map.put("data", "123");
        map.put("userCode", "123");
        map.put("carrierType", CarrierH5TypeEnum.CARRIER_TIANJI);
        getCallbackResult("http://192.168.2.65:8833/carrier/h5/callback", map);
    }

}
