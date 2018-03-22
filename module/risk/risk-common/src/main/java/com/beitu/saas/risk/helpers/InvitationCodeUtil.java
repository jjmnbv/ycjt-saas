package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class InvitationCodeUtil {
	public static final int INVITATION_CODE_LENGTH = 6;
	public static final int AGENT_INVITATION_CODE_LEN = 4;
	
	public static final String INVITATION_CODE_TYPE_NUM="NUM";//邀请码类型，全数字
	
	public static final String INVITATION_CODE_TYPE_NUM_LETTERS="NUM_LETTERS";//邀请码类型，数字字母组合

	public static String getInvitationCode(String mobile, int minLength) {
		return mobile.substring(mobile.length() - minLength);		
	}
	
	public static String getInvitationCode(int minLength) {
		return RandomStringUtils.randomNumeric(minLength);
	}
	
	public static String getCharInvitationCode(int minLength) {
		return RandomStringUtils.randomAlphanumeric(minLength);
	}
}
