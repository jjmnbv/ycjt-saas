package com.beitu.saas.risk.helpers;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.ua.DSTU4145NamedCurves.params;

/**
 * @Author poyangchen
 * @Create 2017-03-31 下午8:56
 * @Description
 */
public class HttpUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String postPage(String url, Map<String, String> parameters) {
        return postPage(url, parameters, null, DEFAULT_CHARSET);

    }

    public static String postPage(String url, Map<String, String> parameters, String charset) {
        return postPage(url, parameters, null, charset);

    }

    public static String getPage(String url, Map<String, String> parameters) {
        return getPage(url, parameters, null, DEFAULT_CHARSET);

    }

    public static String getPage(String url) {
        return getPage(url, null, null, DEFAULT_CHARSET);

    }

    public static String getPage(String url, String charset) {
        return getPage(url, null, null, charset);

    }

    public static String getPage(String url, Map<String, String> parameters, String charset) {
        return getPage(url, parameters, null, charset);

    }

    public static String postPage(String url, Map<String, String> parameters, Map<String, String> headers) {
        return postPage(url, parameters, headers, DEFAULT_CHARSET);

    }

    public static String getPage(String url, Map<String, String> parameters, Map<String, String> headers) {

        return getPage(url, parameters, headers, DEFAULT_CHARSET);
    }

    public static String postPage(String url, Map<String, String> parameters, Map<String, String> headers, String charset) {
        String result = "";

        CloseableHttpClient client = HttpClients.createDefault();


        HttpPost httppost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(20000).setConnectionRequestTimeout(10000).build();

        httppost.setConfig(requestConfig);
        if (CollectionUtils.isNotEmpty(headers)) {
            pushParamsToHeader(httppost, headers);
        }

        List<NameValuePair> list = pushParamsToNameValuePair(parameters);

        CloseableHttpResponse response = null;

        try {
            // 构建url加密实体，并以utf-8方式进行加密；
            httppost.setEntity(new UrlEncodedFormEntity(list, charset));
            response = client.execute(httppost);

//            if (response.getStatusLine().getStatusCode() == 200) {
            result = EntityUtils.toString(response.getEntity(), charset);
//            }
        } catch (Exception e) {
            LOGGER.error("post request error,url:" + url + ",params:" + params, e);
        } finally {
            close(response);
            close(client);
        }
        return result;
    }

    public static String putPage(String pageUrl, Map<String, String> headers) {

        String result = "";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpPut httpPut = new HttpPut(pageUrl);

        if (CollectionUtils.isNotEmpty(headers)) {
            pushParamsToHeader(httpPut, headers);
        }

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPut);
//            if (response.getStatusLine().getStatusCode() == 200) {
            result = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
//            }
        } catch (Exception e) {
            LOGGER.error("put request error,url:" + pageUrl, e);
        } finally {
            close(response);
            close(client);
        }
        return result;
    }

    public static String getPage(String pageUrl, Map<String, String> parameters, Map<String, String> headers, String charset) {

        String result = "";
        CloseableHttpClient client = HttpClients.createDefault();

        pageUrl = joinGetUrl(pageUrl, parameters);
        HttpGet httpGet = new HttpGet(pageUrl);

        if (CollectionUtils.isNotEmpty(headers)) {
            pushParamsToHeader(httpGet, headers);
        }

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
//            if (response.getStatusLine().getStatusCode() == 200) {
            result = EntityUtils.toString(response.getEntity(), charset);
//            }
        } catch (Exception e) {
            LOGGER.error("get request error,url:" + pageUrl, e);
        } finally {
            close(response);
            close(client);
        }
        return result;
    }

    public static void close(CloseableHttpResponse response) {
        if (response != null) {
            try {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    InputStream in = httpEntity.getContent();
                    if (in != null) {
                        in.close();
                    }
                }
            } catch (Exception ex) {
                // ignore
            }

            try {
                response.close();
            } catch (Exception ex) {
                // ignore
            }
        }
    }

    public static String postPageBody(String pageUrl, Map<String, String> headers, String requestBody) {

        String result = "";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(pageUrl);

        if (CollectionUtils.isNotEmpty(headers)) {
            pushParamsToHeader(httpPost, headers);
        }
        CloseableHttpResponse response = null;
        try {

            httpPost.setEntity(new StringEntity(requestBody, DEFAULT_CHARSET));
            response = client.execute(httpPost);

            result = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            LOGGER.error("post request error,url: {}, params: {}.", pageUrl, requestBody, e);
        } finally {
            close(response);
            close(client);
        }

        return result;
    }

    public static void close(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (Exception ex) {
                // ignored
            }
        }
    }

    public static List<NameValuePair> pushParamsToNameValuePair(Map<String, String> parameters) {
        // 构建请求参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();

        //添加参数
        if (CollectionUtils.isNotEmpty(parameters)) {
            Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                list.add(new BasicNameValuePair(key, value));
            }

        }
        return list;
    }

    public static String joinGetUrl(String pageUrl, Map<String, String> parameters) {

        if (CollectionUtils.isEmpty(parameters)) {
            return pageUrl;
        }
        // create post request
        StringBuilder params = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (i == parameters.size()) {
                params.append(entry.getKey() + "=" + entry.getValue());
            } else {
                params.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            i++;
        }

        return pageUrl + "?" + params;
    }

    public static void pushParamsToHeader(HttpGet httpGet, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
    }

    public static void pushParamsToHeader(HttpPut httpPut, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPut.addHeader(entry.getKey(), entry.getValue());
        }
    }

    public static void pushParamsToHeader(HttpPost httpPost, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
    }

    public static byte[] getImageBytes(String pageUrl) {

        byte[] result = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(pageUrl);

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            result = EntityUtils.toByteArray(response.getEntity());
        } catch (Exception e) {
            LOGGER.error("get request error,url:" + pageUrl, e);
        } finally {
            close(response);
            close(client);
        }
        return result;
    }

    public static String submitFile(String url, Map<String, String> parameters, Map<String, byte[]> files) {
        return submitFile(url, parameters, files, DEFAULT_CHARSET);

    }

    public static String submitFile(String url, Map<String, String> parameters, Map<String, byte[]> files, String charset) {
        String result = "";

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entityBuilder.setCharset(Charset.forName(charset));

        if (CollectionUtils.isNotEmpty(parameters)) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                entityBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create("text/plain", Charset.forName(charset)));
            }
        }

        if (CollectionUtils.isNotEmpty(files)) {
            for (Map.Entry<String, byte[]> entry : files.entrySet()) {
                entityBuilder.addBinaryBody(entry.getKey(), entry.getValue(), ContentType.APPLICATION_OCTET_STREAM, entry.getKey());
            }
        }

        CloseableHttpResponse response = null;

        try {
            HttpEntity httpEntity = entityBuilder.build();
            httppost.setEntity(httpEntity);
            response = client.execute(httppost);
            result = EntityUtils.toString(response.getEntity(), charset);
        } catch (Exception e) {
            LOGGER.info("submit file request error,url:" + url + ",params:" + params, e);
        } finally {
            close(response);
            close(client);
        }
        return result;
    }
}
