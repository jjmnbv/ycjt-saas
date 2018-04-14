package com.beitu.saas.common.utils;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/3/22
 * Time: 下午7:41
 */
public class ShortUrlUtil {


    /**
     * 生成端连接信息
     *
     * @author: Zhusw
     * @date: 2015年10月19日上午10:01:10
     */
    public static DefaultHttpClient httpclient;

    static {
        httpclient = new DefaultHttpClient();
    }

    /**
     * 生成短连接信息
     */
    public static String generateShortUrl(String url) {

        try {
            HttpPost httpost = new HttpPost("http://suo.im/api.php");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("format", "utf-8")); // 编码
            params.add(new BasicNameValuePair("url", url)); // 用户名称
            httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = httpclient.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }


    /**
     * 测试生成端连接
     *
     * @param args
     * @author: Zhusw
     * @date:2015年10月19日上午10:02:23
     */
    public static void main(String[] args) {
        generateShortUrl("https://blog.csdn.net/wh_forever/article/details/49247991");
    }
}
