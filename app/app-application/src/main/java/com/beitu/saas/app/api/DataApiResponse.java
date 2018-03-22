package com.beitu.saas.app.api;

import com.fqgj.common.api.Page;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.api.enums.ErrorCodeEnum;

/**
 * Created by linchengyu on 17/6/17.
 */
public class DataApiResponse<T extends ResponseData> extends ApiResponse {

    protected T data;


    public DataApiResponse() {
        super();
    }

    public DataApiResponse(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public DataApiResponse(T data) {
        super();
        this.data = data;
    }

    public DataApiResponse(T data, String msg) {
        super(msg);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
