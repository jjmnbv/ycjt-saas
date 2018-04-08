package com.beitu.saas.common.handle.dianhua;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.carrier.CarrierHandler;
import com.beitu.saas.common.handle.carrier.domain.CarriersPhoneCallVo;
import com.beitu.saas.common.handle.carrier.domain.CarriersVo;
import com.beitu.saas.common.handle.dianhua.domain.CarrierCallLogVo;
import com.beitu.saas.common.handle.dianhua.domain.DunningBodyVo;
import com.beitu.saas.common.utils.ZipUtil;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by linchengyu on 17/8/1.
 */
//电话邦接入
@Component
public class DianhuaHandler {

    private static final Log LOGGER = LogFactory.getLog(DianhuaHandler.class);

    /**
     * api key
     */
//    private static final String apikey = "7b094f26255fea07f0df258127d8b6c1";
    /**
     * secret
     */
//    private static final String appsecret = "a510e00dfa211ce6b7597902ccd9e86355c230a354fd7b7b670f25d51ac901b0";
    /**
     * 超时时间
     */
    private static final int TIME_OUT = 10 * 1000;
    /**
     * content type
     */
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    /**
     * 编码格式
     */
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

    @Autowired
    private CarrierHandler carrierHandler;

    @Autowired
    private ConfigUtil configUtil;

    public String getDunningVo(String carrierUrl) {
        CarriersVo carriersVo = carrierHandler.getCarriersVo(carrierUrl);
        if (carriersVo == null && CollectionUtils.isEmpty(carriersVo.getCarriersCallVoList())) {
            LOGGER.error("电话邦获取运营商数据有误");
        }
        List<CarrierCallLogVo> callLogs = new ArrayList<>();
        for (CarriersPhoneCallVo carriersPhoneCallVo : carriersVo.getCarriersCallVoList()) {
            CarrierCallLogVo logVo = new CarrierCallLogVo(carriersPhoneCallVo);
            callLogs.add(logVo);
        }
        DunningBodyVo bodyVo = new DunningBodyVo();
        bodyVo.setTel(carriersVo.getMobile());
        Long time = System.currentTimeMillis() / 1000;
        bodyVo.setTime(time);
        bodyVo.setCallLong(callLogs);

        String apikey = configUtil.getDhbApiKey();
        String appsecret = configUtil.getDhbAppSecret();

        String url = "https://cuishou-api.dianhua.cn/busi/genCuiShou?apikey=" + apikey + "&time=" + time;
        Map<String, String> map = new TreeMap<String, String>();
        map.put("apikey", apikey);
        map.put("time", time + "");
        String sig = Signature(map, appsecret);
        // 得到api请求url
        String sigUrl = url + "&sig=" + sig;

        return request(sigUrl, bodyVo);
    }

    /**
     * 编码
     *
     * @return
     */
    private String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    /**
     * 获取ContentType
     *
     * @return
     */
    private String getBodyContentType() {
        return "app/json; charset=" + getParamsEncoding();
    }

    private String Signature(Map map, String appsecret) {
        String sig_str = "";

        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            sig_str = sig_str + s + map.get(s);
        }
        sig_str = appsecret + sig_str + appsecret;
        return SHA1(sig_str);
    }

    private String SHA1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes(Charset.forName("UTF-8")));
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String request(String url, DunningBodyVo bodyVo) {
        try {
            URL parsedUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) parsedUrl.openConnection();
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty(HEADER_CONTENT_TYPE, getBodyContentType());
            connection.setDoOutput(true);
            //压缩提交数据
            connection.setRequestProperty("Content-Encoding", "gzip");
            String bodyStr = JSON.toJSONString(bodyVo);
            // byte[] body = bodyStr.getBytes(DEFAULT_PARAMS_ENCODING);
            byte[] body = ZipUtil.compress(bodyStr);
            if (body != null) {
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.write(body);
                out.close();
            }
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                LOGGER.error("电话邦请求失败： code:" + responseCode + " response:" + getContent(connection.getErrorStream()));
                return null;
            }
            String responseData = getContent(connection.getInputStream());
            return responseData;
        } catch (Exception e) {
            LOGGER.error("电话邦请求处理异常:" + e);
        }
        return null;
    }

    private String getContent(InputStream in) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("电话邦返回流处理异常:" + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                LOGGER.error("电话邦返回流处理异常:" + e);
            }
        }
        return sb.toString();
    }
}
