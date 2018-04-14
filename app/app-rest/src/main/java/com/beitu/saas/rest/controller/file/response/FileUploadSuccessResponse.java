package com.beitu.saas.rest.controller.file.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linanjun
 * @create 2018/3/23 下午5:34
 * @description
 */
@ApiModel(value = "文件上传成功返回信息")
public class FileUploadSuccessResponse implements ResponseData {

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    public FileUploadSuccessResponse(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}