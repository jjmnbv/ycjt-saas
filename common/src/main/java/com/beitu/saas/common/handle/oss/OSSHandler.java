package com.beitu.saas.common.handle.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.beitu.saas.common.config.ConfigUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author zxy
 * @ClassName: OSSService
 * @Description: 开放存储服务OSS图片上传服务
 * @date 2015年6月24日 上午11:01:23
 */
@Component
public class OSSHandler {
    
    private static final Log LOGGER = LogFactory.getLog(OSSHandler.class);
    
    @Autowired
    private ConfigUtil configUtil;
    
    /**
     * 上传文件
     *
     * @param bucketName
     * @param inputStream
     * @param contentLength
     * @param fileName
     */
    public void uploadFile(String bucketName, InputStream inputStream, Long contentLength, String fileName) {
        long start = System.currentTimeMillis();
        try {
            OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(contentLength);
            if (fileName.indexOf(".html") > 0) {
                meta.setContentType("text/html");
            }
            if (fileName.indexOf(".mp3") > 0) {
                meta.setContentType("audio/mp3");
            }
            if (fileName.indexOf(".jpg") > 0 || fileName.indexOf(".gif") > 0 || fileName.indexOf(".png") > 0 || fileName.indexOf(".ico") > 0
                    || fileName.indexOf(".bmp") > 0 || fileName.indexOf(".jpeg") > 0) {
                meta.setContentType("image/jpeg");
            }
            client.putObject(bucketName, fileName, inputStream, meta);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        long time = System.currentTimeMillis() - start;
        LOGGER.debug("Cost {} ms to upload the file {}", time, fileName);
    }
    
    /**
     * 删除文件
     *
     * @param bucketName
     * @param fileNamePath
     */
    public void delFile(String bucketName, String fileNamePath) {
        if (bucketName == null || bucketName.trim().equals("")
                || fileNamePath == null || fileNamePath.trim().equals("")
                ) {
            return;
        }
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        client.deleteObject(bucketName, fileNamePath);
    }
    
    /**
     * 得到内容
     *
     * @param bucketName
     * @param fileNamePath
     * @return
     */
    public String getFileContent(String bucketName, String fileNamePath) {
        if (bucketName == null || bucketName.trim().equals("")
                || fileNamePath == null || fileNamePath.trim().equals("")) {
            return "";
        }
        
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        
        OSSObject ossObject = null;
        InputStream objectContent = null;
        String content = "";
        try {
            ossObject = client.getObject(bucketName, fileNamePath);
            // 获取Object的输入流
            objectContent = ossObject.getObjectContent();
            content = new String(input2byte(objectContent), "utf-8");
        } catch (IOException e) {
        } finally {
            // 关闭流
            try {
                if (objectContent != null)
                    objectContent.close();
            } catch (IOException e) {
            }
        }
        return content;
    }
    
    public byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
    
    /**
     * 获取文件集合
     *
     * @param bucketName
     * @param prefix
     * @return
     */
    public List<OSSObjectSummary> getFile(String bucketName, String prefix) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName, prefix, "", "", null);
        ObjectListing listing = client.listObjects(listObjectsRequest);
        List<OSSObjectSummary> ossOSList = listing.getObjectSummaries();
        if (ossOSList != null && !ossOSList.isEmpty()) {
            for (OSSObjectSummary ossObject : ossOSList) {
                ossObject.setKey(configUtil.getAddressURLPrefix() + ossObject.getKey());
            }
            Collections.reverse(ossOSList); // 倒序排列
        }
        return ossOSList != null && !ossOSList.isEmpty() ? ossOSList : null;
    }
    
}
