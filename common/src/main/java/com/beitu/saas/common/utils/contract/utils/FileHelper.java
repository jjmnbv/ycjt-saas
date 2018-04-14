package com.beitu.saas.common.utils.contract.utils;

import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileHelper {

    private static Log LOGGER = LogFactory.getLog(AlgorithmHelper.class);

    /***
     * 获取文件基本信息
     *
     * @param filePath
     * @return
     */
    public static Map<String, String> getFileInfo(String filePath) {
        Map<String, String> fileInfos = new LinkedHashMap<String, String>();
        File file = new File(filePath);
        fileInfos.put("FileName", file.getName());
        fileInfos.put("FileLength", String.valueOf(file.length()));
        return fileInfos;
    }

    /***
     * 获取文件的Bytes
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(String filePath) {
        File file = new File(filePath);
        FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = new FileInputStream(file);
            buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.info("获取文件字节流失败：" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("获取文件字节流失败：" + e.getMessage());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.info("FileInputStream 关闭时发生异常：" + e.getMessage());
            }
        }
        return buffer;
    }

    /***
     * 获取 输入流 的Bytes
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(InputStream inputStream) {
        byte[] buffer = null;
        try {
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("获取输入流字节流失败：" + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.info("inputStream 关闭时发生异常：" + e.getMessage());
            }
        }
        return buffer;
    }

    /**
     * @param bytes
     * @param folder
     * @param fileName
     * @return
     */
    public static Map<String, String> saveFileByStream(byte[] bytes, String folder, String fileName) {
        Map<String, String> fileResult = new HashMap<String, String>();
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(folder);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(folder + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            fileResult.put("errCode", "0");
            fileResult.put("msg", "保存签署后文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("保存签署后文件失败：" + e.getMessage());
            fileResult.put("errCode", "0");
            fileResult.put("msg", "保存签署后文件成功");
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    LOGGER.info("保存签署后文件失败：" + e1.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    LOGGER.info("保存签署后文件失败：" + e1.getMessage());
                }
            }
        }
        return fileResult;
    }

    /***
     * 获取文件MD5值
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String getFileMD5(String filePath) {
        FileInputStream fis = null;
        BigInteger bigInt = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            bigInt = new BigInteger(1, md.digest());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        }
        return bigInt.toString(16);
    }

    /***
     * 创建文件夹
     *
     * @param folderPath
     * @return
     */
    public static boolean createDirectory(String folderPath) {
        boolean isSuccess = false;
        File file = new File(folderPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
            isSuccess = true;
        } else {
            isSuccess = true;
        }
        return isSuccess;
    }

    /***
     * 根据图片路径将图片转成Base64数据
     *
     * @return Base64数据
     */
    public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            LOGGER.info("上传的印章图片转sealData错误：" + e.getMessage());
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        byte[] en = Base64.encodeBase64(data);
        return new String(en);// 返回Base64编码过的字节数组字符串
    }

}
