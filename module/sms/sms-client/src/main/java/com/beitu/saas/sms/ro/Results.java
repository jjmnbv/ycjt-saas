package com.beitu.saas.sms.ro;

public class Results {

    public static <T> Result<T> newSuccessResult(T data)
    {
        return newSuccessResult(data, StateCode.SUCCESS.getMessage());
    }

    public static <T> Result<T> newSuccessResult(T data, String statusText)
    {
        return newResult(data, StateCode.SUCCESS, statusText);
    }

    public static <T> Result<T> newFailedResult(StateCode failedCode)
    {
        return newFailedResult(null, failedCode,null);
    }

    public static <T> Result<T> newFailedResult(T data, StateCode failedCode)
    {
        return newFailedResult(data, failedCode,failedCode.getMessage());
    }

    public static <T> Result<T> newFailedResult(T data, StateCode failedCode, String statusText)
    {
        return newResult(data,failedCode,statusText);
    }

    public static <T> Result<T> newResult(T data, StateCode failedCode, String statusText)
    {
        Result<T> result = new Result();
        result.setData(data);
        result.setStateCode(failedCode);
        result.setStatusText(statusText);
        return result;
    }
}
