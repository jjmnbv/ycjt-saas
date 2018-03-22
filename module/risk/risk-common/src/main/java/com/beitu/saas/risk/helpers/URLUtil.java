package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class URLUtil {

	public static String getHost(String url) {
		int domainStartIdx = url.indexOf("//") + 2;
		int domainEndIdx = url.indexOf('/', domainStartIdx);
        domainEndIdx = domainEndIdx > domainStartIdx ? domainEndIdx : url.length();
        
        String host = url.substring(0, domainEndIdx);
        return host;
	}
	
	public static String getName(String url) {
	    int domainStartIdx = url.indexOf("//") + 2;
        int domainEndIdx = url.indexOf('/', domainStartIdx);
        
        String name = url.substring(domainEndIdx + 1);
        return name.replaceAll("/", ".").replaceAll("\\?", ".");
	}
	
	public static String getFilename(String url) {
	    int startIdx = url.lastIndexOf('/');
	    
	    if (startIdx < 0) return "";
	    
	    int endIdx = url.indexOf('?', startIdx);
	    if (endIdx < 0) {
	        return url.substring(startIdx + 1);
	    } else {
	        return url.substring(startIdx + 1, endIdx);
	    }
	}
	
	public static Map<String, String> parseParameters(String params) {
		if (StringUtils.isBlank(params)) {
			return new HashMap<String, String>();
		}
		
		String[] parts = params.split("&");
		Map<String, String> results = new HashMap<String, String>();
		for (String part : parts) {
			if (StringUtils.isBlank(part)) {
				continue;
			}
			
			String[] subParts = part.split("=");
			if (subParts.length == 2) {
				results.put(subParts[0], subParts[1]);
			}
		}
		
		return results;
	}
}
