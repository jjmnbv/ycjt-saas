package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class ParamUtil {
	
	public static Boolean getBoolean(Object params, String key) {
		return getBoolean((Map) params, key);
	}
	
	public static Boolean getBoolean(Map params, String key) {
		if (params == null || params.get(key) == null) {
			return null;
		}
		
		return Boolean.parseBoolean(params.get(key).toString());
	}
	
	public static Long getLong(Object params, String key) {
		return getLong((Map) params, key);
	}
	
	public static Long getLong(Map params, String key) {
		if (params == null || params.get(key) == null || StringUtils.isBlank(params.get(key).toString())) {
			return null;
		}
		
		return Long.parseLong(params.get(key).toString());
	}
	
	public static Integer getInteger(Object params, String key) {
		return getInteger((Map) params, key);
	}
	
	public static Integer getInteger(Map params, String key) {
		if (params == null || params.get(key) == null || StringUtils.isBlank(params.get(key).toString())) {
			return null;
		}
		
		return Integer.parseInt(params.get(key).toString());
	}
	
	public static String getString(Object params, String key) {
		return getString((Map) params, key);
	}
	
	public static String getString(Map params, String key) {
		if (params == null || params.get(key) == null) {
			return null;
		}
		
		return params.get(key).toString();
	}
	
	public static Float getFloat(Object params, String key) {
		return getFloat((Map) params, key);
	}
	
	public static Float getFloat(Map params, String key) {
		if (params == null || params.get(key) == null || StringUtils.isBlank(params.get(key).toString())) {
			return null;
		}
		
		return Float.parseFloat(params.get(key).toString());
	}
	
	public static Double getDouble(Object params, String key) {
		return getDouble((Map) params, key);
	}
	
	public static Double getDouble(Map params, String key) {
		if (params == null || params.get(key) == null || StringUtils.isBlank(params.get(key).toString())) {
			return null;
		}
		
		return Double.parseDouble(params.get(key).toString());
	}
	
	public static List getList(Object params, String key) {
		return getList((Map) params, key);
	}
	
	public static List getList(Map params, String key) {
		return (List) params.get(key);
	}
}
