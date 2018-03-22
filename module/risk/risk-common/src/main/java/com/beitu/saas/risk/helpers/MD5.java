package com.beitu.saas.risk.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类 
 *
 */
public class MD5 {

	public static String md5(String plainText) {
		String str;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
		return null;
	}


	public static void main(String[] args) {
		System.out.println(md5("ruru520"));
	}
}
