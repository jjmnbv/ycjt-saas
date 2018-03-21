package com.beitu.saas.common.handle.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.beitu.saas.common.config.ConfigUtil;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

}
