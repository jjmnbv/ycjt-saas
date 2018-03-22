package com.beitu.saas.sms.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.google.common.collect.Lists;

/**
 * HttpClient工具类
 *
 * @return
 * @author SHANHY
 * @create 2015年12月18日
 */
public class HttpUtils {
    private static Log LOGGER = LogFactory.getLog(HttpUtils.class);
    private static final int TIME_OUT = 30 * 1000;

    private final static int PORT_INT = 80;

    private final static int MAX_TOTAL = 200;

    private final static int MAX_PER_ROUTE = 40;

    private final static int MAX_ROUTE = 100;

    private final static int TRY_TIMES = 3;

    private static final String CHAR_SET = "UTF-8";

    private static CloseableHttpClient httpClient = null;

    private final static Object syncLock = new Object();

    private static void config(HttpRequestBase httpRequestBase) {
        // 设置Header等
        // httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
        // httpRequestBase
        // .setHeader("Accept",
        // "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        // httpRequestBase.setHeader("Accept-Language",
        // "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");// "en-US,en;q=0.5");
        // httpRequestBase.setHeader("Accept-Charset",
        // "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(TIME_OUT)
                .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * 获取HttpClient对象
     *
     * @return
     * @author SHANHY
     * @create 2015年12月18日
     */
    public static CloseableHttpClient getHttpClient(String url) {
        String hostname = url.split("/")[2];
        int port = PORT_INT;
        if (hostname.contains(":")) {
            String[] arr = hostname.split(":");
            hostname = arr[0];
            port = Integer.parseInt(arr[1]);
        }
        if (httpClient == null) {
            synchronized (syncLock) {
                if (httpClient == null) {
                    httpClient = createHttpClient(MAX_TOTAL, MAX_PER_ROUTE, MAX_ROUTE, hostname, port);
                }
            }
        }
        return httpClient;
    }


    /**
     * 创建HttpClient对象
     *
     * @return
     * @author SHANHY
     * @create 2015年12月18日
     */
    public static CloseableHttpClient createHttpClient(int maxTotal,
                                                       int maxPerRoute, int maxRoute, String hostname, int port) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
                .getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory
                .getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory> create().register("http", plainsf)
                .register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
                registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(maxPerRoute);
        HttpHost httpHost = new HttpHost(hostname, port);
        // 将目标主机的最大连接数增加
        cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,
                                        int executionCount, HttpContext context) {
                if (executionCount >= TRY_TIMES) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext
                        .adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).build();

        return httpClient;
    }

    /**
     * 设置参数
     * @param httpost
     * @param params
     */
    private static void setPostParams(HttpPost httpost,
                                      Map<String, String> params,String charSet) {
        List<NameValuePair> nvps = Lists.newArrayList();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, charSet));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("set postParams  error:",e);
        }
    }

    /**
     * POST请求URL获取内容
     *
     * @param url
     * @return
     * @author SHANHY
     * @throws IOException
     * @create 2015年12月18日
     */
    public static String post(String url, Map<String, String> params,String charSet) throws IOException {
        if(StringUtils.isEmpty(charSet)){
            charSet = CHAR_SET;
        }
        HttpPost httppost = new HttpPost(url);
        config(httppost);
        setPostParams(httppost, params,charSet);
        return execute(url,httppost,charSet);
    }

    /**
     * POST请求URL
     * @param url
     * @param data
     * @param charSet
     * @return
     * @throws IOException
     */
    public static String post(String url, String data,String charSet) throws IOException {
        if(StringUtils.isEmpty(charSet)){
            charSet = CHAR_SET;
        }
        HttpPost httppost = new HttpPost(url);
        config(httppost);

        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(data, charSet);
        httppost.setEntity(se);
        return execute(url,httppost,charSet);
    }
    /**
     * GET请求URL获取内容
     *
     * @param url
     * @return
     * @author SHANHY
     * @create 2015年12月18日
     */
    public static String get(String url,String charSet) {
        if(StringUtils.isEmpty(charSet)){
            charSet = CHAR_SET;
        }
        HttpGet httpget = new HttpGet(url);
        config(httpget);
        return execute(url,httpget,charSet);
    }

    /**
     * 执行请求
     * @param url
     * @param http
     * @return
     */
    public static String execute(String url,HttpRequestBase http,String charSet){
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = getHttpClient(url).execute(http,
                    HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, charSet);
            EntityUtils.consume(entity);
            return result;
        }catch (ConnectTimeoutException e) {
            LOGGER.error("url:"+url+",execute post error 1:",e);
        }catch (ConnectException e) {
            LOGGER.error("url:"+url+",execute post error 2:",e);
        }catch (Exception e) {
            LOGGER.error("url:"+url+",execute post error 3:",e);
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (Exception e) {
                LOGGER.error("url:"+url+",execute post error 4:",e);
            }
        }
        return result;
    }

}