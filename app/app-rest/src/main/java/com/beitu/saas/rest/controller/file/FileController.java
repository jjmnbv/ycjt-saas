package com.beitu.saas.rest.controller.file;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.enums.FileErrorCodeEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.rest.controller.file.request.DownloadZipRequest;
import com.beitu.saas.rest.controller.file.response.FileUploadSuccessResponse;
import com.beitu.saas.risk.helpers.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;


/**
 * @author linanjun
 * @create 2018/3/23 下午5:33
 * @description
 */
@Controller
@RequestMapping("/file")
@Api(description = "文件模块")
public class FileController {

    @Autowired
    private OSSService ossService;

    @Autowired
    private ConfigUtil configUtil;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    @ApiOperation(value = "文件上传", response = FileUploadSuccessResponse.class)
    public DataApiResponse<FileUploadSuccessResponse> upload(@RequestParam(value = "name") MultipartFile uploadFile) {
        if (uploadFile == null) {
            return new DataApiResponse<>(FileErrorCodeEnum.ERROR_FILE);
        }

        String fileName = getFileName(uploadFile.getOriginalFilename());
        InputStream inputStream = null;
        try {
            inputStream = uploadFile.getInputStream();
            String url = ossService.uploadFile(inputStream, inputStream.available(), fileName);
            return new DataApiResponse<>(new FileUploadSuccessResponse(url));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {

                }
            }
        }
        return new DataApiResponse<>(FileErrorCodeEnum.UPLOAD_FILE_FAILURE);
    }

    @SignIgnore
    @RequestMapping(value = "/download/zip", method = RequestMethod.GET)
    @ApiOperation(value = "多个OSS文件打包下载", response = FileUploadSuccessResponse.class)
    public void downloadZip(@RequestParam(value = "filePaths") String filePaths, HttpServletResponse response) {
        if (StringUtils.isEmpty(filePaths)) {
            return;
        }
        String[] filePathArray = filePaths.split(",");
        ossService.zipOssFiles(response, filePathArray);
    }

    private String getFileName(String originalFilename) {
        String ossFileName = "saasFile/";
        if (configUtil.isServerTest()) {
            ossFileName += "test/";
        }
        ossFileName = ossFileName + OrderNoUtil.makeOrderNum() + originalFilename;
        return ossFileName;
    }

}
