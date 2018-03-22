package com.beitu.saas.risk.helpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JSONUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JSONUtils() {

    }

    public static ObjectMapper getInstance() {

        return objectMapper;
    }

    public static <T> T json2pojoAndOffUnknownField(String jsonStr, Class<T> clazz) throws Exception {
        if (StringUtils.isNotEmpty(jsonStr)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonStr, clazz);
        } else {
            return null;
        }
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) throws Exception {
        if (obj != null) {
            return objectMapper.writeValueAsString(obj);
        } else {
            return null;
        }
    }

    public static String obj2jsonNoException(Object obj) {
        if (obj != null) {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz)
            throws Exception {
        if (StringUtils.isNotEmpty(jsonStr)) {
            return objectMapper.readValue(jsonStr, clazz);
        } else {
            return null;
        }
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojoNoException(String jsonStr, Class<T> clazz) {
        try {

            if (StringUtils.isNotEmpty(jsonStr)) {
                return objectMapper.readValue(jsonStr, clazz);

            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * json string convert to map
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> json2map(String jsonStr)
            throws Exception {
        if (StringUtils.isNotEmpty(jsonStr)) {
            return objectMapper.readValue(jsonStr, Map.class);
        } else {
            return null;
        }
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
            throws Exception {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
                new TypeReference<Map<String, T>>() {
                });
        Map<String, T> result = new HashMap<String, T>();
        for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
            throws Exception {
        if (StringUtils.isEmpty(jsonArrayStr)) {
            return null;
        }
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr,
                new TypeReference<List<T>>() {
                });

        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2listNoException(String jsonArrayStr, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        try {
            if (StringUtils.isEmpty(jsonArrayStr)) {
                return null;
            }
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            List<Map<String, Object>> list = null;

            list = objectMapper.readValue(jsonArrayStr,
                    new TypeReference<List<T>>() {
                    });
            for (Map<String, Object> map : list) {
                result.add(map2pojo(map, clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        return objectMapper.convertValue(map, clazz);
    }

}