package com.beitu.saas.app.api;

import com.fqgj.common.api.Page;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.api.enums.ErrorCodeEnum;

/**
 * @author linanjun
 * @create 2018/3/21 下午2:15
 * @description
 */
public class ModuleApiResponse<T extends ResponseData> extends ApiResponse {

    protected T data;

    protected Page page;


    public ModuleApiResponse() {
        super();
    }

    public ModuleApiResponse(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public ModuleApiResponse(T data, Page page) {
        super();
        this.data = data;
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

}