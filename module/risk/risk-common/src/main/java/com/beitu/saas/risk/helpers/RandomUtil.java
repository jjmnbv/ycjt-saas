package com.beitu.saas.risk.helpers;

import java.util.Random;

public class RandomUtil {
   
   /**
    * 生成纯数字验证码
    * @param digit 生成验证码的位数
    */
	public static String getVerifyCode(int digit){  
	   String val = "";     //密码
       Random random = new Random();     
       for(int i = 0; i < digit; i++)     
       {     
          val += String.valueOf(random.nextInt(10));     
       }
       return val;
    }
	
	/**
    * 生成数字和字母验证码
    * @param digit 生成验证码的位数
    * @param type 是否包含大小写字母 0：大小写，1：全大写，2：全小写
    */
	public static String getVerifyCode(int digit,String type){
		String val = "";     //密码
        Random random = new Random();     
        for(int i = 0; i < digit; i++)     
        {     
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字     
            if("char".equalsIgnoreCase(charOrNum)) // 字符串     
            {     
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母     
                val += (char) (choice + random.nextInt(26));     
            }     
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));     
            }     
        }
        if("1".equals(type)){
        	return val.toUpperCase();
        }
        if("2".equals(type)){
        	return val.toLowerCase();
        }
        return val;
    }

    public static Boolean oneOfTwo() {
        Random random = new Random();// 定义随机类
        int result = random.nextInt(2) + 1;// 返回[0,16)集合中的整数，注意不包括16
        if (result == 1) {
            return true;
        }
        return false;
    }


    public static Boolean oneOfFour() {
        Random random = new Random();// 定义随机类
        int result = random.nextInt(4) + 1;// 返回[0,16)集合中的整数，注意不包括16
        if (result == 1) {
            return true;
        }
        return false;
    }

    public static Boolean oneOfFive() {
        Random random = new Random();// 定义随机类
        int result = random.nextInt(20) + 1;// 返回[0,16)集合中的整数，注意不包括16
        if (result >= 1 && result<=4) {
            return true;
        }
        return false;
    }

    public static Boolean oneOfSix() {
        Random random = new Random();// 定义随机类
        int result = random.nextInt(24) + 1;// 返回[0,16)集合中的整数，注意不包括16
        if (result >= 1 && result<=4) {
            return true;
        }
        return false;
    }

}
