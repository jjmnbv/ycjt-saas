package com.beitu.saas.risk.helpers;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;

/**
 * des加密工具
 */
@SuppressWarnings("restriction")
public class DES {

	private static byte[] desKey = "6f04aa1004e7dffbe632615539887f86".getBytes();
	private static String key = "6f04aa1004e7dffbe632615539887f86";
	
	private final static String LeHuaHuaIV = "01234567";

	public DES(String desKey) {
		DES.desKey = key.getBytes();
	}

	public static byte[] desEncrypt(byte[] plainText, String desKey) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key,sr);
		byte data[] = plainText;
		byte encryptedData[] = cipher.doFinal(data);
		return encryptedData;
	}
	

	public static byte[] desDecrypt(byte[] encryptText, String desKey) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		byte encryptedData[] = encryptText;
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return decryptedData;
	}

	/**
	 * 加密
	 * @param input 加密的字符串
	 * @return 加密串
	 * @throws Exception
	 */
	public static String encrypt(String input) throws Exception {
		return encrypt(input, key);
	}
	
	public static String encrypt(String input, String key) throws Exception {
		return base64Encode(desEncrypt(input.getBytes(), key));
	}
	
	public static String leHuaHua3DesEncrypt(String plainText, String secretKey) throws Exception {
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes("UTF-8"));
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		Key deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(LeHuaHuaIV.getBytes("utf-8"));
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes("utf-8"));
		return base64Encode(encryptData);
	}
	
	public static String leHuaHua3DesDecrypt(String encryptText, String secretKey) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes("UTF-8"));
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(LeHuaHuaIV.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(base64Decode(encryptText));

		return new String(decryptData, "utf-8");
	}

	/**
	 * 解密
	 * @param input 解密串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(String input) throws Exception {
		return decrypt(input, key);
	}
	
	public static String decrypt(String input, String key) throws Exception {
		if(null == input)input="";
		byte[] result = base64Decode(input);
		return new String(desDecrypt(result, key));
	}

	public static String base64Encode(byte[] s) {
		if (s == null)
			return null;
		BASE64Encoder b = new BASE64Encoder();
		return b.encode(s);
	}

	public static byte[] base64Decode(String s) throws IOException {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(s);
		return b;
	}

	public static String des(String input){
		try {
			return base64Encode(encrypt(input).getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		try {
			System.out.println(decrypt("f3uYQM8fduA="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}