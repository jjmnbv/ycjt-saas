package com.beitu.saas.risk.domain.platform.rongScore.utils;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 生成私钥：openssl genrsa -out rsa_private_key.pem 1024 
 * 根据私钥生成公钥：openssl rsa -in rsa_private_key.pem -out rsa_public_key.pem -pubout
 * 私钥还不能直接被使用，需要进行PKCS#8编码：openssl pkcs8 -topk8 -in rsa_private_key.pem -out pkcs8_rsa_private_key.pem -nocrypt 
 * sha1签名 openssl sha1 -sign rsa_private_key.pem -out rsasign.bin tos.txt 
 * pkcs8_rsa_private_key 私钥
 * java可以使用
 */
public class RsaEncrypt {

 
	public final String RONG_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDk0QxbyRY49V62lvYy3htjy+uV"
    										+ "sT0eADLJPt3GbC9JeFtUjb1Q3yZi0be7YIgrrqJWtekw8SCr5l4ao47hZPA6X3HD"
    										+ "GJi8/FkPFOOzGK4KSTkshFefc2Udqy2ctRwJdji1TtsuPwyIsGAd3PV6NGQxtXGx"
    										+ "j3TeE/335+1RnA6QJwIDAQAB";
    
    
    public final String DEFAULT_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALGttsGj1mR3i5DXr9zHQsnBQmlQcBgdGdLAzy4+ChGQ8RddEVH2xa9ndYAV2NIH/cpJ3wsArfg/yyrj0Gi5TEInDSHqja6/TAojLvSTeZNzgpUXXaIKU99lHV9DcWYms4+vfkCK5lPP5iwynbhZqB4hXPdVnupibMxnx0fQTP5lAgMBAAECgYBkxQH+pt4kEszb9Afwri5J4Mq6kf1RH8gSiHm4X0U0m0CxIH5/Pvb1OTch0sIqtI+Sl/zg75y7S+r9Q0Fi85y0ghkbLFmmZgv8bGzh4k34WWFCyPMRmexOhsi+IGpTOYkbRnJbycxuY0TXJHHYvEcS0I14fUvI8gbflf12l6DCwQJBANoPXAjHF4TQm0h+qTyFmB4NoGlV3DNH2yk2D2UlKJ+9o7YqjMmw/jNQyeNb65Dj62ij57FQrbn4yfHLBWlkxnECQQDQl7g4kNtDzh0eMMcZYVKU7W/eEkd/G8WeldH26RWE1kZzYBIHeQJiujASDFodM3pqbJ3ssOCizoN1Acy+LPk1AkBvBXjvQyZXSbp1238gwEflrTxpxPw646/SuKJ39cZMJkpu2hUaL1INIGnZpxg1icV2XlT9cz0wPVncEGit81ARAkBkhrnhiO4i31JpGljZgm2qGEOxYl3ShC/ZHZryw6H7QsjxEXue3lKoAdLo38sOB6EXaQEy8ItR6vSvjw59GG+BAkBWwm0ObgH9LJ4eF3ZLCLvViScML3QJr2vAOh3GBsa1avWu/ffD5e3xZGdpmkfEMxWW8nTErFAL46xn8XBLsZqs";

    /**
     * rsa签名
     * 
     * @param content
     *            待签名的字符串
     * @param priKey
     *            rsa私钥字符串
     *            字符编码
     * @return 签名结果
     * @throws Exception
     *             签名失败则抛出异常
     */
    public byte[] rsaSign(String content, RSAPrivateKey priKey)
            throws SignatureException {
        try {

            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes("utf-8"));

            byte[] signed = signature.sign();
            return signed;
        } catch (Exception e) {
            throw new SignatureException("RSAcontent = " + content
                    + "; charset = ", e);
        }
    }

    /**
     * rsa验签
     * 
     * @param content
     *            被签名的内容
     * @param sign
     *            签名后的结果
     * @param pubKey
     *            rsa公钥
     *            字符集
     * @return 验签结果
     * @throws SignatureException
     *             验签失败，则抛异常
     */
    public boolean doCheck(String content, byte[] sign, RSAPublicKey pubKey)
            throws SignatureException {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes("utf-8"));
            return signature.verify((sign));
        } catch (Exception e) {
            throw new SignatureException("RSA验证签名[content = " + content
                    + "; charset = " + "; signature = " + sign + "]发生异常!", e);
        }
    }

    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 获取私钥
     * 
     * @return 当前的私钥对象
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * 获取公钥
     * 
     * @return 当前的公钥对象
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 随机生成密钥对
     */
    public void genKeyPair() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    /**
     * 从字符串中加载公钥
     * 
     * @param publicKeyStr
     *            公钥数据字符串
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public void loadPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = Base64Utils.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (IOException e) {
            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 加载私钥
     * 
     * @param privateKeyStr
     *            私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public void loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64Utils.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            this.privateKey = (RSAPrivateKey) keyFactory
                    .generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (IOException e) {
            throw new Exception("私钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 加密过程
     * 
     * @param publicKey
     *            公钥
     * @param plainTextData
     *            明文数据
     * @return
     * @throws Exception
     *             加密过程中的异常信息
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 解密过程
     * 
     * @param privateKey
     *            私钥
     * @param cipherData
     *            密文数据
     * @return 明文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
            throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // , new BouncyCastleProvider()
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 字节数据转十六进制字符串
     * 
     * @param data
     *            输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

    // btye转换hex函数
    public static String ByteToHex(byte[] byteArray) {
        StringBuffer StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return StrBuff.toString();
    }

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static byte[] readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        byte[] txt = new byte[(int) file.length()];
        try {
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            int i = 0;

            while ((tempbyte = in.read()) != -1) {
                txt[i] = (byte) tempbyte;
                i++;
            }
            in.close();
            return txt;
        } catch (IOException e) {
            e.printStackTrace();
            return txt;
        }
    }

}