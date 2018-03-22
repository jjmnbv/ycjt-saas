package com.beitu.saas.risk.domain.exception;


import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;

/**
 * Created by zcg on 15/4/20.
 */
public class BizException extends RuntimeException {

    private ErrorCodeEnums errorCode;


    public BizException() {
    }

    public BizException(ErrorCodeEnums errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
    }

    public BizException(ErrorCodeEnums errorCodeEnum, String message) {
        super(message);
        this.errorCode = errorCodeEnum;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorCodeEnums getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnums errorCode) {
        this.errorCode = errorCode;
    }


}
