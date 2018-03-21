package com.beitu.saas.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Author xiaochong
 * @Create 2017/8/7 下午8:15
 * @Description
 */
public class PojoUtil {

    public static Map<String, ?> convert2Map(Object object) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        return jsonObject;
    }
}
