package com.beitu.saas.risk.helpers;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileUtil {
	
	private static final Pattern MobilePattern = Pattern.compile("^\\d{11}$");
	
	private static final Pattern QQPattern = Pattern.compile("^\\d{1,10}$");

	/**
	 * 手机号码验证
	 * 考虑以后号码段会变化，所以15和18写的范围就比较大，可能有些号段现在还没有
	 * @param mobiles 手机号码
	 * @return true/false
	 */
	public static boolean isMobile(String mobile) {
		Matcher m = MobilePattern.matcher(mobile);
		return m.matches();
	}
	
	public static boolean isQQ(String qq) {
		return QQPattern.matcher(qq).matches();
	}
	
	public static String hidden(String mobile){
		if(StringUtils.isNotEmpty(mobile)){
			mobile = mobile.substring(0,3)+"****"+mobile.substring(7,11);
			return mobile;
		}
		return null;
	}
	


	/** 
	 * imei编号由15位数字组成， 
	 * 前6位(TAC)是型号核准号码，代表手机类型。 
	 * 接着2位(FAC)是最后装配号，代表产地。 
	 * 后6位(SNR)是串号，代表生产顺序号。 
	 * 最后1位 (SP)是检验码。  
	 *  
	 * 检验码计算： 
	 * (1).将偶数位数字分别乘以2，分别计算个位数和十位数之和 
	 * (2).将奇数位数字相加，再加上上一步算得的值 
	 * (3).如果得出的数个位是0则校验位为0，否则为10减去个位数 
	 *  
	 * @author sonzer 
	 *  
	 */ 
	public static String creatImeiCode(){
		//864710 02 131998 2
		String imeiString = "86471002"+StrUtils.randNum(6);//前14位  
        char[] imeiChar=imeiString.toCharArray();  
        int resultInt=0;  
        for (int i = 0; i < imeiChar.length; i++) {  
            int a=Integer.parseInt(String.valueOf(imeiChar[i]));  
            i++;  
            final int temp=Integer.parseInt(String.valueOf(imeiChar[i]))*2;  
            final int b=temp<10?temp:temp-9;  
            resultInt+=a+b;  
        }  
        resultInt%=10;  
        resultInt=resultInt==0?0:10-resultInt;
//        System.out.println("imei:"+imeiString+resultInt);  
        return imeiString+String.valueOf(resultInt);
	}
    
	
	
}
