package com.beitu.saas.risk.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
	/**
	 * 判断字符串是否是整形数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if(str==null || str.trim().equals("")) return false;
		Pattern pattern = Pattern.compile("[-]?[.0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 判断字符串是否是中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChineseChar(String str) {
		if(str==null || str.trim().equals("")) return false;
		boolean temp = false;
       Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
       Matcher m=p.matcher(str); 
       if(m.find()){ 
           temp =  true;
       }
       return temp;
	}

	/**
	 * 字符串转为整型
	 * @param obj
	 * @return
	 */
	public static int strToInt(Object obj ,int defaultNum){
		if(obj==null) return defaultNum;
		String str = String.valueOf(obj);
		if(!StrUtils.isNumeric(str)) return defaultNum;
		try{
			return Integer.valueOf(str);
		}catch(Exception e){
			return defaultNum;
		}
	}
	public static int strToInt(Object obj){
		return strToInt(obj ,0);
	}
	
	/**
	 * 通过正则表达式找到第一个匹配的字符串
	 * @param source
	 * @param regStr
	 * @return
	 */
	public static String regFindFirst(String source ,String regStr){
		return regFindFirst( source , regStr ,1);
	}

	public static String regFindFirst(String source ,String regStr ,int groupIndex){
//		final Pattern orderNumberPattern = Pattern.compile("(\\d+(\\.\\d+)?)");
		if(source==null || regStr==null) return "";
		try{
		final Pattern pattern = Pattern.compile(regStr);
		Matcher matcher = pattern.matcher(source);
		if(matcher.find()){
			return matcher.group(groupIndex);
		}
		}catch(Exception e){
			
		}
		return "";
	}
/**
 * 
 * @param sourceDate
 * @param formateStr SimpleDateFormat repaymentDateF= new SimpleDateFormat("MMM dd, yyyy",Locale.ENGLISH);//英文日期
 * "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT  
"EEE, MMM d, ''yy"  Wed, Jul 4, '01  
"h:mm a"  12:08 PM  
"hh 'o''clock' a, zzzz"  12 o'clock PM, Pacific Daylight Time  
"K:mm a, z"  0:08 PM, PDT  
"yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM  
"EEE, d MMM yyyy HH:mm:ss Z"  Wed, 4 Jul 2001 12:08:56 -0700  
"yyMMddHHmmssZ"  010704120856-0700  
"yyyy-MM-dd'T'HH:mm:ss.SSSZ"  2001-07-04T12:08:56.235-0700  

 * @return
 */
	public static Date strToDateForEn(String sourceDate ,String formateStr){

		SimpleDateFormat dateF= new SimpleDateFormat(formateStr ,Locale.ENGLISH);
		try{
			return dateF.parse(sourceDate);
		}catch(Exception e){
			
		}
		
		return null;
		
	}
	
/**
 * 产品随机数字
 * @param maxLen
 * @return
 */
	public static String randNum(int maxLen){
		if(maxLen<=0) maxLen=4;
		StringBuilder retStr = new StringBuilder("");
		for(int i=0;i< maxLen;i++){
	        int max=9;
	        int min=0;
	        java.util.Random random = new java.util.Random();

	        int s = random.nextInt(max)%(max-min+1) + min;
	        retStr.append(String.valueOf(s));
		}
		
		return retStr.toString();
		
	}
	
	/**
	 * 去除特殊字符分隔的空字符
	 * @param inStr
	 * @param splitStr
	 * @return
	 */
	public static String trimSplit(String inStr ,String splitStr){
		if(inStr==null || inStr.trim().equals("")) return "";
		if(splitStr==null || splitStr.trim().equals("")) return inStr;
		if(inStr.indexOf(splitStr) < 0) return inStr;

		String[] tempArray = inStr.split(splitStr);
		return trimSplit(tempArray ,splitStr);
		
		
	}

	public static String trimSplit(String[] inArray ,String splitStr){
		if(inArray==null ) return "";
		if(splitStr==null || splitStr.trim().equals("")) splitStr=",";
		StringBuilder tempBu = new StringBuilder();

		for(String temp : inArray){
			if(temp.trim().equals("")) continue;
			if(tempBu.length()>0){
				tempBu.append(splitStr);
			}
			tempBu.append(temp);
		}
		
		return tempBu.toString();
	}
	
	/**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
     * 要用到正则表达式
     */
    public static String digitUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        String head = n < 0? "负": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
	
	public static void main(String args[]){
		
		System.out.println(StrUtils.digitUppercase(547.48));
		
	}
}
