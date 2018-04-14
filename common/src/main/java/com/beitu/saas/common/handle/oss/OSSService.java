package com.beitu.saas.common.handle.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.beitu.saas.common.config.ConfigUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Component
public class OSSService {

    private static final Log LOGGER = LogFactory.getLog(OSSService.class);

    @Resource
    private ConfigUtil configUtil;

    public void uploadFile(File file) {
        OSSClient client = null;
        InputStream input = null;
        try {
            client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());

            ObjectMetadata meta = new ObjectMetadata();

            input = new FileInputStream(file);
            meta.setContentLength(file.length());

            client.putObject(configUtil.getBucketName(), file.getName(), input, meta);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {

            try {
//                if (client != null)
//                    client.shutdown();
                if (input != null)
                    input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String uploadFile(String fileName, byte[] content) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(content.length);
        client.putObject(configUtil.getBucketName(), fileName, new ByteArrayInputStream(content), meta);

        return fileName;
    }

    public String uploadFile(String fileName, String content) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(content.getBytes().length);
        client.putObject(configUtil.getBucketName(), fileName, new ByteArrayInputStream(content.getBytes()), meta);

        return fileName;
    }

    public String uploadFile(InputStream inputStream, long contentLength, String fileName) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(contentLength);
        client.putObject(configUtil.getBucketName(), fileName, inputStream, meta);

        return fileName;
    }

    public void uploadFile(InputStream inputStream, long contentLength, String fileName, String contentType) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());

        ObjectMetadata meta = new ObjectMetadata();

        meta.setContentLength(contentLength);
        if (StringUtils.isNotEmpty(contentType)) meta.setContentType(contentType);

        PutObjectResult result = client.putObject(configUtil.getBucketName(), fileName, inputStream, meta);

    }

    public boolean getFile(String prefix) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(configUtil.getBucketName(), prefix, "", "", null);
        ObjectListing listing = client.listObjects(listObjectsRequest);
        List<OSSObjectSummary> ossOSList = listing.getObjectSummaries();
        return ossOSList != null && !ossOSList.isEmpty() ? true : false;
    }

    public List getFileList(String prefix) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(configUtil.getBucketName(), prefix, "", "", null);
        ObjectListing listing = client.listObjects(listObjectsRequest);
        List<OSSObjectSummary> ossOSList = listing.getObjectSummaries();
        return ossOSList;
    }

    public String getFileContent(String fileNamePath) {
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        OSSObject ossObject = null;
        InputStream objectContent = null;
        String content = "";
        try {
            ossObject = client.getObject(configUtil.getBucketName(), fileNamePath);
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

    private byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}
