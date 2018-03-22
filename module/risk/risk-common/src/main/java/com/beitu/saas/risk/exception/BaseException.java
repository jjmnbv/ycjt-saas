package com.beitu.saas.risk.exception;

/**
 * Created by ganlv on 21/5/16.
 */
public class BaseException extends Exception{

    private Integer code;
    private String message;

    public BaseException(){}

    public BaseException(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
