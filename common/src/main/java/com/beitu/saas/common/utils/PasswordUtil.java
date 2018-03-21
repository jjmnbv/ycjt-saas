package com.beitu.saas.common.utils;

import java.util.regex.Pattern;

/**
 * Created by linchengyu on 17/3/21.
 */
public class PasswordUtil {
    
    public static Boolean isPasswordValidate(String password) {
        String regexPassword1 = "(?=.*?[a-zA-Z]+.*?)(?=.*?[0-9]+.*?).{6,16}$";
        String regexPassword2 = "(?=.*?[a-zA-Z]+.*?)(?=.*?[\\p{Punct}]+.*?).{6,16}$";
        String regexPassword3 = "(?=.*?[0-9]+.*?)(?=.*?[\\p{Punct}]+.*?).{6,16}$";
        
        return (Pattern.matches(regexPassword1, password) || Pattern.matches(regexPassword2, password) || Pattern.matches(regexPassword3, password));
    }
    
//    public static void main(String[] args) {
//        String[] sa = {"!@#$%^&*()_+aaa", "abcde1", "123456a", "12$?@#", "?#$abcd", "c1211111", "0c0010000"};
//
//        for (String s : sa) {
//            System.out.printf("%s:%s\n", s, PasswordUtil.isPasswordValidate(s));
//        }
//    }
}
