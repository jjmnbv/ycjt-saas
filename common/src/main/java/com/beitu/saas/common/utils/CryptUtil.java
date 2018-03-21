package com.beitu.saas.common.utils;


import com.beitu.saas.common.config.ConfigUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;

/**
 * @Author xiaochong
 * @Create 2017/6/27 下午8:46
 * @Description AES加密工具类
 */
@Component
public class CryptUtil {

    private static ConfigUtil configUtil;

    @Autowired
    public CryptUtil(ConfigUtil configUtil) {
        this.configUtil=configUtil;
    }

    public static String Encrypt(String sSrc){
        return Encrypt(sSrc,configUtil.getAESKey(),configUtil.getAESIv());
    }

    public static String Decrypt(String sSrc){
        return Decrypt(sSrc,configUtil.getAESKey(),configUtil.getAESIv());
    }
    /**
     * AES 加密
     * 加密采用AES加密，然后base64编码。
     * 算法/模式/补码方式
     *
     * @param sSrc  明文字符串
     * @param sKey  16位key
     * @param ivStr 16为向量
     * @return 加密后并base64编码的字符串
     * @throws
     * @throws Exception
     * @throws Exception
     */
    public static String Encrypt(String sSrc, String sKey, String ivStr)  {
        try {
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes());
            return Base64.encodeBase64String(encrypted);//此处使用BAES64做转码功能，同时能起到2次加密的作用。
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密
     * base64先进行解码
     * 算法/模式/补码方式
     * AES/CBC/PKCS5Padding
     *
     * @param sSrc  base64编码过并且加密后的字符串，该字符串 先加密在base64
     * @param sKey  16位key字符串
     * @param ivStr 16位向量字符串
     * @return 明文
     * @throws Exception
     * @throws Exception
     */
    public static String Decrypt(String sSrc, String sKey, String ivStr){
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            URL location = Base64.class.getProtectionDomain().getCodeSource().getLocation();
            System.out.print("url-------"+location);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用bAES64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {

       // String encrypt = CryptUtil.Encrypt("1000.78");
       // System.out.println(encrypt);

        /*CryptUtil a = new CryptUtil();
        try {
            String s = CryptUtil.Encrypt("1000.78", "Y90y0L04m5c6o888", "P3E40q04o704qly9");
            System.out.println("加密后" + s);
            String b = CryptUtil.Decrypt("m6N9kJTpHoC98aCywOStuQ==", "Y90y0L04m5c6o888", "P3E40q04o704qly9");
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
