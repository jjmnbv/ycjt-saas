package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;

public class MaskUtil {
	public static String getMaskedUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return "";
		}
		
		return username.charAt(0) + "**";
	}
}
