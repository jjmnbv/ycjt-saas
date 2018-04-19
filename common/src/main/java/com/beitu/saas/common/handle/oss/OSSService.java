package com.beitu.saas.common.handle.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.beitu.saas.common.config.ConfigUtil;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    /**
     * 压缩 OSS多个文件
     *
     * @param response
     * @param filePathArray oss文件路径Array
     */
    public void zipOssFiles(HttpServletResponse response, String[] filePathArray) {
        if (filePathArray == null || filePathArray.length == 0) {
            return;
        }
        response.reset();
        response.setHeader("Content-disposition","attachment; filename=lendCertificate.zip");
        response.setContentType("application/zip");
        OSSClient client = new OSSClient(configUtil.getEndpoint(), configUtil.getAccessKeyId(), configUtil.getAccessKeySecret());
        ZipOutputStream outputStream = null;
        InputStream ossInputStream = null;
        byte[] buf = new byte[1024];
        try {
            outputStream = new ZipOutputStream(response.getOutputStream());
            for (int i = 0; i < filePathArray.length; i++) {
                String filePath = filePathArray[i];
                if (StringUtils.isEmpty(filePath)) {
                    continue;
                }
                OSSObject ossObject = client.getObject(configUtil.getBucketName(), filePath);
                ossInputStream = ossObject.getObjectContent();
                outputStream.putNextEntry(new ZipEntry(getFileNameByOssFilePath(filePath)));
                int len;
                while ((len = ossInputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.closeEntry();
                ossInputStream.close();
            }
            outputStream.close();
        } catch (IOException e) {
        } finally {
            // 关闭流
            try {
                if (ossInputStream != null) {
                    ossInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private String getFileNameByOssFilePath(String ossFilePath) {
        int index = ossFilePath.lastIndexOf("/");
        return ossFilePath.substring(index + 1);
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
