package com.beitu.saas.common.utils.location;

import com.beitu.saas.common.utils.NetworkUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BDLocationUtils {
    
    public static final String BAIDU_MAP_API_AK = "xvhdNSgPAAw1CM9YryGW46PFGozpaG6u";

    public static Map<String, String> getGeoInfoByIpAddress(String ipAddress) {
        Map<String, String> addressMap = new HashMap<>(8);
        String street;
        String district;
        String city;
        String province;
        String address;
        Integer cityCode;
        try {
            BufferedReader in;
            String url = "https://api.map.baidu.com/location/ip?ip=" + ipAddress + "&ak=" + BAIDU_MAP_API_AK;
            URL tirc = new URL(url);
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            if (StringUtils.isNotEmpty(str)) {
                JSONObject json = new JSONObject(str);
                Integer status = json.getInt("status");
                if (status == 0) {
                    JSONObject jsonData = json.getJSONObject("content");
                    if (jsonData != null) {
                        address = jsonData.getString("address");
                        if (address != null) {
                            addressMap.put("address", address);
                        }
                        
                        JSONObject addressComponent = jsonData.getJSONObject("address_detail");
                        if (addressComponent != null) {
                            cityCode = addressComponent.getInt("city_code");
                            if (cityCode != null) {
                                addressMap.put("cityCode", cityCode + "");
                            }
                            
                            street = addressComponent.getString("street");
                            if (street != null) {
                                addressMap.put("street", street);
                            }
                            
                            district = addressComponent.getString("district");
                            if (district != null) {
                                addressMap.put("district", district);
                            }
                            
                            city = addressComponent.getString("city");
                            if (city != null) {
                                addressMap.put("city", city);
                            }
                            
                            province = addressComponent.getString("province");
                            if (province != null) {
                                addressMap.put("province", province);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressMap;
    }
    
    /**
     * 通过经纬度获取城市和地址
     *
     * @param latitude
     * @param longitude
     * @return 城市名
     */
    public static Map<String, String> getGeoInfoByLatLng(String latitude, String longitude) {
        Map<String, String> addressMap = null;
        String street;
        String town;
        String district;
        String city;
        String province;
        String country;
        String address;
        String zipCode;
        Integer cityCode;
        Integer countryCode;
        
        try {
            BufferedReader in;
            String url = "http://api.map.baidu.com/geocoder/v2/?location=" + latitude + "," + longitude + "&output=json&ak=" + BAIDU_MAP_API_AK;
            URL tirc = new URL(url);
            in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            String str = sb.toString();
            if (StringUtils.isNotEmpty(str)) {
                JSONObject json = new JSONObject(str);
                Integer status = json.getInt("status");
                if (status == 0) {
                    JSONObject jsonData = json.getJSONObject("result");
                    if (jsonData != null) {
                        addressMap = new HashMap<String, String>();
                        
                        address = jsonData.getString("formatted_address");
                        if (address != null) {
                            addressMap.put("address", address);
                        }
                        
                        cityCode = jsonData.getInt("cityCode");
                        if (cityCode != null) {
                            addressMap.put("cityCode", cityCode + "");
                        }
                        
                        JSONObject addressComponent = jsonData.getJSONObject("addressComponent");
                        if (addressComponent != null) {
                            street = addressComponent.getString("street");
                            if (street != null) {
                                addressMap.put("street", street);
                            }
                            
                            town = addressComponent.getString("town");
                            if (town != null) {
                                addressMap.put("town", town);
                            }
                            
                            district = addressComponent.getString("district");
                            if (district != null) {
                                addressMap.put("district", district);
                            }
                            
                            city = addressComponent.getString("city");
                            if (city != null) {
                                addressMap.put("city", city);
                            }
                            
                            province = addressComponent.getString("province");
                            if (province != null) {
                                addressMap.put("province", province);
                            }
                            
                            country = addressComponent.getString("country");
                            if (country != null) {
                                addressMap.put("country", country);
                            }
                            
                            zipCode = addressComponent.getString("city");
                            if (zipCode != null) {
                                addressMap.put("zipCode", zipCode);
                            }
                            
                            countryCode = addressComponent.getInt("country_code");
                            if (countryCode != null) {
                                addressMap.put("countryCode", countryCode + "");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressMap;
    }
    
    public static String getProvinceCity(String lat, String lng, HttpServletRequest request) {
        String address = "";
        Map<String, String> locationInfo = getLocationInfoByLatLngIP(lat, lng, request);
        if (locationInfo != null) {
            if (StringUtils.isNotEmpty(locationInfo.get("province"))) {
                address = address + locationInfo.get("province");
            }
            if (StringUtils.isNotEmpty(locationInfo.get("city"))) {
                address = address + locationInfo.get("city");
            }
        }
        return address;
//        return LocationUtil.formatLocation(address);
    }
    
    private static Map<String, String> getLocationInfoByLatLngIP(String lat, String lng, HttpServletRequest request) {
        Map<String, String> addressMap = null;
        if (StringUtils.isNotEmpty(lat) && StringUtils.isNotEmpty(lng)) {
            addressMap = getGeoInfoByLatLng(lat, lng);
        }
        if (addressMap == null) {
            try {
                addressMap = getGeoInfoByIpAddress(NetworkUtil.getIpAddress(request));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return addressMap;
    }
    
}
