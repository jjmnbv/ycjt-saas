package com.beitu.saas.app.enums;

import com.fqgj.common.api.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author linanjun
 * @create 2018/3/23 下午6:03
 * @description
 */
public enum FileErrorCodeEnum implements ErrorCodeEnum {

    ERROR_FILE(10001, "请选择正确的文件", HttpStatus.OK),
    UPLOAD_FILE_FAILURE(10002, "上传文件失败", HttpStatus.OK);

    private Integer code;

    private String msg;

    private HttpStatus httpStatus;

    FileErrorCodeEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public FileErrorCodeEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public FileErrorCodeEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public FileErrorCodeEnum setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

}