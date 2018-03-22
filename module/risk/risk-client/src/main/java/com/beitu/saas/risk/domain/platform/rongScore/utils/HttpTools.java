package com.beitu.saas.risk.domain.platform.rongScore.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenchong@rong360.com
 * @date [2015-5-27]
 */
public class HttpTools {
	
	/*****超时时间 *****/
    public static final int TIMEOUT = 10000;
    
    
    public static String post(String url, Map<String, String> params,int connectTimeout,int readTimeout) {
        CloseableHttpClient httpclient = null;
        HttpPost httppost = new HttpPost(url);
        try {
            httpclient = HttpClients.createDefault();
            if(params == null || params.size() == 0) {
                return "";
            }
            
            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                formparams.add(new BasicNameValuePair(key, params.get(key)));
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            // 设置请求和传输超时时间
    		RequestConfig requestConfig = RequestConfig.custom()
					  .setSocketTimeout(readTimeout)
					  .setConnectTimeout(connectTimeout)
					  .setStaleConnectionCheckEnabled(true)
					  .build();
    		httppost.setConfig(requestConfig);  
            HttpResponse response = null;
            try {
                response = httpclient.execute(httppost);
            } catch (Exception e) {
                for (int i = 0; i < 2; i++) {
                    try {
                        response = httpclient.execute(httppost);
                        break;
                    } catch (Exception e2) {
                        if(i == 1) {
                            throw e2;
                        } else {
                            continue;
                        }
                    }
                }
            }
            
            try {
                if(response == null) {
                    return "";
                } else {
                    if(response.getStatusLine().getStatusCode() >= 400) {
                    }
                }
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httppost != null) {
                httppost.releaseConnection();
            }
        }
        return "";
    }
    
}
