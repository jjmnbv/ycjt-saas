package com.beitu.saas.rest.controller.file.request;

import com.fqgj.common.api.ParamsObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author linanjun
 * @create 2018/4/19 下午5:15
 * @description
 */
@ApiModel(description = "OSS文件打包后下载请求参数")
public class DownloadZipRequest extends ParamsObject {

    @ApiModelProperty(value = "文件路径（多个文件以','分割）", required = true)
    @NotBlank(message = "文件路径不能为空")
    private String filePaths;

    public String getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(String filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public void validate() {

    }

}
