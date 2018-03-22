package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 柳朋朋
 * @Create 2016-09-06 16:13
 */
public class LatitudeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LatitudeUtils.class);

    public static final String KEY_1 = "7d9fbeb43e975cd1e9477a7e5d5e192a";

    public static final String KEY_2 = "37492c0ee6f924cb5e934fa08c6b1676";

    public static final String AK = "YN9NxXjlGwIHaWWrlpdctD27n5WPy5tq";

    /**
     * 返回输入地址的经纬度坐标
     * key lng(经度),lat(纬度)
     */
    public static Map<String, String> getGeocoderLatitude(String address) {
        BufferedReader in = null;
        try {
            //将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address=" + address + "&output=json&key=" + KEY_1);
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            Map<String, String> map = null;
            if (StringUtils.isNotEmpty(str)) {
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String, String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        } catch (Exception e) {
            // 异常日志
            LOGGER.error(e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // 异常日志
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 通过经纬度获取地址信息
     *
     * @param latitude
     * @param longitude
     * @return 城市名
     */
    public static String GetAddr(String latitude, String longitude) {
        String address = "";
        try {
            BufferedReader in = null;
            String url = "http://api.map.baidu.com/geocoder/v2/?output=json&location=" + latitude + "," + longitude + "&key=" + KEY_2;
            URL tirc = new URL(url);
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            if (StringUtils.isNotEmpty(str)) {
                int addressStart = str.indexOf("formatted_address\":");
                int addressEnd = str.indexOf(",\"business");
                if (addressStart > 0 && addressEnd > 0) {
                    address = str.substring(addressStart + 20, addressEnd - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public static String GetGpsAddr(String Lat, String Lng) throws Exception {
        BufferedReader in = null;
        String url = "http://api.map.baidu.com/geocoder/v2/?location=" + Lat + "," + Lng + "&output=json&pois=1&ak=" + AK;
        URL tirc = new URL(url);
        in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
        String res;
        StringBuilder result = new StringBuilder("");
        while ((res = in.readLine()) != null) {
            result.append(res.trim());
        }
        return result.toString();
    }

    public static void main(String args[]) {
        System.out.println(LatitudeUtils.GetAddr("30.231226", "120.20564"));
    }

}
