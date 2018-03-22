package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;

public class EmojiUtil {
	
	public static String stripEmoji(String value) {
		if (!StringUtils.isBlank(value)) {
			try {
				byte[] byteData = value.getBytes("UTF-8");
				for (int i = 0; i < byteData.length; i++) {
				    if((byteData[i] & 0xF8) == 0xF0){
				        for (int j = 0; j < 4 && i + j < byteData.length; j++) {						
				        	byteData[i+j]=0x30;					
				        }
				        i+=3;
				    }
				}
				return new String(byteData, "UTF-8");
			} catch (Exception ex) {
				return null;
			}
		}
		return null;
	}
}
